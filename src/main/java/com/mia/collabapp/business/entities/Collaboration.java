package com.mia.collabapp.business.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Collaboration {
  @JsonProperty("id")
  String id;

  @JsonProperty("owner_id")
  String ownerId;

  @JsonProperty("collaborator_id")
  String collaboratorId;

  public Map<String, Object> toMap(){
    Map<String, Object> result = new HashMap<>();

    result.put("id", id);
    result.put("ownerId", ownerId);
    result.put("collaboratorId", collaboratorId);


    return result;
  }
}
