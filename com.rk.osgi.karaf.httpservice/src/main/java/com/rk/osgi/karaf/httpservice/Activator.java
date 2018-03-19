/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */
package com.rk.osgi.karaf.httpservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator
{

    private ServiceTracker httpServiceTracker;

    public void start(BundleContext context)
    {
        httpServiceTracker = new HttpServiceTracker(context);
        httpServiceTracker.open();
        System.out.println("Starting the bundle");
    }

    public void stop(BundleContext context)
    {
        httpServiceTracker.close();
        httpServiceTracker = null;
        System.out.println("Stopping the bundle");
    }

    private class HttpServiceTracker extends ServiceTracker
    {

        public HttpServiceTracker(BundleContext context)
        {
            super(context, HttpService.class.getName(), null);
            System.out.println(" Hello 1 from HttpService Service Tracker! ");
        }

        public Object addingService(ServiceReference reference)
        {
            HttpService httpService = (HttpService) context.getService(reference);
            System.out.println(" Hello 2 " + httpService);
            try
            {
                httpService.registerServlet("/helloworld", new HelloWorldServlet(), null, null);
                System.out.println(" Hello 3 " + httpService);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return httpService;
        }

        public void removedService(ServiceReference reference, Object service)
        {
            HttpService httpService = (HttpService) service;
            httpService.unregister("/helloworld");
            super.removedService(reference, service);
        }
    }
}
