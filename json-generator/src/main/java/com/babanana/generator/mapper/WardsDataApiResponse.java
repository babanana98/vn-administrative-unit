package com.babanana.generator.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WardsDataApiResponse(
    @JsonProperty("WardCode")
    String wardCode,
    @JsonProperty("DistrictID")
    Integer districtId,
    @JsonProperty("WardName")
    String wardName
) {
}
