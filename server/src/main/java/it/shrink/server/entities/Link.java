package it.shrink.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "links")
@NoArgsConstructor
public class Link {

  @Id
  @GeneratedValue(generator = "uuid")
  @UuidGenerator
  String id;

  String userId;

  String actualUrl;

  String shortcut;

  public Link(String userId, String actualUrl) {
    this.userId = userId;
    this.actualUrl = actualUrl;
  }
}
