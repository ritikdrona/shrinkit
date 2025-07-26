package it.shrink.server.repositories;

import it.shrink.server.entities.Link;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, String> {

  List<Link> getLinksByUserId(String userId);

  Optional<Link> getLinkByShortcut(String shortcut);
}
