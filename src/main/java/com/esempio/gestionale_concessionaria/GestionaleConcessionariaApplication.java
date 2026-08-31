package com.esempio.gestionale_concessionaria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionaleConcessionariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionaleConcessionariaApplication.class, args);
    }

    //    @Bean
//    CommandLineRunner AutoreNuovo(AutoreRepository autoreRepository) {
//        return args -> {
//            Autore autore = new Autore("Nome Autore");
//            autore.aggiungiLibro(new Libro("Titolo del libro", 2023));
//            autore.aggiungiLibro(new Libro("Altro titolo del libro", 2024));
//            autore.aggiungiLibro(new Libro("Ancora un altro titolo", 2025));
//            autoreRepository.save(autore);
//        };
//    }
//    @Bean
//    CommandLineRunner caricaGeneri(AutoreRepository autoreRepository, GenereRepository genereRepository) {
//        return args -> {
//            Genere fantasy = genereRepository.save(new Genere("Fantasy"));
//            Genere avventura = genereRepository.save(new Genere("Avventura"));
//
//            Autore autore = new Autore("Nome Autore Due");
//            Libro libro = new Libro("Un libro con generi", 2026);
//            libro.aggiungiGenere(fantasy);
//            libro.aggiungiGenere(avventura);
//            autore.aggiungiLibro(libro);
//
//            autoreRepository.save(autore);
//        };
//    }
//    @Bean
//    CommandLineRunner verificaTipiVeicolo(VeicoloRepository veicoloRepository) {
//        return args -> {
//            veicoloRepository.save(new Motocicletta("Yamaha", "MT-07", 2021, 100));
//            veicoloRepository.save(new AutoElettrica("Tesla", "Model 3", 2022, 679));
//
//            veicoloRepository.findAll().forEach(v ->
//                    System.out.println(v.getClass().getSimpleName() + ": " + v.getMarca() + " " + v.getModello()));
//        };
//    }
}
