package it.uniroma3.siw.museo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MuseoApplication {



	public static void main(String[] args) {
		SpringApplication.run(MuseoApplication.class, args);
	}
	
	//capire che valore dare a dataMorte e dataNascita nella classe artista
	//Password criptata = 123456 = $2a$10$DTQemAHGn7t7WW.a4XsOmO6tJIAzSv4Om1kSO5y5BjCXw7fB8Li3a

}
