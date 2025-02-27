package com.babanana.generator.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProvincesDataApiResponse(
    @JsonProperty("ProvinceID")
    Integer provinceId,
    @JsonProperty("ProvinceName")
    String provinceName
) {
}
