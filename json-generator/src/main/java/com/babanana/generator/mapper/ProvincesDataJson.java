package com.babanana.generator.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProvincesDataJson(
    @JsonProperty("id")
    Integer id,
    @JsonProperty("name")
    String name
) {
}
