package kz.orion.sk_orion_springboot.controller.visitorController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.enums.ViewsInfo;
import kz.orion.sk_orion_springboot.model.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class VisitorAboutUsController extends AbstractController {

    @FXML
    private TilePane container;

    @FXML
    private AnchorPane infoIns;

    @FXML
    private AnchorPane infoSK;

    @FXML
    private ScrollPane scrollContainer;

    @FXML
    private ComboBox<ViewsInfo> selectView;

    @FXML
    private Label title;

    private final InstructorService instructorService;

    @FXML
    private void initialize(){
        container.getChildren().clear();
        instructorService.findAll().stream().forEach(instructor -> container.getChildren()
                .add(new InstructorItem(instructor)));
//        scrollBarV.setMin(0);
//        scrollBarV.setMax(260);
//        scrollBarV.setValue(100);
        scrollContainer.setContent(container);

        infoIns.setVisible(false);
        infoSK.setVisible(true);

        selectView.getItems().clear();
        selectView.setItems(FXCollections.observableArrayList(ViewsInfo.values()));
        selectView.getItems().setAll(ViewsInfo.values());

    }

    @FXML
    private void selectInfo() {
        if (selectView.getValue() == ViewsInfo.INFO_SK){
            infoSK.setVisible(true);
            infoIns.setVisible(false);
        }else {
            infoIns.setVisible(true);
            infoSK.setVisible(false);
        }

    }

    private class InstructorItem extends VBox {

        private ImageView myImage;

        private Label FIO;

        private Label FIOLabel;

        private Label instructorRole;

        private Label instructorRoleLabel;

        private Label age;

        private Label ageLabel;

        private Label experience;

        private Label experienceLabel;

        private GridPane gridPane;

        public InstructorItem(Instructor instructor) {
            FIOLabel = new Label();
            FIOLabel.setText("ФИО");
            instructorRoleLabel = new Label();
            instructorRoleLabel.setText("Направление");
            ageLabel = new Label();
            ageLabel.setText("Год рождения");
            experienceLabel = new Label();
            experienceLabel.setText("Стаж работы");
            Image image = new Image(instructor.getFileNameImage());
            myImage = new ImageView();
            myImage.setImage(image);
            myImage.setFitHeight(198);
            myImage.setFitWidth(198);
            FIO = new Label();
            FIO.setText(instructor.toString());
            instructorRole = new Label();
            instructorRole.setText(instructor.getInstructorRole().toString());
            age = new Label();
            age.setText(instructor.getDateOfBirth().toString());
            experience = new Label();
            experience.setText(instructor.getExperience());
            gridPane = new GridPane();
            gridPane.setPadding(new Insets(5, 5, 5, 5));
            gridPane.setVgap(8);
            gridPane.setHgap(10);
            gridPane.add(FIOLabel, 0, 0);
            gridPane.add(instructorRoleLabel, 0, 1);
            gridPane.add(ageLabel, 0, 2);
            gridPane.add(experienceLabel, 0, 3);
            gridPane.add(FIO, 1, 0);
            gridPane.add(instructorRole, 1, 1);
            gridPane.add(age, 1, 2);
            gridPane.add(experience, 1, 3);
//            gridPane.setPrefWidth();

            getChildren().addAll(myImage, gridPane);
            getStyleClass().addAll("info-item");
        }
    }

}
