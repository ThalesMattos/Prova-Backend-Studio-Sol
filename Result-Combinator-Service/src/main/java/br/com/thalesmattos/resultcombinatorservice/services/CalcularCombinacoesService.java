package br.com.thalesmattos.resultcombinatorservice.services;

import br.com.thalesmattos.resultcombinatorservice.dtos.ScoreRecordDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalcularCombinacoesService {
    private static final int[] SCORES = {3, 6, 7, 8};

    public int calcularCombinacoes(ScoreRecordDto score) {
        var placar = conversaoPlacarParaInteiro(score.score());
        if (combinacaoIgualAZero(placar)) {
            return 0;
        } else {
            List<Integer> combinacoesDeCadaTime = new ArrayList<>();
            for(int pontuacaoDoTime : placar) {
                var combinacoes = new int[pontuacaoDoTime + 1];
                combinacoes[0] = 1;

                for (int pontuacaoFixa : SCORES) {
                    for (int i = pontuacaoFixa; i <= pontuacaoDoTime; i++) {
                        combinacoes[i] += combinacoes[i - pontuacaoFixa];
                    }
                }
                combinacoesDeCadaTime.add(combinacoes[pontuacaoDoTime]);
            }
            return Math.max(combinacoesDeCadaTime.get(0), combinacoesDeCadaTime.get(1));
        }
    }

    private int[] conversaoPlacarParaInteiro(String score) {
        var placarString = score.split("x");
        return new int[]{Integer.parseInt(placarString[0]),
                         Integer.parseInt(placarString[1])};
    }

    private boolean combinacaoIgualAZero(int[] placares) {
        Set<Integer> placaresInvalidos = new HashSet<>(Arrays.asList(1, 2, 4, 5));
        for (var placar : placares) {
            if (placaresInvalidos.contains(placar)) {
                return true;
            }
        }
        return false;
    }
}