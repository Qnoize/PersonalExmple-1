package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long user_id;

    @Id
    @Column(name = "role_id")
    private Long role_id;

    public Long getUser_id() {
        return user_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public UserRole() { }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public UserRole(Long user_id, Long role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }
}
