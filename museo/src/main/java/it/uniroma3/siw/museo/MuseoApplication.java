package it.uniroma3.siw.museo;


import it.uniroma3.siw.museo.model.Credentials;
import it.uniroma3.siw.museo.repository.CredentialsRepository;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;

@SpringBootApplication
public class MuseoApplication {



	public static void main(String[] args) {
		SpringApplication.run(MuseoApplication.class, args);
	}

}
