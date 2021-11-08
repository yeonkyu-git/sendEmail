package com.hello.springemail;

import com.hello.springemail.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringemailApplication {

	private final EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(SpringemailApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() throws IOException{

		List<String> emails = readEmail();
		List<String> messages = readMessage();

		for (String email : emails) {
			for (String message : messages) {
				senderService.sendEmail(email,
						"This is Enpharos Event",
						message);
			}

		}


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
