package kz.orion.sk_orion_springboot.controller.instructorController;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.controller.LoginController;
import kz.orion.sk_orion_springboot.model.entity.Instructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import java.io.File;


@Controller
@RequiredArgsConstructor
public class InstructorHomeController extends AbstractController {

    @FXML
    private Label lName;

    @FXML
    private Label lSurname;

    @FXML
    private Label lRole;

    @FXML
    private Label lDateOfBirth;

    @FXML
    private Label lNumberPhone;

    @FXML
    private Label lEmail;

    @FXML
    private ImageView profileImage;

    @FXML
    private Button selectImage;

    @FXML
    private Label textDir;

    private File fileObject;

    @FXML
    private void initialize() {
        showInfoProfile();
    }

    @FXML
    private void updateProfile() {

    }

    @FXML
    private void addImage() {
        selectImage.setOnAction(actionEvent -> {
            Node source = (Node) actionEvent.getSource();
            Stage primaryStage = (Stage) source.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image files (*.jpg)" +
                    ", (*.png)", "*.jpg", "*.png", "*.jpeg");
            fileChooser.getExtensionFilters().add(imageFilter);
            fileChooser.getExtensionFilters().addAll(imageFilter);
            fileChooser.setTitle("Выбор фото для профиля");
            fileObject = fileChooser.showOpenDialog(primaryStage);
            try {
                textDir.setText(fileObject.getPath());
                Image myImage = new Image(fileObject.toURI().toURL().toExternalForm());
                profileImage.setImage(myImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void showInfoProfile() {

        Instructor instructor = (Instructor) LoginController.loginUser;

        Integer dayOfMonth = instructor.getDateOfBirth().getDayOfMonth();
        Integer month = instructor.getDateOfBirth().getMonthValue();
        Integer year = instructor.getDateOfBirth().getYear();

        lName.setText(instructor.getFirstName());
        lSurname.setText(instructor.getLastName());
        lRole.setText(instructor.getRole().getStr());
        lDateOfBirth.setText(dayOfMonth.toString() + "." + month.toString()
                + "." + year.toString());
        lNumberPhone.setText(instructor.getPhoneNumber());
        lEmail.setText(instructor.getEmail());

    }


}
