package com.mia.collabapp.business.dtos;

import com.mia.collabapp.business.entities.RequestCollaboration;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRequestCollaboration {
  List<RequestCollaboration> requests;
}
