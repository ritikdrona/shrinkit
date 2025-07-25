package it.shrink.server.controllers;

import it.shrink.server.dtos.LinkDTO;
import it.shrink.server.dtos.request.LinkCreationRequestDTO;
import it.shrink.server.dtos.response.DeletionResponseDTO;
import it.shrink.server.services.LinkService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "links")
@RequiredArgsConstructor
public class LinkController {

  final LinkService linkService;

  @GetMapping
  public ResponseEntity<List<LinkDTO>> getLink() {
    List<LinkDTO> links = linkService.getLinksByUserId("userId");
    return new ResponseEntity<>(links, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<LinkDTO> createLink(
      @RequestBody LinkCreationRequestDTO linkCreationRequestDTO) {
    LinkDTO link = linkService.createLink("userId", linkCreationRequestDTO);
    return new ResponseEntity<>(link, HttpStatus.OK);
  }

  @PutMapping("/{linkId}")
  public ResponseEntity<LinkDTO> modifyLink(
      @PathVariable String linkId, @RequestBody LinkCreationRequestDTO linkCreationRequestDTO) {
    LinkDTO link = linkService.modifyLink("userId", linkId, linkCreationRequestDTO);
    return new ResponseEntity<>(link, HttpStatus.OK);
  }

  @DeleteMapping("/{linkId}")
  public ResponseEntity<DeletionResponseDTO> deleteLink(@PathVariable String linkId) {
    DeletionResponseDTO deletionResponseDTO = linkService.deleteLink("userId", linkId);
    return new ResponseEntity<>(deletionResponseDTO, HttpStatus.OK);
  }
}
