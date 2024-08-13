package uk.gov.hmcts.reform.opal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpalS2SResponseWrapper {

    @JsonProperty("opalResponsePayload")
    public String opalResponsePayload;

    @JsonProperty("errorDetail")
    public String errorDetail;
}
