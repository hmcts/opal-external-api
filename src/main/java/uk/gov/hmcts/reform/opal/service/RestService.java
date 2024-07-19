package uk.gov.hmcts.reform.opal.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import uk.gov.hmcts.reform.opal.exception.RestServiceResponseException;
import uk.gov.hmcts.reform.opal.util.JsonUtil;

@Component
@RequiredArgsConstructor
public abstract class RestService {

    protected final RestClient restClient;

    protected abstract Logger getLog();

    @SuppressWarnings("unchecked")
    public <T> T extractResponse(ResponseEntity<String> responseEntity, Class<T> clzz) {
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            if (responseEntity.getBody() != null) {

                String body = responseEntity.getBody();
                getLog().info("extractResponse: response body: {}", body);

                try {
                    //json serialization
                    return (T) JsonUtil.convertJsonToObject(body, clzz);

                } catch (Exception e) {
                    getLog().error("extractResponse: Error deserializing response: {}", e.getMessage(), e);
                    throw new RestServiceResponseException(e);
                }
            } else {
                String msg = "Received an empty body in the response from the rest endpoint";
                getLog().warn("extractResponse: {}", msg);
                throw new RestServiceResponseException(msg);
            }
        } else {
            String msg = MessageFormatter.format(
                "Received a non-2xx response from the rest endpoint: {}",
                responseEntity.getStatusCode()
            ).getMessage();
            getLog().warn("extractResponse: {}", msg);
            throw new RestServiceResponseException(msg);
        }
    }

    public <T> T postToEndpoint(Class<T> responseType, Object request, String url) {
        getLog().info("postToEndpoint: POST to endpoint: {}", url);

        ResponseEntity<String> responseEntity = restClient.post()
            .uri(url)
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .toEntity(String.class);

        return extractResponse(responseEntity, responseType);
    }
}

