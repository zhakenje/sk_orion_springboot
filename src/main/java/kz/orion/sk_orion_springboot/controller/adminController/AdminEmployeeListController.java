package kz.orion.sk_orion_springboot.controller.adminController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.controller.signUp.EmployeeSignUpController;
import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.enums.InstructorRole;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import kz.orion.sk_orion_springboot.model.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class AdminEmployeeListController extends AbstractController {

    @FXML
    private ComboBox<InstructorRole> instructorRoleComboBox = new ComboBox<>();

    @FXML
    private TableView<Instructor> tableView;

    @FXML
    private TextField textForFirstName;

    @FXML
    private Label message;

    private final InstructorService instructorService;


    @FXML
    private void initialize() {
        instructorRoleComboBox.getItems().clear();
        instructorRoleComboBox.setItems(FXCollections.observableArrayList(InstructorRole.values()));
        instructorRoleComboBox.getItems().setAll(InstructorRole.values());


        tableView.getItems().clear();
        tableView.getItems().addAll(instructorService.findAll());
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Instructor instructor = tableView.getSelectionModel().getSelectedItem();
                if (instructor == null) {
                    EmployeeSignUpController.edit(null, this::save, InstructorRole::values);
                }else {
                    EmployeeSignUpController.edit(instructor, this::save, InstructorRole::values);
                }
            }
        });

    }
    @FXML
    private void searchByInstructorRole(){
        tableView.getItems().clear();
        List<Instructor> list = instructorService.searchByInstructorRole(instructorRoleComboBox.getValue());
        tableView.getItems().addAll(list);
    }

    @FXML
    private void searchByFirstName() {
        try {
            tableView.getItems().clear();
            List<Instructor> list = instructorService.searchByFirstName(textForFirstName.getText());
            tableView.getItems().addAll(list);
        }catch (PosException e){
            message.setText(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void addNew(){
        EmployeeSignUpController.addNew(this::save, InstructorRole::values);
    }

    private void save(Instructor instructor) {
        instructorService.save(instructor);
        instructorRoleComboBox.setValue(instructor.getInstructorRole());
        searchByFirstName();
    }

    @FXML
    private void clear() {
        instructorRoleComboBox.setValue(null);
        tableView.getItems().clear();
        tableView.getItems().addAll(instructorService.findAll());
    }


}
