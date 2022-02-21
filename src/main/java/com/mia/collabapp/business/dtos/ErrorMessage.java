package com.mia.collabapp.business.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
  @JsonProperty("message")
  String message;
}
