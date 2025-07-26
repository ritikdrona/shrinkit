package it.shrink.server.dtos.request;

import lombok.Data;

@Data
public class ApiSingleRequest<T> {
  T request;
}
