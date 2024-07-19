package uk.gov.hmcts.reform.opal.config;

import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Properties;

@Configuration
public class WsSecurityConfig {

    public WSS4JInInterceptor cppWss4JInInterceptor() {
        return createWss4JInInterceptor(cppCryptoProperties());
    }


    public WSS4JOutInterceptor cppWss4JOutInterceptor() {
        return createWss4JOutInterceptor("opal", "cpp", cppCryptoProperties());
    }

    private WSS4JInInterceptor createWss4JInInterceptor(Properties cryptoProperties) {
        HashMap<String, Object> props = new HashMap<>();

        props.put("action", "Signature Encrypt");
        props.put("signaturePropRefId", "cryptoProperties");
        props.put("decryptionPropRefId", "cryptoProperties");
        props.put("passwordCallbackClass", "uk.gov.hmcts.reform.opal.util.ServerPasswordCallback");
        props.put("cryptoProperties", cryptoProperties);

        return new WSS4JInInterceptor(props);
    }

    private WSS4JOutInterceptor createWss4JOutInterceptor(String user, String encryptionUser,
                                                          Properties cryptoProperties) {
        HashMap<String, Object> props = new HashMap<>();

        props.put("action", "Signature Encrypt");
        props.put("user", user);
        props.put("encryptionUser", encryptionUser);
        props.put("signaturePropRefId", "cryptoProperties");
        props.put("encryptionPropRefId", "cryptoProperties");
        props.put("passwordCallbackClass",
                                        "uk.gov.hmcts.reform.opal.util.ServerPasswordCallback");
        props.put("signatureParts", "{Element}{http://www.w3.org/2003/05/soap-envelope}Body");
        props.put("encryptionParts", "{Content}{http://www.w3.org/2003/05/soap-envelope}Body");
        props.put("encryptionSymAlgorithm", "http://www.w3.org/2001/04/xmlenc#tripledes-cbc");
        props.put("cryptoProperties", cryptoProperties);

        return new WSS4JOutInterceptor(props);
    }

    private Properties cppCryptoProperties() {
        Properties properties = new Properties();
        properties.setProperty("org.apache.ws.security.crypto.provider", "org.apache.ws.security.components.crypto.Merlin");
        properties.setProperty("org.apache.ws.security.crypto.merlin.keystore.type", "JKS");
        properties.setProperty("org.apache.ws.security.crypto.merlin.keystore.file", "OpalKeyStore.jks");
        properties.setProperty("org.apache.ws.security.crypto.merlin.keystore.password", "password");
        properties.setProperty("org.apache.ws.security.crypto.merlin.keystore.alias", "opal");
        return properties;
    }
}
