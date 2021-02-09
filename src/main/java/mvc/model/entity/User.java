package mvc.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private LocalDate birthDate;
    private String role;

    @ManyToMany
    private Set<User> followers;

    @ManyToMany(mappedBy = "followers")
    private Set<User> following;

}
