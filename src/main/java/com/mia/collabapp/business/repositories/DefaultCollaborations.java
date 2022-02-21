package com.mia.collabapp.business.repositories;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.mia.collabapp.business.entities.Collaboration;
import com.mia.collabapp.business.entities.Collaborator;
import com.mia.collabapp.firebase.FirebaseClient;
import com.mia.collabapp.firebase.QueryFilters;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultCollaborations implements Collaborations {

  private static String COLLECTION = "collaborations";

  private final FirebaseClient client;

  public DefaultCollaborations(FirebaseClient client) {
    this.client = client;
  }

  @Override
  public void createCollaboration(Collaboration c) throws ExecutionException {
    client.saveDoc(COLLECTION, c.getId(), c.toMap());
  }

  @Override
  public Collaboration getCollaborationByID(String id) throws ExecutionException, InterruptedException {
    return client.getDocsByQuery(COLLECTION, QueryFilters.EQUAL_TO, "id", id, Collaboration.class).get(0);
  }

  @Override
  public List<Collaboration> getCollaborationByCollaborator(String collaboratorId) throws ExecutionException, InterruptedException {
    return client.getDocsByQuery(COLLECTION, QueryFilters.EQUAL_TO, "collaboratorId", collaboratorId, Collaboration.class);
  }

  @Override
  public List<Collaboration> getCollaborationByOwner(String ownerId) throws ExecutionException, InterruptedException {
    return client.getDocsByQuery(COLLECTION, QueryFilters.EQUAL_TO, "ownerId", ownerId, Collaboration.class);
  }
}
