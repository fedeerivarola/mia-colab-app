package com.mia.collabapp.business.repositories;

import com.mia.collabapp.business.entities.RequestCollaboration;
import com.mia.collabapp.business.entities.Collaborator;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Repository for Request Collaboration storage.
 */
public interface RequestsCollaboration {

  void createRequestCollaboration(RequestCollaboration rc) throws ExecutionException;

  RequestCollaboration getRequestCollaborationByID(String id) throws ExecutionException, InterruptedException;

  List<RequestCollaboration> getRequestCollaborationByOwner(String ownerId) throws ExecutionException, InterruptedException;
}
