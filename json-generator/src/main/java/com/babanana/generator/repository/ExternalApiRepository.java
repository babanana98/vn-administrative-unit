package com.babanana.generator.repository;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

public class ExternalApiRepository {

  public <T> T get(String url, Map<String, String> headers, Map<String, String> queryParam, Class<T> responseType) {
    HttpHeaders httpHeaders = new HttpHeaders();
    headers.forEach(httpHeaders::add);

    HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

    URI uri = buildUri(url, queryParam);

    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.exchange(uri, HttpMethod.GET, entity, responseType)
        .getBody();
  }

  private URI buildUri(String url, Map<String, String> queryParam) {
    if (Objects.isNull(queryParam)) {
      return URI.create(url);
    }
    var uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
    queryParam.forEach(uriComponentsBuilder::queryParam);
    return uriComponentsBuilder.build().toUri();
  }
}
