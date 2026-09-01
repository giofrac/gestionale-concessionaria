package com.esempio.gestionale_concessionaria;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record NuovaAutomobileRequest(
        @NotBlank(message = "La marca è obbligatoria") String marca,
        @NotBlank(message = "Il modello è obbligatorio") String modello,
        @Min(value = 1900, message = "Anno di immatricolazione non valido") int annoImmatricolazione,
        @Min(value = 1, message = "Il numero di posti deve essere almeno 1") int numeroPosti
) {}