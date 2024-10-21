package fr.afpa.orm.repositories;


import fr.afpa.orm.entities.Insurance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InsuranceRepository extends CrudRepository<Insurance, Long> {
}
