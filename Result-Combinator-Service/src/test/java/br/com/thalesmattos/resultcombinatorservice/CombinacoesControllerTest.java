package br.com.thalesmattos.resultcombinatorservice;

import br.com.thalesmattos.resultcombinatorservice.controllers.CombinacoesController;
import br.com.thalesmattos.resultcombinatorservice.dtos.CombinationsRecordDto;
import br.com.thalesmattos.resultcombinatorservice.dtos.ScoreRecordDto;
import br.com.thalesmattos.resultcombinatorservice.services.CalcularCombinacoesService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CombinacoesControllerTest {

    @Test
    public void calcularCombinacoesTest() {
        CalcularCombinacoesService service = mock(CalcularCombinacoesService.class);
        CombinacoesController controller = new CombinacoesController(service);
        ScoreRecordDto score = new ScoreRecordDto("3x15");
        when(service.calcularCombinacoes(score)).thenReturn(4);

        ResponseEntity<CombinationsRecordDto> response = controller.calcularCombinacoes(score);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(4, Objects.requireNonNull(response.getBody()).combinations());
    }
}
