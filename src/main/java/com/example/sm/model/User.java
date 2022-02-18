package com.example.sm.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

@MappedSuperclass
public class User extends RepresentationModel<User> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Transient
    protected String role = "USER";

    @Getter
    @Setter
    @Column(nullable = false, length = 20, unique = true)
    protected String username;

    @Getter
    @Setter
    @Column(nullable = false, length = 60)
    protected String password;
}
