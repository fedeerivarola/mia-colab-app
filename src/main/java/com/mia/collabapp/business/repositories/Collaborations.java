package com.mia.collabapp.business.repositories;

import com.mia.collabapp.business.entities.Collaboration;
import com.mia.collabapp.business.entities.Collaborator;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Repository for Collaboration's storage
 */
public interface Collaborations {

  void createCollaboration(Collaboration c) throws ExecutionException;

  Collaboration getCollaborationByID(String id) throws ExecutionException, InterruptedException;

  List<Collaboration> getCollaborationByCollaborator(String collaboratorId) throws ExecutionException, InterruptedException;

  List<Collaboration> getCollaborationByOwner(String ownerId) throws ExecutionException, InterruptedException;
}
