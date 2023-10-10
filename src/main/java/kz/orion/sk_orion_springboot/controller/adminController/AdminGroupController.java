package kz.orion.sk_orion_springboot.controller.adminController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.controller.popups.CreateGroup;
import kz.orion.sk_orion_springboot.model.entity.Group;
import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.enums.DaysOfWeek;
import kz.orion.sk_orion_springboot.model.entity.enums.TimeClass;
import kz.orion.sk_orion_springboot.model.entity.enums.TypeGroup;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import kz.orion.sk_orion_springboot.model.service.GroupService;
import kz.orion.sk_orion_springboot.model.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminGroupController extends AbstractController {

    @FXML
    private ComboBox<TypeGroup> typeOfGroupComboBox = new ComboBox<>();

    @FXML
    private Button searchButton;

    @FXML
    private Button createGroupBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private TableView<Group> tableView;

    @FXML
    private Label message;

    private final GroupService groupService;

    private final InstructorService instructorService;

    @FXML
    private void initialize() {
        typeOfGroupComboBox.getItems().clear();
        typeOfGroupComboBox.setItems(FXCollections.observableArrayList(TypeGroup.values()));
        typeOfGroupComboBox.getItems().setAll(TypeGroup.values());

        tableView.getItems().clear();
        tableView.getItems().addAll(groupService.findAll());
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Group group = tableView.getSelectionModel().getSelectedItem();
                if (group == null) {
                    CreateGroup.edit(null, this::save, TypeGroup::values, this::getUserByRole
                            , DaysOfWeek::values, TimeClass::values);
                } else {
                    CreateGroup.edit(group, this::save, TypeGroup::values, this::getUserByRole
                            , DaysOfWeek::values, TimeClass::values);
                }
            }
        });

    }

    @FXML
    private void createGroup(){
        CreateGroup.addNew(this::save, TypeGroup::values, this::getUserByRole
                , DaysOfWeek::values, TimeClass::values) ;
    }

    @FXML
    private void clear(){
        typeOfGroupComboBox.setValue(null);
        tableView.getItems().clear();
        tableView.getItems().addAll(groupService.findAll());
    }

    @FXML
    private void searchByTypeGroup(){
        try {
            tableView.getItems().clear();
            List<Group> list = groupService.searchByTypeGroup(typeOfGroupComboBox.getValue());
            tableView.getItems().addAll(list);
        }catch (PosException e){
            message.setText(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void save(Group group) {
        groupService.save(group);
        typeOfGroupComboBox.setValue(group.getTypeOfGroup());
    }

    private Instructor[] getUserByRole(){
        List<Instructor> instructorList = instructorService.findAllByRoleInstructor();
        Instructor[] instructors = instructorList.toArray(new Instructor[instructorList.size()]);
        return instructors;
    }
}
