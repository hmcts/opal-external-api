package uk.gov.hmcts.reform.opal.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.CreateFineAccountsRequest;
import uk.gov.hmcts.reform.opal.CPPSoapGateway.CreateFineAccountsResponse;
import uk.gov.hmcts.reform.opal.dto.OpalS2SRequestWrapper;
import uk.gov.hmcts.reform.opal.dto.OpalS2SResponseWrapper;
import uk.gov.hmcts.reform.opal.util.JsonUtil;
import uk.gov.hmcts.reform.opal.util.XmlUtil;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CreateFineAccountsServiceTest extends RestServiceTestsBase {

    @InjectMocks
    private CreateFineAccountsService createFineAccountsService;

    @Mock
    private Logger logger;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(createFineAccountsService, "createFineAccountsEndpoint", "http://example.com");
        ReflectionTestUtils.setField(createFineAccountsService, "createFineAccountsApi", "/api/create-account");
        mockRestClientPost();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testHandleCreateFineAccountsRequest_Success() {
        // Arrange
        CreateFineAccountsRequest request = new CreateFineAccountsRequest();
        OpalS2SRequestWrapper requestWrapper = OpalS2SRequestWrapper.builder()
            .externalApiPayload(XmlUtil.marshalXmlString(request, CreateFineAccountsRequest.class))
            .build();

        OpalS2SResponseWrapper responseWrapper = new OpalS2SResponseWrapper();
        CreateFineAccountsResponse createFineAccountResponse = new CreateFineAccountsResponse();
        createFineAccountResponse.setNumberOfFineAccounts(BigInteger.valueOf(1));
        responseWrapper.setOpalResponsePayload(XmlUtil.marshalXmlString(createFineAccountResponse, CreateFineAccountsResponse.class));

        String responseString = JsonUtil.convertObjectToJson(responseWrapper);

        ResponseEntity<String> successfulResponseEntity = new ResponseEntity<>(responseString, HttpStatus.OK);
        when(requestBodySpec.header(anyString(), anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(OpalS2SRequestWrapper.class))).thenReturn(requestBodySpec);
        when(responseSpec.toEntity(any(Class.class))).thenReturn(successfulResponseEntity);

        // Act
        CreateFineAccountsResponse response = createFineAccountsService.handleCreateFineAccountsRequest(request);

        // Assert
        assertEquals(createFineAccountResponse.getNumberOfFineAccounts(), response.getNumberOfFineAccounts());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testHandleCreateFineAccountsRequest_ErrorResponse() {
        // Arrange
        CreateFineAccountsRequest request = new CreateFineAccountsRequest();
        OpalS2SRequestWrapper requestWrapper = OpalS2SRequestWrapper.builder()
            .externalApiPayload(XmlUtil.marshalXmlString(request, CreateFineAccountsRequest.class))
            .build();

        OpalS2SResponseWrapper responseWrapper = new OpalS2SResponseWrapper();
        responseWrapper.setErrorDetail("Error detail");

        String responseString = JsonUtil.convertObjectToJson(responseWrapper);

        ResponseEntity<String> successfulResponseEntity = new ResponseEntity<>(responseString, HttpStatus.OK);
        when(requestBodySpec.header(anyString(), anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(OpalS2SRequestWrapper.class))).thenReturn(requestBodySpec);
        when(responseSpec.toEntity(any(Class.class))).thenReturn(successfulResponseEntity);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            createFineAccountsService.handleCreateFineAccountsRequest(request);
        });

        assertEquals("Error detail", exception.getMessage());
    }
}

