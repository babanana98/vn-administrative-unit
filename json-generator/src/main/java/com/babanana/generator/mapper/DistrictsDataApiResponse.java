package com.babanana.generator.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DistrictsDataApiResponse(
    @JsonProperty("DistrictID")
    Integer districtId,
    @JsonProperty("ProvinceID")
    Integer provinceId,
    @JsonProperty("DistrictName")
    String districtName
) {
}
