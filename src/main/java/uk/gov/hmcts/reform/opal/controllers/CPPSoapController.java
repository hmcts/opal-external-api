package uk.gov.hmcts.reform.opal.controllers;

import jakarta.jws.WebService;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.CreateFineAccountsRequest;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.CreateFineAccountsResponse;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.ICppGatewayType;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.SearchRequestType;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.SearchResponseType;

@Service
@WebService(serviceName = "CppGatewayService", portName = "CppGatewayPort", targetNamespace = "http://www.justice.gov.uk/magistrates/atcm/CPPSoapGateway", endpointInterface = "uk.gov.hmcts.reform.opal.CPPSoapGateway.ICppGatewayType")
public class CPPSoapController implements ICppGatewayType {

    @Override
    public CreateFineAccountsResponse createFineAccounts(CreateFineAccountsRequest request) {
       //TODO: Implement this method
        CreateFineAccountsResponse response = new CreateFineAccountsResponse();
        response.setErrorMessage("FAKE ERROR MESSAGE");
       return response;
   }

   @Override
    public SearchResponseType searchType(SearchRequestType request) {
        //TODO Implement this method
       SearchResponseType response = new SearchResponseType();
       response.setErrorMessage("FAKE ERROR MESSAGE 2");
       return response;
    }
}
