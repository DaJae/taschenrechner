package de.dajae.taschenrechner.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class HistoryEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="user_agent")
    private String userAgent;

    @Column
    private String formula;


    public HistoryEntity(String userAgent, String formula){
        this.userAgent = userAgent;
        this.formula = formula;
    }

    public HistoryEntity(){}
}
