package tripdairy.gotrip.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(
        name = "locations_seq_generator",
        sequenceName = "locations_seq",
        initialValue = 1, allocationSize = 1)
@Getter
@Setter
public class Locations {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "locations_seq_generator")
    @Column(name = "location_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private TravelDiaries travelDiaries;

    private Float latitude;

    private Float longitude;

    @Column(name = "locations_description")
    private String description;
}
