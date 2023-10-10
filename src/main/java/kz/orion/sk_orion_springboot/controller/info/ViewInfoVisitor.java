package kz.orion.sk_orion_springboot.controller.info;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kz.orion.sk_orion_springboot.controller.popups.CreateGroup;
import kz.orion.sk_orion_springboot.model.entity.Visitor;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.function.Consumer;

@Component
@Controller
@RequiredArgsConstructor
public class ViewInfoVisitor {
    @FXML
    private TextField dateOfBirth;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField numberPhone;

    @FXML
    private ImageView profileImage;

    @FXML
    private RadioButton radioFalse;

    @FXML
    private RadioButton radioTrue;

    private Consumer<Visitor> saveHandler;

    private Visitor visitor;

    @FXML
    private void showDoc() {
        ShowDocVisitor.show(this.visitor);
    }

    public static void edit(Visitor visitor, Consumer<Visitor> saveHandler) {
        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(CreateGroup.class.getClassLoader()
                    .getResource("fxml/info/infoVisitor.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);

            ViewInfoVisitor controller = loader.getController();
            controller.init(visitor, saveHandler);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(Visitor visitor, Consumer<Visitor> saveHandler) {
        this.visitor = visitor;
        this.saveHandler = saveHandler;
        if (visitor.getFileNameImage() != null) {
            Image visitorImage = new Image(visitor.getFileNameImage());
            profileImage.setImage(visitorImage);
        }
        firstName.setText(visitor.getFirstName());
        lastName.setText(visitor.getLastName());
        dateOfBirth.setText(visitor.getDateOfBirth().toString());
        numberPhone.setText(visitor.getPhoneNumber());
        email.setText(visitor.getEmail());
        if (!visitor.getCheckDoc()) {
            radioFalse.fire();
        } else {
            radioTrue.fire();
        }
    }

    @FXML
    private void save() {
        try {
            if(radioFalse.isSelected())
            {
                visitor.setCheckDoc(false);
            } else if (radioTrue.isSelected())
            {
                visitor.setCheckDoc(true);
            }
            saveHandler.accept(visitor);
            close();
        } catch (PosException e) {
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void close() {
        numberPhone.getScene().getWindow().hide();
    }


}
