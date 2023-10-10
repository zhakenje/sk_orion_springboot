package kz.orion.sk_orion_springboot.controller.popups;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kz.orion.sk_orion_springboot.StageListener;
import kz.orion.sk_orion_springboot.model.entity.Group;
import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.enums.DaysOfWeek;
import kz.orion.sk_orion_springboot.model.entity.enums.TimeClass;
import kz.orion.sk_orion_springboot.model.entity.enums.TypeGroup;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.function.Consumer;

@Controller
@Component
@RequiredArgsConstructor
public class CreateGroup {

    @FXML
    private Label title;

    @FXML
    private Label message;

    @FXML
    private ComboBox<TypeGroup> typeGroupComboBox = new ComboBox<>();

    @FXML
    private ComboBox<Instructor> instructorComboBox = new ComboBox<>();

    @FXML
    private ComboBox<DaysOfWeek> daysOfWeekComboBox = new ComboBox<>();

    @FXML
    private ComboBox<TimeClass> timeClassComboBox = new ComboBox<>();

    private Group group;

    private Consumer<Group> saveHandler;

    public static void addNew(Consumer<Group> saveHandler, Supplier<TypeGroup[]> typeGroupSupplier
            , Supplier<Instructor[]> instructorSupplier, Supplier<DaysOfWeek[]> daysOfWeekSupplier
            , Supplier<TimeClass[]> timeClassSupplier) {
        edit(null, saveHandler, typeGroupSupplier, instructorSupplier
                , daysOfWeekSupplier, timeClassSupplier);
    }

    public static void edit(Group group, Consumer<Group> saveHandler, Supplier<TypeGroup[]> typeGroupSupplier
            , Supplier<Instructor[]> instructorSupplier, Supplier<DaysOfWeek[]> daysOfWeekSupplier
            , Supplier<TimeClass[]> timeClassSupplier) {

        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(CreateGroup.class.getClassLoader()
                    .getResource("fxml/popups/createGroup.fxml"));
            loader.setControllerFactory(StageListener.getApplicationContext()::getBean);
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);

            CreateGroup controller = loader.getController();
            controller.init(group, saveHandler, typeGroupSupplier, instructorSupplier
                    , daysOfWeekSupplier, timeClassSupplier);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void init(Group group, Consumer<Group> saveHandler, Supplier<TypeGroup[]> typeGroupSupplier
            , Supplier<Instructor[]> instructorSupplier, Supplier<DaysOfWeek[]> daysOfWeekSupplier
            , Supplier<TimeClass[]> timeClassSupplier) {

        this.group = group;
        this.saveHandler = saveHandler;
        typeGroupComboBox.getItems().addAll(typeGroupSupplier.get());
        instructorComboBox.getItems().addAll(instructorSupplier.get());
        daysOfWeekComboBox.getItems().addAll(daysOfWeekSupplier.get());
        timeClassComboBox.getItems().addAll(timeClassSupplier.get());

        if (null == group) {
            title.setText("Создать новую группу");
            this.group = new Group();

        } else {
            title.setText("Изменить параметры группы");
            typeGroupComboBox.setValue(this.group.getTypeOfGroup());
            instructorComboBox.setValue(this.group.getInstructor());
            daysOfWeekComboBox.setValue(this.group.getDaysOfWeek());
            timeClassComboBox.setValue(this.group.getTimeClass());
        }


    }


    @FXML
    private void save() {
        try {

            group.setTypeOfGroup(typeGroupComboBox.getValue());
            group.setInstructor(instructorComboBox.getValue());
            group.setDaysOfWeek(daysOfWeekComboBox.getValue());
            group.setTimeClass(timeClassComboBox.getValue());

            saveHandler.accept(group);

            close();

        } catch (PosException e) {
            message.setText(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void close() {
        timeClassComboBox.getScene().getWindow().hide();
    }

}
