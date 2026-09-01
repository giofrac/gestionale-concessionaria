package com.esempio.gestionale_concessionaria;

import java.time.LocalDate;

public record VenditaResponse(Long id, String veicoloDescrizione, String clienteNome, LocalDate dataVendita, double prezzo) {
    public static VenditaResponse from(Vendita v) {
        return new VenditaResponse(
                v.getId(),
                v.getVeicolo().getMarca() + " " + v.getVeicolo().getModello(),
                v.getCliente().getNome(),
                v.getDataVendita(),
                v.getPrezzo()
        );
    }
}
