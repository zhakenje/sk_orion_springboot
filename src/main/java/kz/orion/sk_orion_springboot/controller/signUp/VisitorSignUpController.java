package kz.orion.sk_orion_springboot.controller.signUp;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kz.orion.sk_orion_springboot.StageListener;
import kz.orion.sk_orion_springboot.controller.popups.SubscriptionEdit;
import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.Visitor;
import kz.orion.sk_orion_springboot.model.entity.enums.InstructorRole;
import kz.orion.sk_orion_springboot.model.entity.enums.Role;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import kz.orion.sk_orion_springboot.model.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.function.Consumer;

@Component
@Controller
@RequiredArgsConstructor
public class VisitorSignUpController {

    private final VisitorService visitorService;

    @FXML
    private Label title;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private TextField numberPhone;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label message;

    @FXML
    private Button registerButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button imageButton;

    @FXML
    private Button docImageButton;

    @FXML
    private ImageView profileImage;

    @FXML
    private ImageView docImage;

    private File fileObject;

    private String pathProfileImage;

    private String pathDocImage;

    private Consumer<Visitor> saveHandler;

    private Visitor visitor;

    @FXML
    public void close() {
        closeButton.getScene().getWindow().hide();
    }

    @FXML
    private void register() {
        try {

            visitor.setLastName(lastName.getText());
            visitor.setFirstName(firstName.getText());
            visitor.setDateOfBirth(dateOfBirth.getValue());
            visitor.setPhoneNumber(numberPhone.getText());
            visitor.setEmail(email.getText());
            visitor.setUsername(username.getText());
            visitor.setPassword(password.getText());
            if (pathProfileImage != null) {
                visitor.setFileNameImage(pathProfileImage);
            }
            if (pathDocImage != null) {
                visitor.setFileNameDoc(pathDocImage);
            }
            visitor.setRole(Role.VISITOR);

            saveHandler.accept(visitor);

            close();

        } catch (PosException e) {
            message.setText(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addImage() {
        imageButton.setOnAction(actionEvent -> {
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
                pathProfileImage = fileObject.toURI().toURL().toExternalForm();
                Image myImage = new Image(pathProfileImage);
                profileImage.setImage(myImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void addDocImage() {
        docImageButton.setOnAction(actionEvent -> {
            Node source = (Node) actionEvent.getSource();
            Stage primaryStage = (Stage) source.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image files (*.jpg)" +
                    ", (*.png)", "*.jpg", "*.png", "*.jpeg");
            fileChooser.getExtensionFilters().add(imageFilter);
            fileChooser.getExtensionFilters().addAll(imageFilter);
            fileChooser.setTitle("Выборать файл для прикрепления");
            fileObject = fileChooser.showOpenDialog(primaryStage);
            try {
                pathDocImage = fileObject.toURI().toURL().toExternalForm();
                Image myImage = new Image(pathDocImage);
                docImage.setImage(myImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void addNew(Consumer<Visitor> saveHandler) {
        edit(null, saveHandler);
    }

    public static void edit(Visitor visitor, Consumer<Visitor> saveHandler) {
        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(VisitorSignUpController.class
                    .getClassLoader().getResource("fxml/signUp/signUpVisitorN.fxml"));
            loader.setControllerFactory(StageListener.getApplicationContext()::getBean);
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);

            VisitorSignUpController controller = loader.getController();
            controller.init(visitor, saveHandler);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(Visitor visitor, Consumer<Visitor> saveHandler) {

        this.visitor = visitor;
        this.saveHandler = saveHandler;

        if (null == visitor) {
            title.setText("РЕГИСТРАЦИЯ НОВОГО ПОСЕТИТЕЛЯ");
            this.visitor = new Visitor();
            registerButton.setText("Регистрация");

        } else {
            title.setText("ИЗМЕНИТЬ ПАРАМЕТРЫ ПОСЕТИТЕЛЯ");
            lastName.setText(this.visitor.getLastName());
            firstName.setText(this.visitor.getFirstName());
            dateOfBirth.setValue(this.visitor.getDateOfBirth());
            numberPhone.setText(this.visitor.getPhoneNumber());
            email.setText(this.visitor.getEmail());
            username.setText(this.visitor.getUsername());
            password.setText(this.visitor.getPassword());
            if (visitor.getFileNameImage() != null) {
                Image image = new Image(this.visitor.getFileNameImage());
                profileImage.setImage(image);
            }
            if (visitor.getFileNameDoc() != null)
            {
                Image image = new Image(this.visitor.getFileNameImage());
                docImage.setImage(image);
            }
            registerButton.setText("Сохранить");
        }


    }

    public static void show() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(VisitorSignUpController.class.getClassLoader()
                    .getResource("fxml/signUp/signUpVisitor.fxml"));
            loader.setControllerFactory(StageListener.getApplicationContext()::getBean);
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
