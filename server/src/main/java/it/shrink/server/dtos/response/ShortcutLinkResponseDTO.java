package it.shrink.server.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShortcutLinkResponseDTO {

  String redirectUrl;
}
