package uk.gov.hmcts.reform.opal.dto;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.opal.util.ToJsonString;

@Data
@Builder
public class OpalS2SResponseWrapper implements ToJsonString {

    public String opalResponsePayload;
    public String errorDetail;
}
