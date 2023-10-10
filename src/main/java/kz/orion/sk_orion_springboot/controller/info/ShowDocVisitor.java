package kz.orion.sk_orion_springboot.controller.info;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import kz.orion.sk_orion_springboot.controller.instructorController.InstructorController;
import kz.orion.sk_orion_springboot.model.entity.Visitor;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
public class ShowDocVisitor {
    @FXML
    private ImageView docView;
    @FXML
    private Label message;

    @FXML
    void close() {
        docView.getScene().getWindow().hide();
    }

    public static void show(Visitor visitor) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(ShowDocVisitor.class.getClassLoader()
                    .getResource("fxml/info/showDocVisitor.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.show();

            ShowDocVisitor controller = loader.getController();
            controller.init(visitor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(Visitor visitor){
        try {
            if (visitor.getFileNameDoc() != null) {
                Image docImage = new Image(visitor.getFileNameDoc());
                docView.setImage(docImage);
            } else {
                throw new PosException("Документ не прикреплен!");
            }
        } catch (PosException e) {
            message.setText(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
