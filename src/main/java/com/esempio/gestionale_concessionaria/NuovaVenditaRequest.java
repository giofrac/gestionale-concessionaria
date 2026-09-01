package com.esempio.gestionale_concessionaria;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record NuovaVenditaRequest(
        @NotNull(message = "L'id del veicolo è obbligatorio") Long veicoloId,
        @NotNull(message = "L'id del cliente è obbligatorio") Long clienteId,
        @PositiveOrZero(message = "Il prezzo non può essere negativo") double prezzo
) {}
