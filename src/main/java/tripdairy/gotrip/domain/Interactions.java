package tripdairy.gotrip.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Interactions {

    @Id
    @GeneratedValue
    @Column(name = "interaction_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private TravelDiaries travelDiaries;

    @Column(name = "inter_type")
    private String interType;

    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
}
