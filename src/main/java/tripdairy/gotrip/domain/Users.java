package tripdairy.gotrip.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "users")
    List<TravelDiaries> travelDiaries = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    List<Interactions> interactions = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastLoginDate;

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}
