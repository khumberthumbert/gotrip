package tripdairy.gotrip.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "media_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private TravelDiaries travelDiaries;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "media_description")
    @Lob
    private String description;
}
