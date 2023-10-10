package kz.orion.sk_orion_springboot.controller.adminController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.controller.LoginController;
import kz.orion.sk_orion_springboot.model.entity.Admin;
import org.springframework.stereotype.Controller;


@Controller
public class AdminHomeController extends AbstractController {

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
    private void initialize() {
        showInfoProfile();
    }

    @FXML
    private void updateProfile() {

    }

    public void showInfoProfile() {

        Admin admin = (Admin) LoginController.loginUser;

        Integer dayOfMonth = admin.getDateOfBirth().getDayOfMonth();
        Integer month = admin.getDateOfBirth().getMonthValue();
        Integer year = admin.getDateOfBirth().getYear();

        lName.setText(admin.getFirstName());
        lSurname.setText(admin.getLastName());
        lRole.setText(admin.getRole().getStr());
        lDateOfBirth.setText(dayOfMonth.toString() + "." + month.toString()
                + "." + year.toString());
        lNumberPhone.setText(admin.getPhoneNumber());
        lEmail.setText(admin.getEmail());
        if (admin.getFileNameImage() != null) {
            Image myImage = new Image(admin.getFileNameImage());
            profileImage.setImage(myImage);
        }

    }

}
