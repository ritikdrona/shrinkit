package it.shrink.server.controllers;

import it.shrink.server.dtos.LinkDTO;
import it.shrink.server.dtos.request.ApiSingleRequest;
import it.shrink.server.dtos.request.LinkCreationRequestDTO;
import it.shrink.server.dtos.response.ApiResponse;
import it.shrink.server.dtos.response.ApiSingleResponse;
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
  public ResponseEntity<ApiResponse<LinkDTO>> getLinks() {
    List<LinkDTO> links = linkService.getLinksByUserId("userId");
    return new ResponseEntity<>(new ApiResponse<>(links), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ApiSingleResponse<LinkDTO>> createLink(
      @RequestBody ApiSingleRequest<LinkCreationRequestDTO> linkCreationRequestDTO) {
    LinkDTO link = linkService.createLink("userId", linkCreationRequestDTO.getRequest());
    return new ResponseEntity<>(new ApiSingleResponse<>(link), HttpStatus.OK);
  }

  @PutMapping("/{linkId}")
  public ResponseEntity<ApiSingleResponse<LinkDTO>> modifyLink(
      @PathVariable String linkId,
      @RequestBody ApiSingleRequest<LinkCreationRequestDTO> linkCreationRequestDTO) {
    LinkDTO link = linkService.modifyLink("userId", linkId, linkCreationRequestDTO.getRequest());
    return new ResponseEntity<>(new ApiSingleResponse<>(link), HttpStatus.OK);
  }

  @DeleteMapping("/{linkId}")
  public ResponseEntity<ApiSingleResponse<DeletionResponseDTO>> deleteLink(
      @PathVariable String linkId) {
    DeletionResponseDTO deletionResponseDTO = linkService.deleteLink("userId", linkId);
    return new ResponseEntity<>(new ApiSingleResponse<>(deletionResponseDTO), HttpStatus.OK);
  }
}
