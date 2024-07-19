package uk.gov.hmcts.reform.opal.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.CreateFineAccountsRequest;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.CreateFineAccountsResponse;
import uk.gov.hmcts.reform.opal.dto.OpalS2SRequestWrapper;
import uk.gov.hmcts.reform.opal.dto.OpalS2SResponseWrapper;
import uk.gov.hmcts.reform.opal.util.XmlUtil;

@Service
@Slf4j(topic = "CreateFineAccountsService")
public class CreateFineAccountsService extends RestService{

    @Value("${s2s.endpoints.opal-fines-service}")
    private String CREATE_FINE_ACCOUNTS_ENDPOINT;

    @Value("${s2s.api.create-account}")
    private String CREATE_FINE_ACCOUNTS_API;

    public CreateFineAccountsService(RestClient restClient) {
        super(restClient);
    }

    @Override
    public Logger getLog() {
        return log;
    }

    public CreateFineAccountsResponse handleCreateFineAccountsRequest(CreateFineAccountsRequest request) {

        OpalS2SRequestWrapper opalS2SRequestWrapper = OpalS2SRequestWrapper.builder()
            .externalApiPayload(XmlUtil.marshalXmlString(request, CreateFineAccountsRequest.class ))
            .build();

        OpalS2SResponseWrapper opalS2SResponseWrapper = postToEndpoint(OpalS2SResponseWrapper.class,
                                                                                   opalS2SRequestWrapper,
                                                                                   CREATE_FINE_ACCOUNTS_ENDPOINT +
                                                                                       CREATE_FINE_ACCOUNTS_API
                                                                                   );
        if (opalS2SResponseWrapper.getErrorDetail() != null) {
            throw new RuntimeException(opalS2SResponseWrapper.getErrorDetail());
        }

        return XmlUtil.unmarshalXmlString(opalS2SResponseWrapper.getOpalResponsePayload(),
                                          CreateFineAccountsResponse.class);

    }
}
