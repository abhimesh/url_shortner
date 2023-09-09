package org.example;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        Logic obj=new Logic();
        incoming_url incomingUrl1=new incoming_url("www.google.com");
        incoming_url incomingUrl2=new incoming_url("www.pornhub.com");
        List<incoming_url> lis=new ArrayList<>();
        lis.add(incomingUrl1);
        lis.add(incomingUrl2);
        for (int i = 0; i < 2; i++) {
            Thread object
                    = new Thread(obj);

            object.start();
        }

        //System.out.println(t1.getLongUrl("www.bitly.com/2"));
    }
}