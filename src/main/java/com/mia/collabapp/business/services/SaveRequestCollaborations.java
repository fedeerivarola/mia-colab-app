package com.mia.collabapp.business.services;

import java.util.concurrent.ExecutionException;
import lombok.Builder;
import lombok.Getter;

public interface SaveRequestCollaborations {

  Boolean execute(Model model) throws IllegalArgumentException, ExecutionException;

  @Builder
  @Getter
  class Model {
    String ownerId;

    String collaborationId;
  }
}
