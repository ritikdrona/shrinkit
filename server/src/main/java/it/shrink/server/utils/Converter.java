package it.shrink.server.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Converter {

  final ObjectMapper objectMapper;

  public <T, R> R convert(T object, TypeReference<R> typeReference) {
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    return objectMapper.convertValue(object, typeReference);
  }

  public <T, R> List<R> convertCollection(
      List<T> collection, TypeReference<List<R>> typeReference) {
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    return objectMapper.convertValue(collection, typeReference);
  }
}
