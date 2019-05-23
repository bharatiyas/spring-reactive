package com.skb.learn.spring.springreactive.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// @Document identifies the entity as a document to be persisted in MongoDB
@Document

// Lombok annotations
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Profile {

  public Profile(String id, String email) {
    this.id = id;
    this.email = email;
  }

  @Id //Identifies the document ID for this document
  private String id;

  private String email;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Profile{" +
            "id='" + id + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
