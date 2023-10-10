package kz.orion.sk_orion_springboot.model.entity;

import jakarta.persistence.*;
import kz.orion.sk_orion_springboot.model.entity.enums.TypeSubscription;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "statistic")
@Data
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TypeSubscription typeSubscription;

    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Subscription subscription;


}
