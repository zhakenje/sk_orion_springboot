package kz.orion.sk_orion_springboot.controller.adminController;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.controller.info.ViewInfoVisitor;
import kz.orion.sk_orion_springboot.model.entity.Visitor;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import kz.orion.sk_orion_springboot.model.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminVisitorListController extends AbstractController {

    @FXML
    private TextField firstName;

    @FXML
    private Button searchButton;

    @FXML
    private Label message;

    @FXML
    private TableView<Visitor> tableView;

    private final VisitorService visitorService;

    @FXML
    private void searchByFirstName() {
        try {
            tableView.getItems().clear();
            List<Visitor> list = visitorService.searchByFirstName(firstName.getText());
            tableView.getItems().addAll(list);
            message.setText(" ");
        } catch (PosException e) {
            message.setText(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize(){
        tableView.getItems().clear();
        List<Visitor> list = visitorService.findAll();
        tableView.getItems().addAll(list);

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Visitor visitor = tableView.getSelectionModel().getSelectedItem();
                if (visitor == null) {
                }else {
                    ViewInfoVisitor.edit(visitor, this::save);
                }
            }
        });
    }

    @FXML
    private void clear() {
        firstName.setText("");
        tableView.getItems().clear();
        tableView.getItems().addAll(visitorService.findAll());
    }

    private void save(Visitor visitor) {
        visitorService.save(visitor);
    }
}
