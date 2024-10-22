package fr.afpa.orm.web.controllers;


import fr.afpa.orm.dto.ClientDTO;
import fr.afpa.orm.entities.Client;
import fr.afpa.orm.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/client")
public class ClientRestController {

    private final ClientRepository clientRepository;


    @Autowired
    public ClientRestController(ClientRepository clientRepository) {

        this.clientRepository = clientRepository;

    }


    @GetMapping
    @Transactional  // Assure que la session Hibernate est active pour charger les comptes
    public List<ClientDTO> getAll() {
        List<Client> clients = (List<Client>) clientRepository.findAll();
        return clients.stream()
                .map(client -> new ClientDTO(
                        client.getId(),
                        client.getFirstName(),
                        client.getLastName(),
                        client.getEmail(),
                        client.getBirthdate(),
                        client.getInsurances()// Ceci devrait maintenant être initialisé

                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> obtenirCompteParId(@PathVariable UUID id) {
        Optional<Client> clients = clientRepository.findById(id);

        if (clients.isPresent()) {
            Client client = clients.get();

            ClientDTO clientDTO = new ClientDTO(
                    client.getId(),
                    client.getFirstName(),
                    client.getLastName(),
                    client.getEmail(),
                    client.getBirthdate()
            );
            return ResponseEntity.ok(clientDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
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

//    @PostMapping
//    public ResponseEntity<Client> postAccount(@RequestBody ClientDTO clientDTO) {
//
//    }

    /**
     * TODO implémenter une méthode qui traite les requêtes PUT
     * <p>
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
     * <p>
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

