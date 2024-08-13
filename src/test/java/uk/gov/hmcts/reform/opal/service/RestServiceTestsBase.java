package uk.gov.hmcts.reform.opal.service;

import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.web.client.RestClient.RequestBodySpec;
import static org.springframework.web.client.RestClient.RequestBodyUriSpec;
import static org.springframework.web.client.RestClient.RequestHeadersSpec;
import static org.springframework.web.client.RestClient.RequestHeadersUriSpec;
import static org.springframework.web.client.RestClient.ResponseSpec;

public abstract class RestServiceTestsBase {

    @Mock
    RestClient restClient;

    @Mock
    RequestHeadersUriSpec requestHeaderUriSpec;

    @Mock
    RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    RequestHeadersSpec requestHeaderSpec;

    @Mock
    RequestBodySpec requestBodySpec;

    @Mock
    ResponseSpec responseSpec;

    @SuppressWarnings("unchecked")
    void mockRestClientPost() {
        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.contentType(any(MediaType.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
    }
}
