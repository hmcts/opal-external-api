package uk.gov.hmcts.reform.opal.config;

import lombok.RequiredArgsConstructor;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.crypto.Crypto;
import org.apache.wss4j.common.crypto.Merlin;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.hmcts.reform.opal.util.ServerPasswordCallback;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Base64;
import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class WsSecurityConfig {

    @Value("${soap.keystore.encoded-jks-string}")
    private String keystoreBase64String;

    @Value("${soap.keystore.password}")
    private String keystorePassword;

    @Value("${soap.keystore.aliases.private}")
    private String privateKeyAlias;

    @Value("${soap.keystore.aliases.public.cpp}")
    private String cppAlias;

    private final ServerPasswordCallback serverPasswordCallback;

    @Bean
    public WSS4JInInterceptor cppWss4JInInterceptor() throws Exception {
        return createWss4JInInterceptor();
    }

    @Bean
    public WSS4JOutInterceptor cppWss4JOutInterceptor() throws Exception {
        return createWss4JOutInterceptor(privateKeyAlias, cppAlias);
    }

    private WSS4JInInterceptor createWss4JInInterceptor() throws Exception {
        HashMap<String, Object> props = new HashMap<>();

        props.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPTION);
        props.put(WSHandlerConstants.SIG_PROP_REF_ID, "cryptoProperties");
        props.put(WSHandlerConstants.DEC_PROP_REF_ID, "cryptoProperties");
        props.put(WSHandlerConstants.PW_CALLBACK_REF, serverPasswordCallback);
        props.put("cryptoProperties", crypto());

        return new WSS4JInInterceptor(props);
    }

    private WSS4JOutInterceptor createWss4JOutInterceptor(String user, String encryptionUser) throws Exception {
        HashMap<String, Object> props = new HashMap<>();

        props.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPTION);
        props.put(WSHandlerConstants.USER, user);
        props.put(WSHandlerConstants.ENCRYPTION_USER, encryptionUser);
        props.put(WSHandlerConstants.SIG_PROP_REF_ID, "cryptoProperties");
        props.put(WSHandlerConstants.ENC_PROP_REF_ID, "cryptoProperties");
        props.put(WSHandlerConstants.PW_CALLBACK_REF, serverPasswordCallback);
        props.put(WSHandlerConstants.SIGNATURE_PARTS, "{Element}{http://www.w3.org/2003/05/soap-envelope}Body");
        props.put(WSHandlerConstants.ENCRYPTION_PARTS, "{Content}{http://www.w3.org/2003/05/soap-envelope}Body");
        props.put(WSHandlerConstants.ENC_SYM_ALGO, "http://www.w3.org/2001/04/xmlenc#tripledes-cbc");
        props.put("cryptoProperties", crypto());

        return new WSS4JOutInterceptor(props);
    }

    private Crypto crypto() throws Exception {

        // Load keystore from terraform key vault secret.
        KeyStore keyStore = KeyStore.getInstance("JKS");
        String keystoreData = keystoreBase64String;
        byte[] decodedKeystoreData = Base64.getDecoder().decode(keystoreData);
        try (InputStream keyStoreStream = new ByteArrayInputStream(decodedKeystoreData)) {
            keyStore.load(keyStoreStream, keystorePassword.toCharArray());
        }

        Merlin crypto = new Merlin();
        crypto.setKeyStore(keyStore);
        return crypto;
    }
}
