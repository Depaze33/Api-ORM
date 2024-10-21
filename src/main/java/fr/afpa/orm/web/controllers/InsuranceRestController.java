package fr.afpa.orm.web.controllers;

import fr.afpa.orm.entities.Insurance;
import fr.afpa.orm.repositories.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceRestController {

    private final InsuranceRepository insuranceRepository;

    @Autowired
    public InsuranceRestController(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @GetMapping
    public Iterable<Insurance> getAll() {
        return insuranceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insurance> getById(@PathVariable long id) {
        return insuranceRepository.findById(id)
                .map(ResponseEntity::ok) // Si l'assurance est trouvée, retourner HTTP 200 avec l'objet Insurance
                .orElse(ResponseEntity.notFound().build()); // Si non trouvée, retourner HTTP 404
    }

    @PostMapping
    public ResponseEntity<Insurance> postAccount(@RequestBody Insurance insurance) {
        Insurance savedInsurance = insuranceRepository.save(insurance);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInsurance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insurance> putAccount(@PathVariable Long id, @RequestBody Insurance insurance) {
        Optional<Insurance> existingInsurance = insuranceRepository.findById(id);

        if (existingInsurance.isPresent()) {
            Insurance modifInsurance = existingInsurance.get();

            // Update the fields provided
            modifInsurance.setName(insurance.getName());

            // Save the modified insurance account
            insuranceRepository.save(modifInsurance);

            // Return the modified account with a 200 OK status
            return ResponseEntity.ok(modifInsurance);
        } else {
            // Return a 404 response if the insurance with the given ID is not found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable long id) {
        return insuranceRepository.findById(id)
                .map(account -> {
                    insuranceRepository.delete(account);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
