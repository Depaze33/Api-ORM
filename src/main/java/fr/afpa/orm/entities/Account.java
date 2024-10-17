package fr.afpa.orm.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Classe représentant le compte bancaire d'un utilisateur
 * <p>
 * TODO faire de cette classe une entité
 * Plus d'informations sur les entités -> https://gayerie.dev/epsi-b3-orm/javaee_orm/jpa_entites.html
 * Attention de bien choisir les types en fonction de ceux du script SQL.
 */
@Entity
@Table(name = "account")
public class Account {
    /**
     * Identifiant unique du compte
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime creationTime;
    private BigDecimal balance;
    /**
     * TODO ajout d'une association de type @ManyToOne : plusieurs comptes différents peuvent être associés à la même personne
     * <p>
     * Tutoriel présentant l'utilisation d'une telle association : https://koor.fr/Java/TutorialJEE/jee_jpa_many_to_one.wp
     */
    @JoinColumn(name = "id_client")
    @ManyToOne(targetEntity = Client.class)
    private Client client;

    /*
     *
     */
    public Account(Client client) {
        this.client = client;
    }

    public Account() {

    }

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}

