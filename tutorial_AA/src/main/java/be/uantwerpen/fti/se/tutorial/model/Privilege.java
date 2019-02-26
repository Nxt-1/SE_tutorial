package be.uantwerpen.fti.se.tutorial.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
public class Privilege extends AbstractPersistable<Long> {
    private String name;

    public Privilege() {
        this.name = "";
    }

    public Privilege(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

