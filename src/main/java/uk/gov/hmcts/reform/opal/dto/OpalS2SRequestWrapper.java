package uk.gov.hmcts.reform.opal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpalS2SRequestWrapper {

    private String externalApiPayload;
}
