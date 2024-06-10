package br.com.thalesmattos.resultcombinatorservice.services;

import br.com.thalesmattos.resultcombinatorservice.dtos.Score;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalcularCombinacoesService {
    private static final int[] SCORES = {3, 6, 7, 8};

    public int calcularCombinacoes(Score score) {
        var placar = conversaoPlacarParaInteiro(score.score());
        if (combinacaoIgualAZero(placar) == true) {
            return 0;
        } else {
            List<Integer> combinacoesTimes = new ArrayList<>();
            for(int pontuacaoDoTime : placar) {
                var combinacoes = new int[pontuacaoDoTime + 1];
                combinacoes[0] = 1;

                for (int pontuacaoFixa : SCORES) {
                    for (int i = pontuacaoFixa; i <= pontuacaoDoTime; i++) {
                        combinacoes[i] += combinacoes[i - pontuacaoDoTime];
                    }
                }
                combinacoesTimes.add(combinacoes[pontuacaoDoTime]);
            }
            if (combinacoesTimes.get(0) > combinacoesTimes.get(1)){
                return combinacoesTimes.get(0);
            } else {
                return combinacoesTimes.get(1);
            }
        }
    }

    private int[] conversaoPlacarParaInteiro(String score) {
        var placarString = score.split("x");
        return new int[]{Integer.parseInt(placarString[0]),
                Integer.parseInt(placarString[1])};
    }

    private boolean combinacaoIgualAZero(int[] placares) {
        boolean igualAZero = false;
        for (var placar : placares) {
            if (placar == 1 || placar == 2 || placar == 4 || placar == 5) {
                igualAZero = true;
            }
        }
        return igualAZero;
    }
}