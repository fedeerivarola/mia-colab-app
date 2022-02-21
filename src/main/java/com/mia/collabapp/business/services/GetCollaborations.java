package com.mia.collabapp.business.services;

import com.mia.collabapp.business.entities.Collaboration;
import com.mia.collabapp.business.entities.Collaborator;
import java.util.List;

public interface GetCollaborations {

  Collaboration getCollaborationByID(String id);

  List<Collaboration> getCollaborationByCollaborator(Collaborator collaborator);

  List<Collaboration> getCollaborationByOwner(Collaborator owner);
}
