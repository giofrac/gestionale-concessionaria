package com.esempio.gestionale_concessionaria;

public record VeicoloResponse(String tipo, String marca, String modello, int anno, double tassaAnnuale, Double autonomiaKm) {
    public static VeicoloResponse from(Veicolo v) {
        String tipo = switch (v) {
            case Automobile a -> "Automobile";
            case Motocicletta m -> "Motocicletta";
            case AutoElettrica a -> "AutoElettrica";
            default -> "Sconosciuto";
        };
        Double autonomia = (v instanceof Elettrico e) ? e.autonomiaKm() : null;
        return new VeicoloResponse(tipo, v.getMarca(), v.getModello(), v.getAnnoImmatricolazione(), v.calcolaTassaAnnuale(), autonomia);
    }
}
