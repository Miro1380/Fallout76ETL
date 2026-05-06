package Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="wasteland_items")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalID;

    private String name;
    private String type;
    private Integer level;
    private Double weight;
    private String rarity;

    @Column
    @CreatedDate
    private LocalDateTime migrationDate;
}
