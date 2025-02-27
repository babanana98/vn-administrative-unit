package com.babanana.generator.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ProvincesApiResponse(
    @JsonProperty("data")
    List<ProvincesDataApiResponse> data
) {
}
