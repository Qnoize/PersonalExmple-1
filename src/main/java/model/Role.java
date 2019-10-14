package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_role")
public class Role implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    private String role;

    @Column(name = "id_owner")
    private Long id_owner;

    public Role(Long id, String role, Long id_owner) {
        this.id = id;
        this.role = role;
        this.id_owner = id_owner;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public Long getId_owner() {
        return id_owner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId_owner(Long id_owner) {
        this.id_owner = id_owner;
    }

    public Role(String role, Long id_owner) {
        this.role = role;
        this.id_owner = id_owner;
    }

    public Role() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role1 = (Role) o;
        return Objects.equals(id, role1.id) &&
                Objects.equals(role, role1.role) &&
                Objects.equals(id_owner, role1.id_owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, id_owner);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", id_owner=" + id_owner +
                '}';
    }
}
