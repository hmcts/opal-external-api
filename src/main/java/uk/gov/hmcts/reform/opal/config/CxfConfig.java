package uk.gov.hmcts.reform.opal.config;

import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.soap.SOAPBinding;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.hmcts.reform.opal.controllers.CppSoapController;


@Configuration
@RequiredArgsConstructor
public class CxfConfig {

    private final CppSoapController cppSoapController;

    private final WsSecurityConfig wsSecurityConfig;

    @Bean
    public Endpoint cppEndpoint() throws Exception {
        EndpointImpl endpoint = new EndpointImpl(springBus(), cppSoapController);
        endpoint.setBindingUri(SOAPBinding.SOAP12HTTP_BINDING);
        endpoint.publish("/CppGatewayService");
        endpoint.getInInterceptors().add(wsSecurityConfig.cppWss4JInInterceptor());
        endpoint.getOutInterceptors().add(wsSecurityConfig.cppWss4JOutInterceptor());
        endpoint.getInInterceptors().add(new LoggingInInterceptor());
        endpoint.getOutInterceptors().add(new LoggingOutInterceptor());
        return endpoint;
    }

    @Bean
    public ServletRegistrationBean cxfServlet() {
        CXFServlet cxfServlet = new CXFServlet();
        ServletRegistrationBean<CXFServlet> servletDef = new ServletRegistrationBean<>(cxfServlet, "/service/*");
        servletDef.setLoadOnStartup(1);
        return servletDef;
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }
}
