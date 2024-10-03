package com.redhat;

import org.apache.camel.Exchange;
import org.apache.camel.spi.HeaderFilterStrategy;
import org.apache.camel.support.DefaultHeaderFilterStrategy;
import org.apache.camel.support.http.HttpUtil;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
@Component("customHeaderFilterStrategy")
public class CustomHeaderFilterStrategy implements HeaderFilterStrategy {

    public static final String[] STARTS_WITH = new String[] { "Camel", "camel", "org.apache.camel." };
    private static final Logger LOGGER = Logger.getLogger(CustomHeaderFilterStrategy.class);

    public CustomHeaderFilterStrategy() {
        LOGGER.debug("Inicializando custom filter");
    }


    @Override
    public boolean applyFilterToCamelHeaders(String headerName, Object headerValue, Exchange exchange) {
        LOGGER.infof("Header out -> %s", headerName);
        return false;
    }

    @Override
    public boolean applyFilterToExternalHeaders(String headerName, Object headerValue, Exchange exchange) {
        LOGGER.infof("Header in -> %s", headerName);
        if (headerName.equals("Authorization")) {
            return true;
        }
        return false;
    }
}