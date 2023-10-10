package kz.orion.sk_orion_springboot.model.entity;

import jakarta.persistence.*;
import kz.orion.sk_orion_springboot.model.entity.enums.TypeSubscription;
import kz.orion.sk_orion_springboot.utils.FormatUtils;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "subscription")
@Data
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateOfPurchase;

    private LocalDate dateOfValidity;

    @Enumerated(EnumType.STRING)
    private TypeSubscription typeSubscription;

    private int price;

    private int numberOfVisits;

    private String qrCode;

    private boolean groupTraining;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Visitor visitor;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Group group;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.MERGE
            , fetch = FetchType.EAGER)
    private List<Statistic> statisticList;

    public String getPriceStr() {
        return FormatUtils.formatNumber(price);
    }

    public void addStatisticToSubscription(Statistic statistic) {
        if (statisticList == null) {
            statisticList = new ArrayList<>();
        }
        statisticList.add(statistic);
        statistic.setSubscription(this);
    }


}
