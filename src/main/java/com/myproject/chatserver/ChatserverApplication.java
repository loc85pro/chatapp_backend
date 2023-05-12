package com.myproject.chatserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//import com.myproject.chatserver.exception.GlobalExceptionHandler;

@SpringBootApplication
// @Import(GlobalExceptionHandler.class)
public class ChatserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatserverApplication.class, args);
	}

}
