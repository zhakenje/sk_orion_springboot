package kz.orion.sk_orion_springboot.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "visitor")
@Data
@Table(name = "visitor")
public class Visitor extends BaseEntity{

    private String fileNameDoc;

    private Boolean checkDoc = false;

    @OneToMany(mappedBy = "visitor",
            cascade = CascadeType.MERGE)
    private List<Subscription> subscriptionList;

//    @ManyToMany(targetEntity = Group.class,
//            cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    @JoinTable(name = "visitor_group",
//              joinColumns = {@JoinColumn(name = "visitor_id", referencedColumnName = "username")},
//              inverseJoinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")})
//    private List<Group> groupList;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
