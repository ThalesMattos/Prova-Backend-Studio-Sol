package br.com.thalesmattos.resultcombinatorservice.controllers;

import br.com.thalesmattos.resultcombinatorservice.dtos.ScoreRecordDto;
import br.com.thalesmattos.resultcombinatorservice.services.CalcularCombinacoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
@RequiredArgsConstructor
public class CombinacoesController {

    private final CalcularCombinacoesService calcularCombinacoesService;

    @PostMapping
    public ResponseEntity<Integer> calcularCombinacoes(@RequestBody ScoreRecordDto score){
        var combinacoes = calcularCombinacoesService.calcularCombinacoes(score);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(combinacoes);
    }
}
