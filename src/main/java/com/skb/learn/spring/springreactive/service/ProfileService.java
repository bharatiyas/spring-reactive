package com.skb.learn.spring.springreactive.service;

import com.skb.learn.spring.springreactive.entity.Profile;
import com.skb.learn.spring.springreactive.event.ProfileCreatedEvent;
import com.skb.learn.spring.springreactive.repository.ProfileRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service class will contain any course grained business logic. In the beginning a lot of the business logic will be
 * passed through logic delegating to the repository, but we can add things like validation and integration with other
 * systems at this layer.
 */
@Service
public class ProfileService {

  // We want to publish events to other beans managed in the Spring ApplicationContext.
  // Earlier, we defined an ApplicationListener<ApplicationReadyEvent> that listened for an event that was published in
  // the ApplicationContext. Now, we’re going to publish an event for consumption of other beans of our devices in the
  // ApplicationContext.
  private final ApplicationEventPublisher publisher;


  private final ProfileRepository profileRepository;

  public ProfileService(ApplicationEventPublisher publisher, ProfileRepository profileRepository) {
    this.publisher = publisher;
    this.profileRepository = profileRepository;
  }

  // ​find all documents
  public Flux<Profile> all() {
    return this.profileRepository.findAll();
  }

  // ​find a document by its ID
  public Mono<Profile> get(String id) {
    return this.profileRepository.findById(id);
  }

  // update a Profile and give it a new email
  public Mono<Profile> update(String id, String email) {
    return this.profileRepository
            .findById(id)
            .map(p -> new Profile(p.getId(), email))
            .flatMap(this.profileRepository::save);
  }

  // ​delete a record by its id
  public Mono<Profile> delete(String id) {
    return this.profileRepository
            .findById(id)
            .flatMap(p -> this.profileRepository.deleteById(p.getId()).thenReturn(p));
  }

  // create a new Profile in the database and publish an ApplicationContextEvent, one of our own creation called
  // ProfileCreatedEvent, on successful write to the database.
  public Mono<Profile> create(String email) {
    return this.profileRepository
            .save(new Profile(null, email))
            // The doOnSuccess callback takes a Consumer<T> that gets invoked after the data in the reactive pipeline
            // has been written to the database
            .doOnSuccess(profile -> this.publisher.publishEvent(new ProfileCreatedEvent(profile)));
  }

}
