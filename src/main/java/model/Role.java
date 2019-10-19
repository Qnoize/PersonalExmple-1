package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "roleTable")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "roleId")
    private Long roleId;

    @Column(name = "role")
    private String role;

    public Role(Long roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    @ManyToMany
    @JoinTable(
            name = "userRole",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private Set<User> users = new HashSet<>();

    public Long getRoleId() {
        return roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Role(String role) {
        this.role = role;
    }

    public Role() { }

    public Set<User> getUsers() { return users; }

    public void setUsers(Set<User> users) { this.users = users; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role1 = (Role) o;
        return Objects.equals(roleId, role1.roleId) &&
                Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }
}
