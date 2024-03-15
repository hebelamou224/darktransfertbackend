package com.trustify.darktransfertdata.model;

import com.trustify.darktransfertdata.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String fullname;
    private String photo;
    private String password;
    private Role role;
    private String telephone;

}
