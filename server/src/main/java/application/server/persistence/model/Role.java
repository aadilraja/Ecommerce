package application.server.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {


    @Id
    @Column(name = "role_id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    public Role() {
    }

    public Role(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}


