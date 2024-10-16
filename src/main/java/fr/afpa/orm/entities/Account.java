package fr.afpa.orm.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Classe représentant le compte bancaire d'un utilisateur
 * 
 * TODO faire de cette classe une entité
 * Plus d'informations sur les entités -> https://gayerie.dev/epsi-b3-orm/javaee_orm/jpa_entites.html
 * Attention de bien choisir les types en fonction de ceux du script SQL.
 */
public class Account {
    /**
     * Identifiant unique du compte
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * TODO ajout d'une association de type @ManyToOne : plusieurs comptes différents peuvent être associés à la même personne
     * 
     * Tutoriel présentant l'utilisation d'une telle association : https://koor.fr/Java/TutorialJEE/jee_jpa_many_to_one.wp
     */

    /*
     * TODO implémenter un constructeur vide --> obligatoire pour l'utilisation d'un ORM
     */

    /*
     * TODO implémenter les getters et les setters
     */
}
