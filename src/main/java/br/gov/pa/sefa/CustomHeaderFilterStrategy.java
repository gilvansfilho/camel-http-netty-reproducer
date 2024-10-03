package br.gov.pa.sefa;

import org.apache.camel.support.DefaultHeaderFilterStrategy;
import org.apache.camel.support.http.HttpUtil;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
@Component("customHeaderFilterStrategy")
public class CustomHeaderFilterStrategy extends DefaultHeaderFilterStrategy {

    public static final String[] STARTS_WITH = new String[] { "Camel", "camel", "org.apache.camel." };
    private static final Logger LOGGER = Logger.getLogger(CustomHeaderFilterStrategy.class);

    public CustomHeaderFilterStrategy() {
        LOGGER.debug("Inicializando custom filter");
        this.initialize();
    }

    protected void initialize() {
        HttpUtil.addCommonFilters(getOutFilter());
        getOutFilter().add("authorization");
        getInFilter().add("authorization");
        
        setLowerCase(true);

        // filter headers begin with "Camel" or "org.apache.camel"
        // must ignore case for Http based transports
        setOutFilterStartsWith(STARTS_WITH);
        setInFilterStartsWith(STARTS_WITH);
    }
}