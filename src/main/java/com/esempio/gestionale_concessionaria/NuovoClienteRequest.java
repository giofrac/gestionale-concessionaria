package com.esempio.gestionale_concessionaria;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NuovoClienteRequest(
        @NotBlank(message = "Il nome è obbligatorio")
        String nome,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "Email non valida")
        String email,

        @Size(min = 6, max = 15, message = "Il telefono deve avere tra 6 e 15 caratteri")
        String telefono
) {}
