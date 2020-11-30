package mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterApp {
    public static void main(String[] args) {
        SpringApplication.run(TwitterApp.class, args);

        System.out.println("Hello Twitter!");
        System.out.println("Hi Miłosz");

    }
}
