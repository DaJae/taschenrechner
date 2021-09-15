package de.dajae.taschenrechner.database;

import de.dajae.taschenrechner.database.entity.HistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepo extends CrudRepository<HistoryEntity, Long> {

    List<HistoryEntity> findByUserAgent(String userAgent);
}
