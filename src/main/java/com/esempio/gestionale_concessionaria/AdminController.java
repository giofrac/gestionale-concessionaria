package com.esempio.gestionale_concessionaria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/api/admin/report")
    public String reportRiservato() {
        return "Dati riservati";
    }
}
