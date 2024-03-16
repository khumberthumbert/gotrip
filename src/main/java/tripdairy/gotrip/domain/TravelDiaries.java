package tripdairy.gotrip.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TravelDiaries {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diary_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy = "travelDiaries")
    List<Media> media = new ArrayList<>();

    @OneToMany(mappedBy = "travelDiaries")
    List<Interactions> interactions = new ArrayList<>();

    @OneToMany(mappedBy = "travelDiaries")
    List<Locations> locations = new ArrayList<>();

    private String title;

    @Lob
    private String diaryDescription;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime travelDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
}
