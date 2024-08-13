package uk.gov.hmcts.reform.opal.exception;

public class RestServiceResponseException extends RuntimeException {

    public RestServiceResponseException(String msg) {
        super(msg);
    }

    public RestServiceResponseException(Exception e) {
        super(e);
    }
}
