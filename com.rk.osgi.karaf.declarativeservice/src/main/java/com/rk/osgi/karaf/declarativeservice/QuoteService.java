package com.rk.osgi.karaf.declarativeservice;

import java.util.Random;

import com.rk.osgi.karaf.quote.IQuoteService;

import aQute.bnd.annotation.component.Component;

@Component
public class QuoteService implements IQuoteService
{

    public String getQuote()
    {
        Random random = new Random();
        int num = random.nextInt(3);
        System.out.println("Generated num:"+num);
        switch (num)
        {
            case 0:
                return "service quote -0";
            case 1:
                return "service quote -1";
            case 2:
                return "service quote -2";
            case 3:
                return "service quote -3";
            default:
                return "service quote -default -100";
        }
    }

}
