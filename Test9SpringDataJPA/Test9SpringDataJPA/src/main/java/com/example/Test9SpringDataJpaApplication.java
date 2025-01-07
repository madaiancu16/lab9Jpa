package com.example;

import com.example.entity.Masina;
import com.example.repository.MasinaSpringDataJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication
public class Test9SpringDataJpaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Test9SpringDataJpaApplication.class, args);
    }

    @Autowired
    MasinaSpringDataJpaRepository repository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Meniu Interactiv pentru Operatii ");
        boolean k = true;
        while (k) {
            System.out.println("\n1. Afiseaza lista masinilor");
            System.out.println("2. Insereaza o masina");
            System.out.println("3. Sterge masina dupa numar de inmatriculare");
            System.out.println("4. Cauta masina dupa numar de inmatriculare");
            System.out.println("5. Afiseaza numarul de masini cu o anumita marca");
            System.out.println("6. Afiseaza numarul de masini care au sub 100000km");
            System.out.println("7. Afiseaza masinile mai noi de 5 ani");
            System.out.println("8. Iesi din aplicatie");

            System.out.print("Alege o optiune: ");
            int optiune = scanner.nextInt();
            scanner.nextLine(); 

            switch (optiune) {
                case 1:
                    System.out.println("Lista Masinilor din tabel");
                    repository.findAll().forEach(System.out::println);
                    break;
                case 2:
                	  System.out.println("Inserarea unei masini");
                      Masina masinaAutomatica = new Masina("BV12ABC", "Dacia", 2018, "rosu", 75000);
                      repository.save(masinaAutomatica);
                      System.out.println("Masina inserata automat: " + masinaAutomatica);
                      break;
                case 3:
                    System.out.println("Stergere dupa nr de inmatriculare");
                    System.out.print("Nr inmatriculare: ");
                    String NrInmatriculare = scanner.nextLine();
                    repository.deleteByNrInmatriculare(NrInmatriculare);
                    System.out.println("Masina cu nr inmatriculare " +NrInmatriculare + " a fost stearsa.");
                    break;
                case 4:
                    System.out.println("Cautare dupa nr de inmatriculare");
                    System.out.print("Nr inmatriculare: ");
                    NrInmatriculare= scanner.nextLine();
                    System.out.println(repository.findByNrInmatriculare(NrInmatriculare));
                    break;
                case 5:
                    System.out.println("Nr masini cu o anumita marca");
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.println("Nr masini cu marca " + marca + ": " + repository.countByMarca(marca));
                    break;
                case 6:
                    System.out.println(" Nr masini care au sub 100000km ");
                    System.out.println("Nr masini: " + repository.countByNrKilometriiLessThan(100000));
                    break;
                case 7:
                    System.out.println("Masini mai noi de 5 ani");
                    repository.findByAnFabricatieGreaterThan(LocalDate.now().getYear() - 5).forEach(System.out::println);
                    break;
                case 8:
                    k = false;
                    System.out.println("Iesire din aplicatie...");
                    break;
                default:
                    System.out.println("Optiune invalida. Te rog sa alegi din nou.");
                    break;
            }
        }

        logger.info("Dupa modificari avem urmatoarea lista {}", repository.findAll());
    }
}
