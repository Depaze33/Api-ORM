package fr.afpa.orm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import fr.afpa.orm.entities.Client;

/**
 * TODO : implémenter un DTO (uniquement à partir de l'implémentation de la relation "OneToMany")
 *
 * Attention : il faudra peut être 1 DTO par classe métier ?
 *
 * Plus d'informations sur la pattern DTO : https://medium.com/@zubeyrdamar/java-spring-boot-handling-infinite-recursion-a95fe5a53c92
 */
public class AccountDto{

    private Long id;
    private LocalDateTime creationTime;
    private BigDecimal balance;
    private UUID clientId;  // On peut vouloir uniquement l'ID du client dans le DTO, pas tout l'objet Client.

    // Constructeur par défaut
    public AccountDto() {}

    // Constructeur avec tous les champs
    public AccountDto(Long id, LocalDateTime creationTime, BigDecimal balance, UUID clientId) {
        this.id = id;
        this.creationTime = creationTime;
        this.balance = balance;
        this.clientId = clientId;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
}
