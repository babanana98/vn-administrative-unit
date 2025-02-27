package com.babanana.generator.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ProvincesJson(
    @JsonProperty("data")
    List<ProvincesDataJson> provincesDataJson
) {
}
