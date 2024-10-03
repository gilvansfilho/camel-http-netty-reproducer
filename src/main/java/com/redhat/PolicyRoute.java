package com.redhat;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PolicyRoute extends RouteBuilder {

    @Autowired
    CustomHeaderFilterStrategy headerFilterStrategy;

    private static String logName = PolicyRoute.class.getName();

    @Override
    public void configure() {

        onException(java.lang.Exception.class)
            .handled(true)
            .setBody(constant("Unauthorized"))
            .setHeader(Exchange.HTTP_RESPONSE_CODE , constant(401))
            .maximumRedeliveries(0)
            .log(LoggingLevel.ERROR, logName, ">>> ${routeId} - Caught exception: ${exception.stacktrace}");

        from("netty-http:proxy://0.0.0.0:8080")
            .id("3scale-policy-proxy")
            .log(LoggingLevel.INFO, "HTTP HEADERS: ${headers}")
            .removeHeader("Authorization")
            .toD("netty-http:"
                + "${headers." + Exchange.HTTP_SCHEME + "}://"
                + "${headers." + Exchange.HTTP_HOST + "}:"
                + "${headers." + Exchange.HTTP_PORT + "}"
                + "${headers." + Exchange.HTTP_PATH + "}"
                + "?synchronous=true&throwExceptionOnFailure=false");
    }

}