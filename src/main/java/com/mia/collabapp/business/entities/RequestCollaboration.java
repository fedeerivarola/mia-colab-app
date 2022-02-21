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
public class RequestCollaboration {
  @JsonProperty("id")
  String id;

  @JsonProperty("owner_id")
  String ownerId;

  @JsonProperty("status")
  Status status;

  @JsonProperty("assigned")
  Boolean assigned;

  @JsonProperty("associated_collab_id")
  String collaborationId;

  public Map<String, Object> toMap() {
    Map<String, Object> result = new HashMap<>();

    result.put("id", id);
    result.put("ownerId", ownerId);
    result.put("status", status);
    result.put("assigned", assigned);
    result.put("collaborationId", collaborationId);


    return result;
  }

  public enum Status {
    PENDING("pending"),
    ASSIGNED("assigned"),
    FINISHED("finished");

    String value;

    Status(String status) {
      value = status;
    }

    String getValue() {
      return value;
    }
  }
}
