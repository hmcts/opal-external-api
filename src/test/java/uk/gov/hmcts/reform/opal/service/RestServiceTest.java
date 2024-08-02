package uk.gov.hmcts.reform.opal.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.hmcts.reform.opal.dto.OpalS2SResponseWrapper;
import uk.gov.hmcts.reform.opal.exception.RestServiceResponseException;
import uk.gov.hmcts.reform.opal.util.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

class RestServiceTest extends RestServiceTestsBase {

    @Mock
    private Logger logger;

    private MockedStatic<JsonUtil> jsonUtilMock;

    @InjectMocks
    private RestService restService = new RestService(restClient) {
        @Override
        protected Logger getLog() {
            return logger;
        }
    };

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jsonUtilMock = mockStatic(JsonUtil.class);
    }

    @AfterEach
    void tearDownClass() {
        jsonUtilMock.close();
    }

    @Test
    void extractResponse_shouldReturnDeserializedObject_whenResponseIsSuccessful() {
        // Arrange
        ResponseEntity<String> responseEntity = new ResponseEntity<>(getJsonResponse(), HttpStatus.OK);
        Class<OpalS2SResponseWrapper> responseType = OpalS2SResponseWrapper.class;

        OpalS2SResponseWrapper expectedResponse = OpalS2SResponseWrapper.builder()
            .errorDetail("ERROR IN DATABASE")
            .build();

        jsonUtilMock.when(() -> JsonUtil.convertJsonToObject(getJsonResponse(), responseType))
            .thenReturn(expectedResponse);

        // Act
        OpalS2SResponseWrapper result = restService.extractResponse(responseEntity, responseType);

        // Assert
        assertNotNull(result);
        assertEquals("ERROR IN DATABASE", result.getErrorDetail());
    }

    @Test
    void extractResponse_shouldThrowException_whenResponseIsNot2xx() {
        // Arrange
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

        // Act & Assert
        RestServiceResponseException exception = assertThrows(RestServiceResponseException.class, () ->
            restService.extractResponse(responseEntity, OpalS2SResponseWrapper.class)
        );

        assertEquals("Received a non-2xx response from the rest endpoint: 400 BAD_REQUEST",
                     exception.getMessage());
    }

    @Test
    void extractResponse_shouldThrowException_whenResponseBodyIsNull() {
        // Arrange
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);

        // Act & Assert
        RestServiceResponseException exception = assertThrows(RestServiceResponseException.class, () ->
            restService.extractResponse(responseEntity, OpalS2SResponseWrapper.class)
        );

        assertEquals("Received an empty body in the response from the rest endpoint", exception.getMessage());
    }

    String getJsonResponse() {
        return "{\"opalResponsePayload\":null,\"errorDetail\":\"ERROR IN DATABASE\"}";
    }
}
