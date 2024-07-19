package uk.gov.hmcts.reform.opal.util;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

public class ServerPasswordCallback implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        for (Callback callback : callbacks) {
            // handle callback
            final WSPasswordCallback pc = (WSPasswordCallback) callback;

            if (pc.getUsage() == WSPasswordCallback.SIGNATURE || pc.getUsage() == WSPasswordCallback.DECRYPT) {
                //TODO: get password from key vault
                pc.setPassword("password");
            }
        }
    }
}
