package kz.orion.sk_orion_springboot.model.entity;

import jakarta.persistence.*;
import kz.orion.sk_orion_springboot.model.entity.enums.DaysOfWeek;
import kz.orion.sk_orion_springboot.model.entity.enums.TimeClass;
import kz.orion.sk_orion_springboot.model.entity.enums.TypeGroup;
import lombok.Data;

import java.util.List;

@Entity(name = "group_1")
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_group")
    private TypeGroup typeOfGroup;

    @Enumerated(EnumType.STRING)
    @Column(name = "days_of_week")
    private DaysOfWeek daysOfWeek;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_class")
    private TimeClass timeClass;

//    @ManyToMany(mappedBy = "groupList", fetch = FetchType.EAGER)
//    private List<Visitor> visitorList;

    @OneToMany(mappedBy = "group",
                cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Subscription> subscriptionList;

    @ManyToOne(targetEntity = Instructor.class,cascade = CascadeType.MERGE)
    private Instructor instructor;

    private int sizeOfGroup;

    @Override
    public String toString() {
        return id.toString();
    }

    public int getSizeOfGroup() {
        return this.getSubscriptionList().size();
    }
}
