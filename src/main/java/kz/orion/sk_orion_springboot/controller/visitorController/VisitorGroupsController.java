package kz.orion.sk_orion_springboot.controller.visitorController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.controller.LoginController;
import kz.orion.sk_orion_springboot.model.entity.Group;
import kz.orion.sk_orion_springboot.model.entity.Subscription;
import kz.orion.sk_orion_springboot.model.entity.Visitor;
import kz.orion.sk_orion_springboot.model.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class VisitorGroupsController extends AbstractController {

    @FXML
    private TableView<Group> tableView;

    @FXML
    private Label title;

    private final GroupService groupService;

    private Visitor vs;

    @FXML
    private void initialize() {
        vs = (Visitor) LoginController.loginUser;
        tableView.getItems().clear();
        tableView.getItems().addAll(groupService.findAll());
        List<Group> groupListThisVisitor = groupService.findAllByVisitor(vs);

        tableView.setRowFactory(tableView -> new TableRow<Group>() {
            @Override
            protected void updateItem(Group group, boolean b) {
                super.updateItem(group, b);
                if (group == null) {
                    setStyle("");
                } else {
                    for (Subscription sb : group.getSubscriptionList()) {
                        if (sb.getVisitor().toString().equals(vs.toString())) {
                            setStyle("-fx-background-color: #00FF00");
                            break;
                        }
                    }

                }
            }
        });
    }
}
