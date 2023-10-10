package kz.orion.sk_orion_springboot.model.entity;

import jakarta.persistence.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import kz.orion.sk_orion_springboot.model.entity.enums.InstructorRole;
import lombok.Data;
import javafx.scene.control.Label;

import java.awt.*;
import java.util.List;

@Entity(name = "instructor")
@Data
@Table(name = "instructor")
public class Instructor extends BaseEntity{

    private String experience;

    @Enumerated(EnumType.STRING)
    private InstructorRole instructorRole;

    @OneToMany(targetEntity = Group.class,mappedBy = "instructor",
            cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Group> groupList;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }


}
