package kz.orion.sk_orion_springboot;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SkOrionSpringbootApplication {
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        Application.launch(JavaFXApplication.class, args);
    }
}
