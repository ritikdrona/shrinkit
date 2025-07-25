package it.shrink.server.dtos;

import lombok.Data;

@Data
public class LinkDTO {

  String id;

  String shortUrl;

  String actualUrl;
}
