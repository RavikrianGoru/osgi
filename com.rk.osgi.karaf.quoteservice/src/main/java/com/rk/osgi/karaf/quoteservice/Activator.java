/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rk.osgi.karaf.quoteservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.rk.osgi.karaf.quote.IQuoteService;
import com.rk.osgi.karaf.quoteservice.internal.QuoteService;

public class Activator implements BundleActivator {

    public void start(BundleContext context) {
        IQuoteService quoteService=new QuoteService();
        System.out.println("QuotaService activate's start()-called");
        System.out.println("About to register IQuoteService");
        context.registerService(IQuoteService.class.getName(), quoteService, null);
        System.out.println("IQuoteService is Registered");
    }

    public void stop(BundleContext context) {
        System.out.println("QuotaService activate's stop()-called");
        context.registerService(IQuoteService.class.getName(), null, null);
        System.out.println("IQuoteService is Deregistered");
    }

}