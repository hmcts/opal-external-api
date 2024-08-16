package uk.gov.hmcts.reform.opal.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.hmcts.reform.authorisation.ServiceAuthorisationApi;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGeneratorFactory;

@Configuration
public class ServiceTokenGeneratorConfiguration {

        @Bean
        public AuthTokenGenerator serviceAuthTokenGenerator(
            @Value("${s2s.security.secret}") final String secret,
            @Value("${s2s.security.microservice.key}") final String microService,
            final ServiceAuthorisationApi serviceAuthorisationApi
        ) {
            return AuthTokenGeneratorFactory.createDefaultGenerator(secret, microService, serviceAuthorisationApi);
        }
}

