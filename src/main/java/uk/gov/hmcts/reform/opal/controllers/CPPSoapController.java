package uk.gov.hmcts.reform.opal.controllers;

import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.CreateFineAccountsRequest;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.CreateFineAccountsResponse;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.ICppGatewayType;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.SearchRequestType;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.SearchResponseType;
import uk.gov.hmcts.reform.opal.service.CreateFineAccountsService;

@Service
@WebService(serviceName = "CppGatewayService", portName = "CppGatewayPort", targetNamespace = "http://www.justice.gov.uk/magistrates/atcm/CPPSoapGateway", endpointInterface = "uk.gov.hmcts.reform.opal.CPPSoapGateway.ICppGatewayType")
@RequiredArgsConstructor
public class CPPSoapController implements ICppGatewayType {

    private final CreateFineAccountsService createFineAccountsService;

    @Override
    public CreateFineAccountsResponse createFineAccounts(CreateFineAccountsRequest request) {
       return createFineAccountsService.handleCreateFineAccountsRequestStub(request);
   }

   @Override
    public SearchResponseType searchType(SearchRequestType request) {
        //not yet implemented
       SearchResponseType response = new SearchResponseType();
       response.setErrorMessage("FAKE ERROR MESSAGE 2");
       return response;
    }
}
