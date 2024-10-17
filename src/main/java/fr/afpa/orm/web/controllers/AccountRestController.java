package fr.afpa.orm.web.controllers;

import fr.afpa.orm.dto.AccountDto;
import fr.afpa.orm.entities.Account;
import fr.afpa.orm.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {
    private final AccountRepository accountRepository;

    // Constructor injection with @Autowired annotation
    @Autowired
    public AccountRestController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    /**
     * TODO injecter {@link AccountRepository} en dépendance par injection via le constructeur
     * Plus d'informations -> https://keyboardplaying.fr/blogue/2021/01/spring-injection-constructeur/
     */


    /**
     * TODO implémenter une méthode qui traite les requêtes GET et qui renvoie une liste de comptes
     * <p>
     * Attention, il manque peut être une annotation :)
     */
    @GetMapping
    public List<AccountDto> getAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(acc -> new AccountDto(
                        acc.getId(),
                        acc.getCreationTime(),
                        acc.getBalance(),
                        acc.getClient().getId()
                ))
                .collect(Collectors.toList());

    }

    /**
     * TODO implémenter une méthode qui traite les requêtes GET avec un identifiant "variable de chemin" et qui retourne les informations du compte associé
     * Plus d'informations sur les variables de chemin -> https://www.baeldung.com/spring-pathvariable
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isPresent()) {
            Account acc = account.get();

            // Conversion de l'entité Account en DTO
            AccountDto accountDTO = new AccountDto(
                    acc.getId(),
                    acc.getCreationTime(),
                    acc.getBalance(),
                    acc.getClient().getId()
            );

            return ResponseEntity.ok(accountDTO);
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
    public ResponseEntity<Account> postAccount(@RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    /**
     * TODO implémenter une méthode qui traite les requêtes PUT
     * <p>
     * Attention de bien ajouter les annotations qui conviennent
     */
    @PutMapping
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account) {
        Optional<Account> existingAccount = accountRepository.findById(id);

        if (existingAccount.isPresent()) {
            Account modifAccount = existingAccount.get();

            // Mise à jour des champs fournis
            modifAccount.setCreationTime(account.getCreationTime());
            modifAccount.setBalance(account.getBalance());
            modifAccount.setClient(account.getClient());

            // On ne met pas à jour l'ID ni la date de création (générés automatiquement lors de la création)

            // Sauvegarde du compte modifié
            accountRepository.save(modifAccount);

            // Retourne le compte modifié avec un status 200 OK
            return ResponseEntity.ok(modifAccount);
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
    public void delete(@PathVariable long id, HttpServletResponse response) {
        // TODO implémentation
    }
}
