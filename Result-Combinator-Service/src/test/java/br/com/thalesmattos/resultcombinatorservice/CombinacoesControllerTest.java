package br.com.thalesmattos.resultcombinatorservice;

import br.com.thalesmattos.resultcombinatorservice.controllers.CombinacoesController;
import br.com.thalesmattos.resultcombinatorservice.dtos.ScoreRecordDto;
import br.com.thalesmattos.resultcombinatorservice.services.CalcularCombinacoesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CombinacoesControllerTest {

    @InjectMocks
    private CombinacoesController combinacoesController;

    @Mock
    private CalcularCombinacoesService calcularCombinacoesService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalcularCombinacoes() {
        ScoreRecordDto score = new ScoreRecordDto("48x100");
        Integer combinacoesEsperadas = 230;

        when(calcularCombinacoesService.calcularCombinacoes(score)).thenReturn(combinacoesEsperadas);

        ResponseEntity<Integer> response = combinacoesController.calcularCombinacoes(score);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(combinacoesEsperadas, response.getBody());
    }
}
