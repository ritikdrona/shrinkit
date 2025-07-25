package it.shrink.server.services;

import com.fasterxml.jackson.core.type.TypeReference;
import it.shrink.server.dtos.LinkDTO;
import it.shrink.server.dtos.request.LinkCreationRequestDTO;
import it.shrink.server.dtos.response.DeletionResponseDTO;
import it.shrink.server.entities.Link;
import it.shrink.server.repositories.LinkRepository;
import it.shrink.server.utils.Converter;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkService {

  final LinkRepository linkRepository;

  public List<LinkDTO> getLinksByUserId(String userId) {
    List<Link> links = linkRepository.getLinksByUserId(userId);
    return Converter.convertCollection(links, new TypeReference<>() {});
  }

  public LinkDTO createLink(String userId, LinkCreationRequestDTO linkCreationRequestDTO) {
    Link newLink = new Link(userId, linkCreationRequestDTO.getUrl());

    // TODO: generate shortcut
    String shortcut = "shortcut";
    newLink.setShortcut(shortcut);

    linkRepository.save(newLink);

    return Converter.convert(newLink, new TypeReference<>() {});
  }

  public LinkDTO modifyLink(
      String userId, String linkId, LinkCreationRequestDTO linkCreationRequestDTO) {
    Optional<Link> existingLink = linkRepository.findById(linkId);

    if (existingLink.isEmpty()) {
      throw new RuntimeException("Link does not exist");
    }

    Link link = existingLink.get();
    link.setActualUrl(linkCreationRequestDTO.getUrl());

    linkRepository.save(link);

    return Converter.convert(link, new TypeReference<>() {});
  }

  public DeletionResponseDTO deleteLink(String userId, String linkId) {
    Optional<Link> existingLink = linkRepository.findById(linkId);

    if (existingLink.isEmpty()) {
      log.debug("Link does not exist with id: {}", linkId);
      return new DeletionResponseDTO(true);
    }

    if (userId.equals(existingLink.get().getUserId())) {
      linkRepository.delete(existingLink.get());
      return new DeletionResponseDTO(true);
    }

    // unauthorized deletion attempt
    return new DeletionResponseDTO(false);
  }
}
