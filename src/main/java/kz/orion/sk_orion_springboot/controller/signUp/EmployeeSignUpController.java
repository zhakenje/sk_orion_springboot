package kz.orion.sk_orion_springboot.controller.signUp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kz.orion.sk_orion_springboot.controller.popups.SubscriptionEdit;
import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.enums.InstructorRole;
import kz.orion.sk_orion_springboot.model.entity.enums.Role;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.function.Consumer;

@Component
@Controller
public class EmployeeSignUpController {

    @FXML
    private Label title;

    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private TextField numberPhone;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<InstructorRole> instructorRoleComboBox;

    @FXML
    private TextField experience;

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
    private ImageView profileImage;

    private Instructor instructor;

    private Consumer<Instructor> saveHandler;

    private File fileObject;

    private String pathImage;

    @FXML
    public void close() {
        closeButton.getScene().getWindow().hide();
    }

    public static void addNew(Consumer<Instructor> saveHandler
            , Supplier<InstructorRole[]> supplier) {
        edit(null, saveHandler, supplier);
    }

    public static void edit(Instructor instructor, Consumer<Instructor> saveHandler
            , Supplier<InstructorRole[]> supplier) {
        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(EmployeeSignUpController.class
                    .getClassLoader().getResource("fxml/signUp/signUpEmployee.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);

            EmployeeSignUpController controller = loader.getController();
            controller.init(instructor, saveHandler, supplier);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(Instructor instructor, Consumer<Instructor> saveHandler
            , Supplier<InstructorRole[]> supplier) {

        this.instructor = instructor;
        this.saveHandler = saveHandler;
        instructorRoleComboBox.getItems().addAll(supplier.get());




        if (null == instructor) {
            title.setText("РЕГИСТРАЦИЯ НОВОГО СОТРУДНИКА");
            this.instructor = new Instructor();

        } else {
            title.setText("ИЗМЕНИТЬ ПАРАМЕТРЫ СОТРУДНИКА");
            lastName.setText(this.instructor.getLastName());
            firstName.setText(this.instructor.getFirstName());
            dateOfBirth.setValue(this.instructor.getDateOfBirth());
            numberPhone.setText(this.instructor.getPhoneNumber());
            email.setText(this.instructor.getEmail());
            instructorRoleComboBox.setValue(this.instructor.getInstructorRole());
            experience.setText(this.instructor.getExperience());
            username.setText(this.instructor.getUsername());
            password.setText(this.instructor.getPassword());
            Image image = new Image(this.instructor.getFileNameImage());
            profileImage.setImage(image);
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
                if(fileObject != null)
                {
                    pathImage = fileObject.toURI().toURL().toExternalForm();
                    Image myImage = new Image(pathImage);
                    profileImage.setImage(myImage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void register() {
        try {

            instructor.setLastName(lastName.getText());
            instructor.setFirstName(firstName.getText());
            instructor.setDateOfBirth(dateOfBirth.getValue());
            instructor.setPhoneNumber(numberPhone.getText());
            instructor.setEmail(email.getText());
            instructor.setInstructorRole(instructorRoleComboBox.getValue());
            instructor.setExperience(experience.getText());
            instructor.setUsername(username.getText());
            instructor.setPassword(password.getText());
            instructor.setFileNameImage(pathImage);
            instructor.setRole(Role.INSTRUCTOR);

            addTextLimiter(numberPhone, 11);

            saveHandler.accept(instructor);

            close();

        } catch (PosException e) {
            message.setText(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() < maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }else {
                    throw new PosException("Поле заполнено не правильно!");
                }
            }
        });
    }

}
