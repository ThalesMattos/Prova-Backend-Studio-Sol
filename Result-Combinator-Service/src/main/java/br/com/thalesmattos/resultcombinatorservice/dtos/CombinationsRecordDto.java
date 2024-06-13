package br.com.thalesmattos.resultcombinatorservice.dtos;

import jakarta.validation.constraints.NotNull;

public record CombinationsRecordDto(@NotNull int combinations) {
}
