package br.com.thalesmattos.resultcombinatorservice.dtos;

import jakarta.validation.constraints.NotNull;

public record Score(
        @NotNull String score
) {
}
