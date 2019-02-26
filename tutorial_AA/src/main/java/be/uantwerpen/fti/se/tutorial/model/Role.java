package be.uantwerpen.fti.se.tutorial.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Role extends AbstractPersistable<Long> {
    @Size(min=2, max=60)
    private String name;
    @ManyToMany
    @JoinTable(
            name="ROLE_PERM",
            joinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="PERM_ID", referencedColumnName="ID")})
    private List<Privilege> privileges;

    @ManyToMany(mappedBy="roles")
    private List<User> users;

    public Role() {
        this.name = "";
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}

