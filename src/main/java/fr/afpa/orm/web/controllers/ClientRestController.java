package fr.afpa.orm.web.controllers;


import fr.afpa.orm.entities.Client;
import fr.afpa.orm.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/client")
public class ClientRestController {

    private final ClientRepository clientRepository;
    @Autowired
    public ClientRestController(ClientRepository clientRepository) {

        this.clientRepository = clientRepository;
    }

    @GetMapping
    public Iterable<Client> getAll() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getOne(@PathVariable UUID id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * TODO implémenter une méthode qui traite les requêtes POST
     * Cette méthode doit recevoir les informations d'un compte en tant que "request body", elle doit sauvegarder le compte en mémoire et retourner ses informations (en json)
     * Tutoriel intéressant -> https://stackabuse.com/get-http-post-body-in-spring/
     * Le serveur devrai retourner un code http de succès (201 Created)
     **/
    @PostMapping
    public ResponseEntity<Client> postAccount(@RequestBody Client client) {
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    /**
     * TODO implémenter une méthode qui traite les requêtes PUT
     *
     * Attention de bien ajouter les annotations qui conviennent
     */
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable UUID id, @RequestBody Client client) {
        Optional<Client> existingAccount = clientRepository.findById(id);

        if (existingAccount.isPresent()) {
            Client modifClient = existingAccount.get();

            // Mise à jour des champs fournis
            modifClient.setLastName(client.getLastName());
            modifClient.setFirstName(client.getFirstName());
            modifClient.setEmail(client.getEmail());
            modifClient.setBirthdate(client.getBirthdate());
            modifClient.setAccounts(client.getAccounts());

            // On ne met pas à jour l'ID ni la date de création (générés automatiquement lors de la création)

            // Sauvegarde du compte modifié
            clientRepository.save(modifClient);

            // Retourne le compte modifié avec un status 200 OK
            return ResponseEntity.ok(modifClient);
        } else {
            // Retourne une réponse 404 si le compte avec l'ID donné n'est pas trouvé
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * TODO implémenter une méthode qui traite les requêtes  DELETE
     * L'identifiant du compte devra être passé en "variable de chemin" (ou "path variable")
     * Dans le cas d'un suppression effectuée avec succès, le serveur doit retourner un status http 204 (No content)
     *
     * Il est possible de modifier la réponse du serveur en utilisant la méthode "setStatus" de la classe HttpServletResponse pour configurer le message de réponse du serveur
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

