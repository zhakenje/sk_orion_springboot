package kz.orion.sk_orion_springboot.controller.instructorController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.controller.LoginController;
import kz.orion.sk_orion_springboot.model.entity.Group;
import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.Visitor;
import kz.orion.sk_orion_springboot.model.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class InstructorGroupsController extends AbstractController {
    @FXML
    private TableView<Group> tableView;

    @FXML
    private Label title;

    private final GroupService groupService;

    private Instructor instructor;

    @FXML
    private void initialize() {
        instructor = (Instructor) LoginController.loginUser;
        tableView.getItems().clear();
        tableView.getItems().addAll(groupService.searchByInstructorGroups(instructor));
    }
}
