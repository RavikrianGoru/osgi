package com.rk.osgi.karaf.servicetracker.consumer;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import com.rk.osgi.karaf.quote.IQuoteService;

public class MyQuoteServiceTrackerCustomizer implements ServiceTrackerCustomizer
{
    private final BundleContext context;

    public MyQuoteServiceTrackerCustomizer(BundleContext context)
    {
        this.context = context;
    }

    private MyThread myThread;

    public Object addingService(ServiceReference reference)
    {
        IQuoteService service = (IQuoteService) context.getService(reference);
        myThread = new MyThread(service);
        myThread.start();
        return service;
    }

    public void modifiedService(ServiceReference reference, Object service)
    {
        // removedService(reference, service);
        // addingService(reference);
    }

    public void removedService(ServiceReference reference, Object service)
    {
        System.out.println("How sad. Service for quote is gone");
        myThread.stopThread();
    }

    public static class MyThread extends Thread
    {
        private volatile boolean active = true;
        private final IQuoteService service;

        public MyThread(IQuoteService service)
        {
            this.service = service;
        }

        public void run()
        {
            while (active)
            {
                System.out.println(service.getQuote());
                try
                {
                    Thread.sleep(5000);
                }
                catch (Exception e)
                {
                    System.out.println("Thread interrupted " + e.getMessage());
                }
            }
        }

        public void stopThread()
        {
            active = false;
        }
    }
}
