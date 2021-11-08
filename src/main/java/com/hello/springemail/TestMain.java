package com.hello.springemail;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) throws IOException {
        List<String> email = readEmail();
        List<String> events = readMessage();
        System.out.println("email = " + email);
        System.out.println("events = " + events);
        System.out.println("events = " + events.size());
    }

    public static List<String> readEmail() throws IOException {
        String file = "/Users/yeonkyu/Desktop/etc/01.sendMail/springemail/email";

        List<String> emails = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        reader.lines().forEach(emails::add);

        return emails;
    }

    public static List<String> readMessage() throws IOException {
        String file = "/Users/yeonkyu/Desktop/etc/01.sendMail/springemail/event";

        List<String> events = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        String event = "";
        int index = 0;

        while ( (line = reader.readLine()) != null) {
            event += line +"\n";
            index += 1;
            if (index == 11) {
                events.add(event);
                event = "";
                index = 0;
            }
        }
        return events;
    }
}
