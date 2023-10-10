package kz.orion.sk_orion_springboot.controller.visitorController;

import com.google.zxing.WriterException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.controller.LoginController;
import kz.orion.sk_orion_springboot.controller.adminController.notification.NotificationFormController;
import kz.orion.sk_orion_springboot.controller.info.ElectronSubscriptionShow;
import kz.orion.sk_orion_springboot.controller.popups.SubscriptionEdit;
import kz.orion.sk_orion_springboot.model.entity.Group;
import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.Subscription;
import kz.orion.sk_orion_springboot.model.entity.Visitor;
import kz.orion.sk_orion_springboot.model.entity.enums.TypeSubscription;
import kz.orion.sk_orion_springboot.model.service.InstructorService;
import kz.orion.sk_orion_springboot.model.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.controlsfx.control.Notifications;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class VisitorMySubscriptionsController extends AbstractController {

    @FXML
    private ComboBox<TypeSubscription> typeSubscription = new ComboBox<>();

    @FXML
    private TableView<Subscription> tableView;

    private final SubscriptionService subscriptionService;

    private Visitor vs;

    @FXML
    private void initialize() {
        vs = (Visitor) LoginController.loginUser;
        for (Subscription sb : subscriptionService.returnAllSubscriptionForVisitor(vs)) {
            if (sb.getDateOfValidity().isBefore(LocalDate.now())) {
                NotificationFormController.showNotificationsErr(Pos.BASELINE_RIGHT, "Сообщение об истечении срока"
                        , "Срок действия абонемента с id = " + sb.getId().toString()
                                + " истек!");
            } else if (sb.getDateOfValidity().equals(LocalDate.now().plusDays(3))) {
                NotificationFormController.showNotificationsWarning(Pos.BASELINE_RIGHT, "Сообщение об истечении срока"
                        , "Срок действия абонемента с id = " + sb.getId().toString()
                                + " истекает через 3 дня!");
            } else if (sb.getDateOfValidity().equals(LocalDate.now().plusDays(2))) {
                NotificationFormController.showNotificationsWarning(Pos.BASELINE_RIGHT, "Сообщение об истечении срока"
                        , "Срок действия абонемента с id = " + sb.getId().toString()
                                + " истекает через 2 дня!");
            } else if (sb.getDateOfValidity().equals(LocalDate.now().plusDays(1))) {
                NotificationFormController.showNotificationsWarning(Pos.BASELINE_RIGHT, "Сообщение об истечении срока"
                        , "Срок действия абонемента с id = " + sb.getId().toString()
                                + " истекает через 1 дня!");
            } else if (sb.getDateOfValidity().equals(LocalDate.now())) {
                NotificationFormController.showNotificationsWarning(Pos.BASELINE_RIGHT, "Сообщение об истечении срока"
                        , "Срок действия абонемента с id = " + sb.getId().toString()
                                + " истекает сегодня!");
            }
        }

        if (vs.getCheckDoc()) {
            NotificationFormController.showNotificationsSuccess(Pos.BASELINE_RIGHT, "Сообщение об одобрении"
                    , "Администратор одобрил ваш документ, вы можете купить абонемент на бассейн!");
        }

        typeSubscription.getItems().clear();
        typeSubscription.setItems(FXCollections.observableArrayList(TypeSubscription.values()));
        typeSubscription.getItems().setAll(TypeSubscription.values());

        tableView.getItems().clear();
        tableView.getItems().addAll(subscriptionService.returnAllSubscriptionForVisitor(vs));

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Subscription subscription = tableView.getSelectionModel().getSelectedItem();
                if (subscription == null) {
                    SubscriptionEdit.edit(null, this::save, TypeSubscription::values);
                } else {
                    ElectronSubscriptionShow.show(subscription);
                }
            }
        });

        tableView.setRowFactory(tableView -> new TableRow<Subscription>() {
            @Override
            protected void updateItem(Subscription subscription, boolean b) {
                super.updateItem(subscription, b);
                if (subscription == null) {
                    setStyle("");
                } else if (subscription.getDateOfValidity().isBefore(LocalDate.now()) ||
                        subscription.getNumberOfVisits() == 0) {
                    setStyle("-fx-background-color: #FF4500");
                } else if (subscription.getDateOfValidity().equals(LocalDate.now()) ||
                        subscription.getDateOfValidity().equals(LocalDate.now().plusDays(3)) ||
                        subscription.getDateOfValidity().equals(LocalDate.now().plusDays(2)) ||
                        subscription.getDateOfValidity().equals(LocalDate.now().plusDays(1))) {
                    setStyle("-fx-background-color: #FFFF00");
                } else if (subscription.getDateOfValidity().isAfter(LocalDate.now())) {
                    setStyle("-fx-background-color: #00FF00");
                }
            }
        });

    }

    @FXML
    private void search() {
        tableView.getItems().clear();
        List<Subscription> list = subscriptionService.search(vs, typeSubscription.getValue());
        tableView.getItems().addAll(list);
    }

    @FXML
    private void clear() {
        typeSubscription.setValue(null);
        tableView.getItems().clear();
        tableView.getItems().clear();
        tableView.getItems().addAll(subscriptionService.returnAllSubscriptionForVisitor(vs));
    }

    @FXML
    private void addNew() {
        SubscriptionEdit.addNew(this::save, TypeSubscription::values);
    }

    private void save(Subscription subscription) {
        subscriptionService.save(subscription);
        typeSubscription.setValue(subscription.getTypeSubscription());
        search();
    }

}
