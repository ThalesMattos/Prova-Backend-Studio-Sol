package br.com.thalesmattos.resultcombinatorservice;

import br.com.thalesmattos.resultcombinatorservice.dtos.ScoreRecordDto;
import br.com.thalesmattos.resultcombinatorservice.services.CalcularCombinacoesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcularCombinacoesServiceTest {

    @InjectMocks
    private CalcularCombinacoesService calcularCombinacoesService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalcularCombinacoes() {
        ScoreRecordDto score = new ScoreRecordDto("48x100");

        int combinacoesEsperadas = 230;

        int result = calcularCombinacoesService.calcularCombinacoes(score);

        assertEquals(combinacoesEsperadas, result);
    }
}
