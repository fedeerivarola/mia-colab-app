package com.mia.collabapp.business.services;

import com.mia.collabapp.business.entities.Collaborator;
import com.mia.collabapp.business.entities.RequestCollaboration;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GetRequestCollaborations {

  RequestCollaboration getRequestCollaborationByID(String id);

  List<RequestCollaboration> getRequestCollaborationByOwner(String ownerId) throws ExecutionException, InterruptedException;
}
