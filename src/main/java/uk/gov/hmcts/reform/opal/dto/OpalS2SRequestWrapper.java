package uk.gov.hmcts.reform.opal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpalS2SRequestWrapper {

    private String externalApiPayload;
}
