package com.mia.collabapp.business.services;

import static com.mia.collabapp.utils.constants.ErrorMessages.INVALID_OWNER_PARAM;

import com.mia.collabapp.business.entities.RequestCollaboration;
import com.mia.collabapp.business.repositories.RequestsCollaboration;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DefaultSaveRequestCollaborations implements SaveRequestCollaborations {

  private static String REQUEST_COLLAB_ID_REGEX = "collab-%s-owner-%s";

  private final RequestsCollaboration repository;

  public DefaultSaveRequestCollaborations(RequestsCollaboration repository) {
    this.repository = repository;
  }

  @Override
  public Boolean execute(SaveRequestCollaborations.Model model) throws IllegalArgumentException, ExecutionException {
    if (Objects.isNull(model.getCollaborationId())
        || StringUtils.containsWhitespace(model.getCollaborationId())) {
      throw new IllegalArgumentException(INVALID_OWNER_PARAM);
    }

    if (Objects.isNull(model.getOwnerId())
        || StringUtils.containsWhitespace(model.getOwnerId())) {
      throw new IllegalArgumentException(INVALID_OWNER_PARAM);
    }

    RequestCollaboration rc = RequestCollaboration.builder()
        .collaborationId(model.getCollaborationId())
        .ownerId(model.getOwnerId())
        .assigned(false)
        .status(RequestCollaboration.Status.PENDING)
        .id(generateId(model.getCollaborationId(), model.getOwnerId()))
        .build();

    try {
      repository.createRequestCollaboration(rc);
    } catch (ExecutionException e) {
      e.printStackTrace();
      throw e;
    }

    return true;
  }

  private String generateId(String collabId, String ownerId) {
    String key = String.format(REQUEST_COLLAB_ID_REGEX, collabId, ownerId);
    return Base64.getEncoder().encode(key.getBytes(StandardCharsets.UTF_8)).toString();
  }

  @Builder
  static class Model {
    String ownerId;

    String collaborationId;
  }
}
