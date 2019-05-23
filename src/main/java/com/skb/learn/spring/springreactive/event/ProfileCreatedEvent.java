package com.skb.learn.spring.springreactive.event;

import com.skb.learn.spring.springreactive.entity.Profile;
import org.springframework.context.ApplicationEvent;

public class ProfileCreatedEvent extends ApplicationEvent {

  public ProfileCreatedEvent(Profile source) {
    super(source);
  }
}
