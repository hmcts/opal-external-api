package uk.gov.hmcts.reform.opal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpalS2SResponseWrapper {

    @JsonProperty("opalResponsePayload")
    public String opalResponsePayload;

    @JsonProperty("errorDetail")
    public String errorDetail;
}
