package com.example.sm.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

import static com.example.sm.security.ApplicationUserRole.MANAGER;
import static com.example.sm.security.ApplicationUserRole.PROFESSOR;

@Entity
@Data
public class Manager extends ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int managerId;

    @Column(unique = true, nullable = false, length = 10)
    private long nationalId;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    public Manager() {
        setGrantedAuthorities(MANAGER.getGrantedAuthorities());
    }

    @Override
    public boolean equals(Object obj) {

        Manager manager = null;
        if (obj instanceof Manager) manager = (Manager) obj;
        assert manager != null;
        return managerId == manager.getManagerId();
    }
}
