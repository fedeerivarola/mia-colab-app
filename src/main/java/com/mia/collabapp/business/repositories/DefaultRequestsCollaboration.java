package com.mia.collabapp.business.repositories;

import com.mia.collabapp.business.entities.Collaborator;
import com.mia.collabapp.business.entities.RequestCollaboration;
import com.mia.collabapp.firebase.FirebaseClient;
import com.mia.collabapp.firebase.QueryFilters;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultRequestsCollaboration implements RequestsCollaboration {

  private static String COLLECTION = "requests-collaboration";

  private final FirebaseClient client;

  public DefaultRequestsCollaboration(FirebaseClient client) {
    this.client = client;
  }

  @Override
  public void createRequestCollaboration(RequestCollaboration rc) throws ExecutionException {
    client.saveDoc(COLLECTION, rc.getId(), rc.toMap());
  }

  @Override
  public RequestCollaboration getRequestCollaborationByID(String id) throws ExecutionException, InterruptedException {
    return client.getDocsByQuery(COLLECTION, QueryFilters.EQUAL_TO, "id", id,  RequestCollaboration.class).get(0);
  }

  @Override
  public List<RequestCollaboration> getRequestCollaborationByOwner(String ownerId) throws ExecutionException, InterruptedException {
    return client.getDocsByQuery(COLLECTION, QueryFilters.EQUAL_TO, "ownerId", ownerId, RequestCollaboration.class);
  }
}
