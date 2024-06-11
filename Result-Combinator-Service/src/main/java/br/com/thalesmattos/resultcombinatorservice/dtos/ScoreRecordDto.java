package br.com.thalesmattos.resultcombinatorservice.dtos;

import jakarta.validation.constraints.NotNull;

public record ScoreRecordDto(
        @NotNull String score
) {
}
