package fr.afpa.orm.repositories;

import fr.afpa.orm.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * TODO implémenter un "repository" (similaire à un DAO) permettant d'interagir avec les données de la BDD
 * Tutoriel -> https://www.geeksforgeeks.org/spring-boot-crudrepository-with-example/
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {


    /**
     * @param id Identifiant du compte à retrouver
     * @return Un objet de compte correspondant à l'identifiant en paramètre
     */
    Optional<Account> findById(long id);

    /**
     * Enregistre les informations d'un compte
     *
     * @param account Le compte à enregistrer
     * @return L'objet enregistré
     */
    Account save(Account account);

    /**
     * Suppression d'un compte
     *
     * @param account
     */
    void delete(Account account);


}
