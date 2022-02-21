package com.mia.collabapp.business.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mia.collabapp.business.entities.RequestCollaboration;
import lombok.Data;

@Data
public class PostRequestCollaboration {

  @JsonProperty("owner_id")
  String ownerId;

  @JsonProperty("associated_collab_id")
  String collaborationId;
}
