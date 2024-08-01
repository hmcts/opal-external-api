package uk.gov.hmcts.reform.opal.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.CreateFineAccountsRequest;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.CreateFineAccountsResponse;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.SearchRequestType;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.SearchResponseType;
import uk.gov.hmcts.reform.opal.service.CreateFineAccountsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CppSoapControllerTest {

    @Mock
    private CreateFineAccountsService createFineAccountsService;

    @InjectMocks
    private CppSoapController cppSoapController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFineAccounts() {
        CreateFineAccountsRequest request = new CreateFineAccountsRequest();
        CreateFineAccountsResponse response = new CreateFineAccountsResponse();

        when(createFineAccountsService.handleCreateFineAccountsRequest(any(CreateFineAccountsRequest.class)))
            .thenReturn(response);

        CreateFineAccountsResponse result = cppSoapController.createFineAccounts(request);

        verify(createFineAccountsService).handleCreateFineAccountsRequest(request);
        assertEquals(response, result);
    }

    @Test
    void testSearchType() {
        SearchRequestType request = new SearchRequestType();
        SearchResponseType expectedResponse = new SearchResponseType();
        expectedResponse.setErrorMessage("ERROR MESSAGE");

        SearchResponseType result = cppSoapController.searchType(request);

        assertEquals(expectedResponse.getErrorMessage(), result.getErrorMessage());
    }
}
