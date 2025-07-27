package it.shrink.server.services;

import com.fasterxml.jackson.core.type.TypeReference;
import it.shrink.server.dtos.LinkDTO;
import it.shrink.server.dtos.request.LinkCreationRequestDTO;
import it.shrink.server.dtos.response.DeletionResponseDTO;
import it.shrink.server.dtos.response.ShortcutLinkResponseDTO;
import it.shrink.server.entities.Link;
import it.shrink.server.repositories.LinkRepository;
import it.shrink.server.utils.Converter;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkService {

  final LinkRepository linkRepository;

  final Converter converter;

  @Value("${link.shortcut.length:8}")
  int shortcutLength;

  public ShortcutLinkResponseDTO getLinksByShortcut(String shortcut) {
    Optional<Link> link = linkRepository.getLinkByShortcut(shortcut);
    if (link.isEmpty()) {
      throw new RuntimeException("Link does not exist");
    }
    return new ShortcutLinkResponseDTO(link.get().getActualUrl());
  }

  public List<LinkDTO> getLinksByUserId(String userId) {
    List<Link> links = linkRepository.getLinksByUserId(userId);
    return converter.convertCollection(links, new TypeReference<>() {});
  }

  public LinkDTO createLink(String userId, LinkCreationRequestDTO linkCreationRequestDTO) {
    Link newLink = new Link(userId, linkCreationRequestDTO.getUrl());
    newLink.setShortcut(generateValidShortcut());

    linkRepository.save(newLink);

    return converter.convert(newLink, new TypeReference<>() {});
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

    return converter.convert(link, new TypeReference<>() {});
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

  public String generateValidShortcut() {
    log.debug("Generating a valid shortcut of length: {}", shortcutLength);

    for (int i = 0; i < 10; i++) {
      String shortcut = generateRandomString(shortcutLength);
      Optional<Link> existingLink = linkRepository.getLinkByShortcut(shortcut);
      if (existingLink.isEmpty()) {
        return shortcut;
      }
    }

    throw new RuntimeException(
        "Unable to generate a valid shortcut after 10 attempts. Please try again.");
  }

  private String generateRandomString(int length) {
    String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder result = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int index = (int) (Math.random() * characters.length());
      result.append(characters.charAt(index));
    }

    return result.toString();
  }
}
