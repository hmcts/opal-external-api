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
public class CreateFineAccountsService extends RestService {

    @Value("${s2s.endpoints.opal-fines-service}")
    private String createFineAccountsEndpoint;

    @Value("${s2s.api.create-account}")
    private String createFineAccountsApi;

    public CreateFineAccountsService(RestClient restClient) {
        super(restClient);
    }

    @Override
    public Logger getLog() {
        return log;
    }

    public CreateFineAccountsResponse handleCreateFineAccountsRequest(CreateFineAccountsRequest request) {

        log.info("Preparing request to create fine account(s)");
        OpalS2SRequestWrapper opalS2SRequestWrapper = OpalS2SRequestWrapper.builder()
            .externalApiPayload(XmlUtil.marshalXmlString(request, CreateFineAccountsRequest.class))
            .build();
        log.info("Request prepared");
        log.debug("Request: {}", opalS2SRequestWrapper.getExternalApiPayload());

        log.info("Calling back-end service to create fine account(s)");
        OpalS2SResponseWrapper opalS2SResponseWrapper = postToEndpoint(OpalS2SResponseWrapper.class,
                                                                       opalS2SRequestWrapper,
                                                                       createFineAccountsEndpoint +
                                                                           createFineAccountsApi
        );
        if (opalS2SResponseWrapper.getErrorDetail() != null) {
            log.error("Error response from back-end service: {}", opalS2SResponseWrapper.getErrorDetail());
            throw new RuntimeException(opalS2SResponseWrapper.getErrorDetail());
        }

        log.info("Successful response received from back-end service");
        log.debug("Response: {}", opalS2SResponseWrapper.getOpalResponsePayload());
        return XmlUtil.unmarshalXmlString(opalS2SResponseWrapper.getOpalResponsePayload(),
                                          CreateFineAccountsResponse.class);

    }
}
