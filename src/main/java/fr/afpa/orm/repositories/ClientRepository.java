package fr.afpa.orm.repositories;

import fr.afpa.orm.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.orm.entities.Account;

import java.util.List;
import java.util.Optional;

/**
 * TODO implémenter un "repository" (similaire à un DAO) permettant d'interagir avec les données de la BDD
 * Tutoriel -> https://www.geeksforgeeks.org/spring-boot-crudrepository-with-example/
 */
@Repository
public interface ClientRepository extends CrudRepository<Client, String>{

    List<Client> findAll();

    /**
     * @param id Identifiant du compte à retrouver
     * @return Un objet de compte correspondant à l'identifiant en paramètre
     */
    Optional<Client> findById(long id);

    /**
     *
     * @param client
     * @return
     */
    Client save(Client client);

    /**
     *
     * @param client
     */
    void delete(Client client);



}

