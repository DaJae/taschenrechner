package de.dajae.taschenrechner.database;

import de.dajae.taschenrechner.database.entity.HistoryEntity;
import de.dajae.taschenrechner.database.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RepoHelper {


    private HistoryRepo historyRepo;
    private UserRepo userRepo;

    public void saveHistory(String userAgent, String formula){
        HistoryEntity entity = new HistoryEntity(userAgent, formula);

        historyRepo.save(entity);
    }

    public boolean checkHistoryEntries(String userAgent){
        List<HistoryEntity> historyEntity = historyRepo.findByUserAgent(userAgent);

        return historyEntity.isEmpty();
    }

    public void createUser(String userAgent){
        UserEntity userEntity = new UserEntity(userAgent);

        userRepo.save(userEntity);
    }

    @Autowired
    public void setUserRepo(UserRepo userRepo){ this.userRepo = userRepo;}

    @Autowired
    public void setHistoryRepo(HistoryRepo historyRepo){
        this.historyRepo = historyRepo;
    }
}
