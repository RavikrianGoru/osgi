package com.rk.osgi.karaf.servicetracker.consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import com.rk.osgi.karaf.quote.IQuoteService;

public class Activator implements BundleActivator
{

    private static BundleContext context;
    private ServiceTracker serviceTracker;

    static BundleContext getContext()
    {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception
    {
        Activator.context = bundleContext;
        System.out.println("Starting quoteconsumer bundles");
        // Register directly with the service
        MyQuoteServiceTrackerCustomizer customer = new MyQuoteServiceTrackerCustomizer(context);
        serviceTracker = new ServiceTracker(context, IQuoteService.class.getName(), customer);
        serviceTracker.open();
    }

    public void stop(BundleContext bundleContext) throws Exception
    {
        Activator.context = null;
        System.out.println("Stopping quoteconsumer bundles");
        serviceTracker.close();
    }

}
