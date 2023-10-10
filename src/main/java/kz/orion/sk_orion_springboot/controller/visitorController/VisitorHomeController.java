package kz.orion.sk_orion_springboot.controller.visitorController;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.controller.LoginController;
import kz.orion.sk_orion_springboot.controller.adminController.notification.NotificationFormController;
import kz.orion.sk_orion_springboot.controller.signUp.VisitorSignUpController;
import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.Subscription;
import kz.orion.sk_orion_springboot.model.entity.Visitor;
import kz.orion.sk_orion_springboot.model.service.SubscriptionService;
import kz.orion.sk_orion_springboot.model.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;


@Controller
@RequiredArgsConstructor
public class VisitorHomeController extends AbstractController {

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

    private final SubscriptionService subscriptionService;

    private final VisitorService visitorService;

    private Visitor visitor;

    @FXML
    private void initialize() {
        visitor = (Visitor) LoginController.loginUser;
        showInfoProfile();
    }

    @FXML
    private void updateProfile() {
        VisitorSignUpController.edit(visitor, this::save);

    }

    public void showInfoProfile() {

        Integer dayOfMonth = visitor.getDateOfBirth().getDayOfMonth();
        Integer month = visitor.getDateOfBirth().getMonthValue();
        Integer year = visitor.getDateOfBirth().getYear();

        lName.setText(visitor.getFirstName());
        lSurname.setText(visitor.getLastName());
        lRole.setText(visitor.getRole().getStr());
        lDateOfBirth.setText(dayOfMonth.toString() + "." + month.toString()
                + "." + year.toString());
        lNumberPhone.setText(visitor.getPhoneNumber());
        lEmail.setText(visitor.getEmail());
        if (visitor.getFileNameImage() != null) {
            Image myImage = new Image(visitor.getFileNameImage());
            profileImage.setImage(myImage);
        }
    }

    private void save(Visitor visitor) {
        visitorService.registerVisitor(visitor);
    }

}
