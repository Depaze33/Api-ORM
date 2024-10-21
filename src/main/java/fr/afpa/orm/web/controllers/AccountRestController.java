package fr.afpa.orm.web.controllers;

import fr.afpa.orm.dto.AccountDto;
import fr.afpa.orm.entities.Account;
import fr.afpa.orm.entities.Client;
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
        List<Account> accounts = (List<Account>) accountRepository.findAll();

        return accounts.stream()
                .map(account -> new AccountDto(
                        account.getId(),
                        account.getCreationTime(),
                        account.getBalance(),
                        account.getClient().getId()  // On récupère uniquement l'ID du client
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

            AccountDto accountDTO = new AccountDto(
                    acc.getId(),
                    acc.getCreationTime(),
                    acc.getBalance(),
                    acc.getClient() != null ? acc.getClient().getId() : null  // clientId est de type UUID
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
    public ResponseEntity<AccountDto> postAccount(@RequestBody AccountDto accountDto) {
        Account account = new Account();
        account.setCreationTime(accountDto.getCreationTime());
        account.setBalance(accountDto.getBalance());

        Client client = new Client();
        client.setId(accountDto.getClientId());  // On assigne simplement l'ID du client
        account.setClient(client);

        Account savedAccount = accountRepository.save(account);

        AccountDto savedAccountDto = new AccountDto(
                savedAccount.getId(),
                savedAccount.getCreationTime(),
                savedAccount.getBalance(),
                savedAccount.getClient().getId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccountDto);
    }


    /**
     * TODO implémenter une méthode qui traite les requêtes PUT
     * <p>
     * Attention de bien ajouter les annotations qui conviennent
     */
    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        Optional<Account> existingAccount = accountRepository.findById(id);

        if (existingAccount.isPresent()) {
            Account modifAccount = existingAccount.get();

            // Mise à jour des champs fournis
            modifAccount.setCreationTime(accountDto.getCreationTime());
            modifAccount.setBalance(accountDto.getBalance());

            // Gestion de la relation Client
            Client client = new Client();
            client.setId(accountDto.getClientId());
            modifAccount.setClient(client);

            // Sauvegarde du compte modifié
            Account updatedAccount = accountRepository.save(modifAccount);

            // Retourne le DTO mis à jour
            AccountDto updatedAccountDto = new AccountDto(
                    updatedAccount.getId(),
                    updatedAccount.getCreationTime(),
                    updatedAccount.getBalance(),
                    updatedAccount.getClient().getId()
            );

            return ResponseEntity.ok(updatedAccountDto);
        } else {
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
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable long id) {
        return accountRepository.findById(id)
                .map(account -> {
                    AccountDto accountDto = new AccountDto(
                            account.getId(),
                            account.getCreationTime(),
                            account.getBalance(),
                            account.getClient() != null ? account.getClient().getId() : null
                    );

                    accountRepository.delete(account);
                    return ResponseEntity.ok(accountDto);  // Retourne le DTO du compte supprimé
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
