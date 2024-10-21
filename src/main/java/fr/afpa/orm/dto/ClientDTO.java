package fr.afpa.orm.dto;

import fr.afpa.orm.entities.Insurance;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ClientDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthdate;
    private List<AccountDto> accounts;

    public ClientDTO(UUID id, String firstName, String lastName, String email, LocalDate birthdate, Set<Insurance> insurances) {
        // Constructeur vide
    }

    public ClientDTO(UUID id, String firstName, String lastName, String email, LocalDate birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;


    }


    // Getters et setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }


}

