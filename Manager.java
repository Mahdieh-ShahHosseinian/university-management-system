package com.example.sm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Manager extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private int managerId;

    @Getter
    @Setter
    @Column(unique = true, nullable = false, length = 10)
    private long nationalId;

    @Getter
    @Setter
    @Column(nullable = false, length = 20)
    private String firstName;

    @Getter
    @Setter
    @Column(nullable = false, length = 20)
    private String lastName;

    public Manager() {
        role = "MANAGER";
    }

    @Override
    public boolean equals(Object obj) {

        Manager manager = null;
        if (obj instanceof Manager) manager = (Manager) obj;
        assert manager != null;
        return managerId == manager.getManagerId();
    }
}
