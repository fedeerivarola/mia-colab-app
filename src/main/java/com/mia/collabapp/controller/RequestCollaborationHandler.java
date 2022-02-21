package com.mia.collabapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mia.collabapp.business.dtos.ErrorMessage;
import com.mia.collabapp.business.dtos.GetRequestCollaboration;
import com.mia.collabapp.business.dtos.PostRequestCollaboration;
import com.mia.collabapp.business.entities.RequestCollaboration;
import com.mia.collabapp.business.services.GetRequestCollaborations;
import com.mia.collabapp.business.services.SaveRequestCollaborations;
import com.mia.collabapp.utils.JsonUtils;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/mia/blinded-experiment/request")
public class RequestCollaborationHandler {

  private final SaveRequestCollaborations saveService;

  private final GetRequestCollaborations getService;

  private final ObjectMapper mapper = new ObjectMapper();

  public RequestCollaborationHandler(SaveRequestCollaborations saveService,
                                     GetRequestCollaborations getService) {
    this.saveService = saveService;
    this.getService = getService;
  }

  @PostMapping
  Object postRequestCollaboration(@RequestBody PostRequestCollaboration postRequestCollaboration) {
    Boolean result;
    try {
      result = saveService.execute(SaveRequestCollaborations.Model.builder()
          .collaborationId(postRequestCollaboration.getCollaborationId())
          .ownerId(postRequestCollaboration.getOwnerId())
          .build());
    } catch (IllegalArgumentException iae) {
      return ResponseEntity
          .badRequest()
          .body(JsonUtils.getJsonFromObject(ErrorMessage.builder().message(iae.getMessage()).build()));
    } catch (ExecutionException ee) {
      return ResponseEntity
          .internalServerError()
          .body(JsonUtils.getJsonFromObject(ErrorMessage.builder().message(ee.getMessage()).build()));
    }

    if (!result) {
      return ResponseEntity.accepted().build();
    }

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping()
  Object getRequestCollaborationByOwner(@RequestParam("owner.id") String ownerId) {
    List<RequestCollaboration> result;
    try {
      result = getService.getRequestCollaborationByOwner(ownerId);
    } catch (IllegalArgumentException iae) {
      return ResponseEntity.badRequest().body(JsonUtils.getJsonFromObject(ErrorMessage.builder().message(iae.getMessage()).build()));
    } catch (ExecutionException e) {
      return ResponseEntity.badRequest().body(JsonUtils.getJsonFromObject(ErrorMessage.builder().message(e.getMessage()).build()));
    } catch (InterruptedException e) {
      return ResponseEntity.badRequest().body(JsonUtils.getJsonFromObject(ErrorMessage.builder().message(e.getMessage()).build()));
    }

    return ResponseEntity.ok(GetRequestCollaboration.builder().requests(result).build());
  }
}
