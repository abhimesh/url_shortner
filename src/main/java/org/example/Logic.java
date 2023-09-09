package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Logic implements Runnable{
    static int counter;
    ReentrantLock lock ;
    static HashMap<String,Integer> urlToValue;
    static HashMap<Integer,String> valueToUrl;
    static List<Boolean> vis;
    Random rand ;


    Logic(){
        counter=0;
        urlToValue=new HashMap<>();
        valueToUrl=new HashMap<>();
        lock=new ReentrantLock();
        vis=new ArrayList<>();
        vis.add(false);
        vis.add(false);
        rand=new Random();
    }

    private String base62(int value)
    {
        String s="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb=new StringBuilder();
        while (value>0)
        {
            sb.append(s.charAt(value%62));
            value=value/62;
        }
        return sb.toString();
    }

    private Integer URLtoValue(String short_URL) {
        String s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(short_URL);
        sb.reverse();
        String reversed_short_URL = sb.toString();
        int val = 0;
        for (int i = 0; i < reversed_short_URL.length(); i++) {
            val = val + s.indexOf(reversed_short_URL.charAt(i));
        }
        return val;
    }
    private String encode(String Long_URL)
    {
        lock.lock();
        counter++;
        String shortUrl=base62(counter);
        urlToValue.put(Long_URL,counter);
        valueToUrl.put(counter,Long_URL);
        lock.unlock();
        System.out.println(Long_URL);
        System.out.println(shortUrl);
        return shortUrl;
    }

    public String getShortUrl(incoming_url incoming_url)
    {
        String Long_URL=incoming_url.getLong_URL();
        if(urlToValue.containsKey(Long_URL))
        {return base62(urlToValue.get(Long_URL));}
        else {
            return "www.bitly.com/"+encode(Long_URL);
        }
    }

    public String getLongUrl(String short_URL)
    {
        short_URL=short_URL.substring(14);
        int val=URLtoValue(short_URL);
        if(valueToUrl.containsKey(val))
        {
            return valueToUrl.get(val);
        }
        else {
            System.out.println("short URL is not present in our system");
        }

    return "";}
    public void remove(String Long_URL)
    {
        lock.lock();
        int val=urlToValue.get(Long_URL);
        urlToValue.remove(Long_URL);
        valueToUrl.remove(val);
        lock.unlock();
    }

    @Override
    public void run() {
        List<incoming_url> lis=new ArrayList<>();
        lis.add(new incoming_url("www.google.com"));
        lis.add(new incoming_url("www.myntra.com"));


        int a= rand.nextInt(2);
        while(vis.get(a)==true)
        {
            a=rand.nextInt(2);
        }
        System.out.println("kj");
        System.out.println(a);
        getShortUrl(lis.get(a));
        vis.set(a,true);
        System.out.println(
                "Thread " + Thread.currentThread().getId()
                        + " is running");

    }
}
