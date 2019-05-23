package com.skb.learn.spring.springreactive.repository;


import com.skb.learn.spring.springreactive.entity.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

// In order to persist documents of type Profile, we declaratively define a repository.
// Repositories are responsible for persisting entities and value types.
// A repository, a design pattern from Eric Evans' seminal tome, Domain Driven Design, is a way of encapsulating
// object persistence.

// Advantages of using the Repository patter:
// 1. They present clients with a simple model for obtaining persistent objects and managing their life cycle.
// 2. They decouple application and domain design from persistence technology and strategy choices.
// 3. They allow easy substitution of implementation with a dummy implementation, ideal in testing.

// Spring Data’s repositories support all these goals with interface definitions whose implementations are created by
// the framework at startup time.

// ReactiveMongoRepository interface which in turn provides a number of data access methods supporting reads, writes,
// deletes and searches

// Spring Data will create an object that implements all these methods. It will provide an object for us that we can
// inject into into other objects to handle persistence.

// Spring Data repositories also supports custom queries. We could, for example, define a custom finder method, of
// the form Flux<Profile> findByEmail(String email). If you define custom queries, then this might be an appropriate
// thing to test.

public interface ProfileRepository extends ReactiveMongoRepository<Profile, String> {
}
