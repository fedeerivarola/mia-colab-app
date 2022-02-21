package com.mia.collabapp.business.services;

import com.mia.collabapp.business.entities.Collaboration;
import com.mia.collabapp.business.repositories.Collaborations;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
public class DefaultSaveCollaborations implements SaveCollaborations {

  Collaborations repository;

  public DefaultSaveCollaborations(Collaborations repository) {
    this.repository = repository;
  }

  @Override
  public Boolean execute(Collaboration c) {
    try{
      repository.createCollaboration(c);
    } catch (Exception e){
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
