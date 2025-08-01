package it.shrink.server.controllers;

import it.shrink.server.dtos.LinkDTO;
import it.shrink.server.dtos.request.ApiSingleRequest;
import it.shrink.server.dtos.request.LinkCreationRequestDTO;
import it.shrink.server.dtos.response.ApiResponse;
import it.shrink.server.dtos.response.ApiSingleResponse;
import it.shrink.server.dtos.response.DeletionResponseDTO;
import it.shrink.server.dtos.response.ShortcutLinkResponseDTO;
import it.shrink.server.entities.User;
import it.shrink.server.services.LinkService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "links")
@RequiredArgsConstructor
public class LinkController {

  final LinkService linkService;

  @GetMapping
  public ResponseEntity<ApiResponse<LinkDTO>> getLinks(@AuthenticationPrincipal User user) {
    List<LinkDTO> links = linkService.getLinksByUserId(user.getId());
    return new ResponseEntity<>(new ApiResponse<>(links), HttpStatus.OK);
  }

  @GetMapping("/shortcut/{shortcut}")
  public ResponseEntity<ApiSingleResponse<ShortcutLinkResponseDTO>> getLinkByShortcut(
      @PathVariable String shortcut) {
    ShortcutLinkResponseDTO response = linkService.getLinksByShortcut(shortcut);
    return new ResponseEntity<>(new ApiSingleResponse<>(response), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ApiSingleResponse<LinkDTO>> createLink(
      @RequestBody ApiSingleRequest<LinkCreationRequestDTO> linkCreationRequestDTO,
      @AuthenticationPrincipal User user) {
    LinkDTO link = linkService.createLink(user.getId(), linkCreationRequestDTO.getRequest());
    return new ResponseEntity<>(new ApiSingleResponse<>(link), HttpStatus.OK);
  }

  @PutMapping("/{linkId}")
  public ResponseEntity<ApiSingleResponse<LinkDTO>> modifyLink(
      @PathVariable String linkId,
      @RequestBody ApiSingleRequest<LinkCreationRequestDTO> linkCreationRequestDTO,
      @AuthenticationPrincipal User user) {
    LinkDTO link =
        linkService.modifyLink(user.getId(), linkId, linkCreationRequestDTO.getRequest());
    return new ResponseEntity<>(new ApiSingleResponse<>(link), HttpStatus.OK);
  }

  @DeleteMapping("/{linkId}")
  public ResponseEntity<ApiSingleResponse<DeletionResponseDTO>> deleteLink(
      @PathVariable String linkId, @AuthenticationPrincipal User user) {
    DeletionResponseDTO deletionResponseDTO = linkService.deleteLink(user.getId(), linkId);
    return new ResponseEntity<>(new ApiSingleResponse<>(deletionResponseDTO), HttpStatus.OK);
  }
}
