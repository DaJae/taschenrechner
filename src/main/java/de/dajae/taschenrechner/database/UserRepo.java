package de.dajae.taschenrechner.database;

import de.dajae.taschenrechner.database.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {
}
