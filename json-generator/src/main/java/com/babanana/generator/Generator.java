package com.babanana.generator;

import com.babanana.generator.mapper.DistrictsApiResponse;
import com.babanana.generator.mapper.ProvincesApiResponse;
import com.babanana.generator.mapper.WardsApiResponse;
import com.babanana.generator.repository.ExternalApiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Generator {

  private static final String TOKEN = "TestToken";
  private static final String GHN_DOMAIN = "https://online-gateway.ghn.vn/";
  private static final String PROVINCES_URI = "shiip/public-api/master-data/province";
  private static final String DISTRICTS_URI = "shiip/public-api/master-data/district";
  private static final String WARDS_URI = "shiip/public-api/master-data/ward";
  private static final Path PROVINCES_PATH = Path.of("provinces/provinces.min.json");
  private static final Path DISTRICTS_PATH = Path.of("districts");
  private static final Path WARDS_PATH = Path.of("wards");

  private static int numOfDistricts = 0;
  private static int numOfWards = 0;

  public static void main(String[] args) {
    var externalApiRepository = new ExternalApiRepository();

    // Get provinces from external api
    var provincesApiResponse = externalApiRepository.get(GHN_DOMAIN + PROVINCES_URI,
        Map.of("token", TOKEN), null, ProvincesApiResponse.class);

    // Write provinces to json file
    writeToFile(PROVINCES_PATH, provincesApiResponse);

    provincesApiResponse.data().forEach(province -> {
      String provinceId = province.provinceId().toString();

      // Get districts from external api
      var districtsApiResponse = externalApiRepository.get(GHN_DOMAIN + DISTRICTS_URI,
          Map.of("token", TOKEN), Map.of("province_id", provinceId), DistrictsApiResponse.class);

      // Write districts to json file
      writeToFile(DISTRICTS_PATH.resolve(provinceId + ".json"), districtsApiResponse);
      numOfDistricts += 1;

      districtsApiResponse.data().forEach(district -> {
        String districtId = district.districtId().toString();

        // Get wards from external api
        var wardsApiResponse = externalApiRepository.get(GHN_DOMAIN + WARDS_URI,
            Map.of("token", TOKEN), Map.of("district_id", districtId), WardsApiResponse.class);

        // Write wards to json file
        writeToFile(WARDS_PATH.resolve(districtId + ".json"), wardsApiResponse);
        numOfWards += 1;
      });
    });

    System.out.println("numOfProvinces=" + provincesApiResponse.data().size());
    System.out.println("numOfDistricts=" + numOfDistricts);
    System.out.println("numOfWards=" + numOfWards);
  }

  private static void writeToFile(Path path, Object json) {
    try {
      new ObjectMapper().writeValue(Files.newBufferedWriter(path), json);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
