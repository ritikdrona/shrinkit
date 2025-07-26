package it.shrink.server.dtos.request;

import java.util.List;
import lombok.Data;

@Data
public class ApiRequest<T> {
  List<T> request;
}
