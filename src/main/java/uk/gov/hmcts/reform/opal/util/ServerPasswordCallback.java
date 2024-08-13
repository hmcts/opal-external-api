package uk.gov.hmcts.reform.opal.util;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

@Component
public class ServerPasswordCallback implements CallbackHandler {

    @Value("${soap.keystore.password}")
    private String keystorePassword;

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        for (Callback callback : callbacks) {
            // handle callback
            final WSPasswordCallback pc = (WSPasswordCallback) callback;

            if (pc.getUsage() == WSPasswordCallback.SIGNATURE || pc.getUsage() == WSPasswordCallback.DECRYPT) {

                pc.setPassword(keystorePassword);
            }
        }
    }
}
