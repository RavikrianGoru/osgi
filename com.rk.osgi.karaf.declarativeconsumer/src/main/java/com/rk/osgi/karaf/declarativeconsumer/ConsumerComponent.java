package com.rk.osgi.karaf.declarativeconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rk.osgi.karaf.quote.IQuoteService;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Deactivate;
import aQute.bnd.annotation.component.Reference;

@Component (name = ConsumerComponent.COMPONENT_NAME)
public class ConsumerComponent
{
    public static final String COMPONENT_NAME = "CunsumerComponent";
    public static final String COMPONENT_LABEL = "Cunsumer Component";
    private static final Logger LOG = LoggerFactory.getLogger(ConsumerComponent.class);

    private IQuoteService quoteService;

    /**
     * Called when all of the SCR Components required dependencies have been satisfied.
     */
    @Activate
    public void activate()
    {
        LOG.info("Activating the " + COMPONENT_LABEL);
        System.out.println(quoteService.getQuote());
        LOG.info("Activating the Quote Value " + quoteService.getQuote());
    }

    /**
     * Called when any of the SCR Components required dependencies become unsatisfied.
     */
    @Deactivate
    public void deactivate()
    {
        LOG.info("Deactivating the " + COMPONENT_LABEL);
    }

    @Reference
    public void setQuoteService(final IQuoteService quoteService)
    {
        this.quoteService = quoteService;
    }

    public void unsetQuoteService(final IQuoteService quoteService)
    {
        this.quoteService = null;
    }
}
