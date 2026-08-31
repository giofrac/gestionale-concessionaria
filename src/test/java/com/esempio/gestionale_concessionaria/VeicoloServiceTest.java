package com.esempio.gestionale_concessionaria;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VeicoloServiceTest {

    @Mock
    private VeicoloRepository veicoloRepository;

    @InjectMocks
    private VeicoloService veicoloService;

    @Test
    void calcolaTassaTotaleFlottaSommaCorrettamente() {
        Automobile a = new Automobile("Fiat", "Panda", 2020, 5);       // tassa 100
        Motocicletta m = new Motocicletta("Yamaha", "MT-07", 2021, 100); // tassa 25

        when(veicoloRepository.findAll()).thenReturn(List.of(a, m));

        double totale = veicoloService.calcolaTassaTotaleFlotta();

        assertEquals(125.0, totale);
    }

    @Test
    void descrizioneVeicoloLanciaEccezioneSeNonTrovato() {
        when(veicoloRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> veicoloService.descrizioneVeicolo(999L));
    }

    @Test
    void registraNuovaAutomobileChiamaSave() {
        when(veicoloRepository.save(any(Automobile.class)))
                .thenAnswer(invocazione -> invocazione.getArgument(0));

        veicoloService.registraNuovaAutomobile("Fiat", "500", 2024, 4);

        verify(veicoloRepository).save(any(Automobile.class));
    }

    @Test
    void descrizioneVeicoloIdEsistenteRestituisceDescrizioneCorretta() {
        Automobile a = new Automobile("Fiat", "Panda", 2020, 5);
        when(veicoloRepository.findById(1L)).thenReturn(Optional.of(a));

        String descrizione = veicoloService.descrizioneVeicolo(1L);

        assertEquals("Fiat Panda 2020", descrizione);
    }
}
