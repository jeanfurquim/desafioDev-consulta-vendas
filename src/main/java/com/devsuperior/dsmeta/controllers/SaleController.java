package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleResumeDTO;
import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/report")
    public ResponseEntity<Page<SaleSellerDTO>> getReport(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            @RequestParam(value = "name", defaultValue = "") String name,
            Pageable pageable
    ) {
        Page<SaleSellerDTO> dto = service.searchReport(minDate, maxDate, name, pageable);
        return ResponseEntity.ok(dto);


    }

    @GetMapping(value = "/summary")
    public ResponseEntity<List<SaleResumeDTO>> getSummary(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate
    ) {
        List<SaleResumeDTO> dto = service.getResume(minDate, maxDate);
        return ResponseEntity.ok(dto);
    }
}
