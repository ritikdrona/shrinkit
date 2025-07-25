package it.shrink.server.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class Converter {

  static ObjectMapper objectMapper = new ObjectMapper();

  public static <T, R> R convert(T object, TypeReference<R> typeReference) {
    return objectMapper.convertValue(object, typeReference);
  }

  public static <T, R> List<R> convertCollection(
      List<T> collection, TypeReference<List<R>> typeReference) {
    return objectMapper.convertValue(collection, typeReference);
  }
}
