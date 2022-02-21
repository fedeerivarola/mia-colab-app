package com.mia.collabapp.business.services;

import static com.mia.collabapp.utils.constants.ErrorMessages.INVALID_OWNER_PARAM;

import com.mia.collabapp.business.entities.Collaborator;
import com.mia.collabapp.business.entities.RequestCollaboration;
import com.mia.collabapp.business.repositories.RequestsCollaboration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DefaultGetRequestCollaborations implements GetRequestCollaborations{

  private final RequestsCollaboration repository;

  public DefaultGetRequestCollaborations(RequestsCollaboration repository) {
    this.repository = repository;
  }

  @Override
  public RequestCollaboration getRequestCollaborationByID(String id) {
    return null;
  }

  @Override
  public List<RequestCollaboration> getRequestCollaborationByOwner(String ownerId) throws ExecutionException, InterruptedException {
    if(Objects.isNull(ownerId)
    || StringUtils.containsWhitespace(ownerId)){
      throw new IllegalArgumentException(INVALID_OWNER_PARAM);
    }

    return repository.getRequestCollaborationByOwner(ownerId);
  }
}
