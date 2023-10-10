package kz.orion.sk_orion_springboot.controller.popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kz.orion.sk_orion_springboot.StageListener;
import kz.orion.sk_orion_springboot.controller.LoginController;
import kz.orion.sk_orion_springboot.model.entity.Group;
import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.Subscription;
import kz.orion.sk_orion_springboot.model.entity.Visitor;
import kz.orion.sk_orion_springboot.model.entity.enums.InstructorRole;
import kz.orion.sk_orion_springboot.model.entity.enums.TypeGroup;
import kz.orion.sk_orion_springboot.model.entity.enums.TypeSubscription;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import kz.orion.sk_orion_springboot.model.service.GroupService;
import kz.orion.sk_orion_springboot.model.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Controller
@Component
@RequiredArgsConstructor
public class SubscriptionEdit {

    @FXML
    private ComboBox<TypeSubscription> typeSubscriptionComboBox;

    @FXML
    private Label title;

    @FXML
    private Label message;

    @FXML
    private DatePicker dateOfPurchase;

    @FXML
    private DatePicker dateOfValidity;

    @FXML
    private TextField price;

    @FXML
    private TextField daysClasses;

    @FXML
    private ComboBox<Group> groupComboBox;

    @FXML
    private ComboBox<Instructor> instructorsComboBox;

    @FXML
    private RadioButton radioFalse;

    @FXML
    private RadioButton radioTrue;

    @FXML
    private TextField sumVisitorsInGroup;

    @FXML
    private TextField timeClasses;

    @FXML
    private AnchorPane groupAnchorPane;

    private Subscription subscription;

    private Consumer<Subscription> saveHandler;

    private final InstructorService instructorService;

    private final GroupService groupService;

    private Visitor vs = null;

    LocalDate d1 = null;
    LocalDate d2 = null;

    public static void addNew(Consumer<Subscription> saveHandler
            , Supplier<TypeSubscription[]> supplier) {
        edit(null, saveHandler, supplier);
    }

    public static void edit(Subscription subscription, Consumer<Subscription> saveHandler
            , Supplier<TypeSubscription[]> supplier) {
        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(SubscriptionEdit.class
                    .getClassLoader().getResource("fxml/popups/subscriptionEdit.fxml"));
            loader.setControllerFactory(StageListener.getApplicationContext()::getBean);
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);

            SubscriptionEdit controller = loader.getController();
            controller.init(subscription, saveHandler, supplier);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void save() {
        try {
            if (!vs.getCheckDoc()) {
                if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_MONTH_POOL
                        || typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_DAY_POOL) {
                    throw new PosException("Пожалуйста, прикрепите документ к профилю!");
                } else if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_MONTH_GYM
                        || typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_DAY_GYM) {
                    if (ChronoUnit.YEARS.between(d1, d2) < 14) {
                        throw new PosException("Вам еще не исполнилось 14 лет(для тренажерного зала)!");
                    } else {
                        subscription.setTypeSubscription(typeSubscriptionComboBox.getValue());
                        subscription.setDateOfPurchase(dateOfPurchase.getValue());
                        subscription.setDateOfValidity(dateOfValidity.getValue());
                        subscription.setPrice(Integer.parseInt(price.getText()));
                        subscription.setVisitor(vs);
                        if (radioTrue.isSelected()) {
                            if (groupComboBox.getValue() == null) {
                                throw new PosException("Выберите группу");
                            } else {
                                subscription.setGroup(groupComboBox.getValue());
                            }
                        }
                    }
                }
            } else {
                subscription.setTypeSubscription(typeSubscriptionComboBox.getValue());
                subscription.setDateOfPurchase(dateOfPurchase.getValue());
                subscription.setDateOfValidity(dateOfValidity.getValue());
                subscription.setPrice(Integer.parseInt(price.getText()));
                subscription.setVisitor(vs);
                if (radioTrue.isSelected()) {
                    if (groupComboBox.getValue() == null) {
                        throw new PosException("Выберите группу");
                    } else {
                        subscription.setGroup(groupComboBox.getValue());
                    }
                }
            }

            String qrFileName = UUID.randomUUID().toString();
            File file = new File("src/main/resources/QRimage/" + qrFileName + ".png");
            while (file.exists()) {
                qrFileName = UUID.randomUUID().toString();
                file = new File("src/main/resources/QRimage/" + qrFileName + ".png");
            }
            subscription.setQrCode(file.getAbsolutePath());

            saveHandler.accept(subscription);

            close();

        } catch (PosException e) {
            message.setText(e.getMessage());
        } catch (NumberFormatException e) {
            message.setText("Пожалуйста, введите цену цифрой!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void close() {
        price.getScene().getWindow().hide();
    }

    private void init(Subscription subscription, Consumer<Subscription> saveHandler
            , Supplier<TypeSubscription[]> supplier) {

        this.subscription = subscription;
        this.saveHandler = saveHandler;
        typeSubscriptionComboBox.getItems().addAll(supplier.get());
        vs = (Visitor) LoginController.loginUser;
        d1 = LocalDate.parse(vs.getDateOfBirth().toString());
        d2 = LocalDate.parse(LocalDate.now().toString());

        groupAnchorPane.setVisible(false);
        radioFalse.fire();


        if (null == subscription) {
            title.setText("Купить новый абонемент");
            this.subscription = new Subscription();

        } else {
            title.setText("Изменить параметры абонемента");
        }

        typeSubscriptionComboBox.setValue(this.subscription.getTypeSubscription());

        typeSubscriptionComboBox.setOnAction(actionEvent -> {
            if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_MONTH_GYM) {
                dateOfPurchase.setValue(LocalDate.now());
                dateOfValidity.setValue(LocalDate.now().plusMonths(2));
                if (ChronoUnit.YEARS.between(d1, d2) <= 14) {
                    price.setText(String.valueOf(650));
                } else {
                    price.setText(String.valueOf(1000));
                }
            } else if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_MONTH_POOL) {
                dateOfPurchase.setValue(LocalDate.now());
                dateOfValidity.setValue(LocalDate.now().plusMonths(2));
                if (ChronoUnit.YEARS.between(d1, d2) <= 14) {
                    price.setText(String.valueOf(700));
                } else {
                    price.setText(String.valueOf(1300));
                }
            } else if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_DAY_GYM) {
                dateOfPurchase.setValue(LocalDate.now());
                dateOfValidity.setValue(LocalDate.now().plusDays(3));
                if (ChronoUnit.YEARS.between(d1, d2) <= 14) {
                    price.setText(String.valueOf(65));
                } else {
                    price.setText(String.valueOf(100));
                }
            } else if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_DAY_POOL) {
                dateOfPurchase.setValue(LocalDate.now());
                dateOfValidity.setValue(LocalDate.now().plusDays(3));
                if (ChronoUnit.YEARS.between(d1, d2) <= 14) {
                    price.setText(String.valueOf(70));
                } else {
                    price.setText(String.valueOf(130));
                }
            }
        });

    }

    @FXML
    private void selectInstructor(ActionEvent event) {

        List<Group> groupList = groupService.searchByInstructorGroups(instructorsComboBox.getValue());
        List<Group> groupListKids = new ArrayList<>();
        List<Group> groupListAdult = new ArrayList<>();


        try {
            if (ChronoUnit.YEARS.between(d1, d2) <= 14) {
                for (Group gr : groupList) {
                    if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_MONTH_GYM) {
                        if (gr.getTypeOfGroup().toString().equals(TypeGroup.TYPE_GROUP_GYM_KIDS.toString())) {
                            groupListKids.add(gr);
                        }
                    } else if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_MONTH_POOL) {
                        if (gr.getTypeOfGroup().toString().equals(TypeGroup.TYPE_GROUP_SWIM_KIDS.toString())) {
                            groupListKids.add(gr);
                        }
                    }
                }
                groupComboBox.getItems().clear();
                groupComboBox.getItems().addAll(groupListKids);
            } else {
                for (Group gr : groupList) {
                    if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_MONTH_GYM) {
                        if (gr.getTypeOfGroup().toString().equals(TypeGroup.TYPE_GROUP_GYM.toString())) {
                            groupListAdult.add(gr);
                        }
                    } else if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_MONTH_POOL) {
                        if (gr.getTypeOfGroup().toString().equals(TypeGroup.TYPE_GROUP_SWIM.toString())) {
                            groupListAdult.add(gr);
                        }
                    }
                }
                groupComboBox.getItems().clear();
                groupComboBox.getItems().addAll(groupListAdult);
            }
        } catch (NullPointerException e) {
            message.setText("У данного инструктора нет группы которая соответсвует вашей возрастной категории");
        }

    }

    @FXML
    private void groupFalse(ActionEvent event) {
        if (radioFalse.isSelected()) {
            groupAnchorPane.setVisible(false);
            if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_MONTH_GYM) {
                dateOfPurchase.setValue(LocalDate.now());
                dateOfValidity.setValue(LocalDate.now().plusMonths(2));
                if (ChronoUnit.YEARS.between(d1, d2) <= 14) {
                    price.setText(String.valueOf(650));
                } else {
                    price.setText(String.valueOf(1000));
                }
                instructorsComboBox.getItems().clear();
                instructorsComboBox.getItems().addAll(instructorService
                        .searchByInstructorRole(InstructorRole.INSTRUCTOR_GYM));
            } else if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_MONTH_POOL) {
                dateOfPurchase.setValue(LocalDate.now());
                dateOfValidity.setValue(LocalDate.now().plusMonths(2));
                if (ChronoUnit.YEARS.between(d1, d2) <= 14) {
                    price.setText(String.valueOf(700));
                } else {
                    price.setText(String.valueOf(1300));
                }
                instructorsComboBox.getItems().clear();
                instructorsComboBox.getItems().addAll(instructorService
                        .searchByInstructorRole(InstructorRole.INSTRUCTOR_SWIM));
            } else if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_DAY_GYM) {
                dateOfPurchase.setValue(LocalDate.now());
                dateOfValidity.setValue(LocalDate.now().plusDays(3));
                if (ChronoUnit.YEARS.between(d1, d2) <= 14) {
                    price.setText(String.valueOf(65));
                } else {
                    price.setText(String.valueOf(100));
                }
                groupAnchorPane.setVisible(false);
            } else if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_DAY_POOL) {
                dateOfPurchase.setValue(LocalDate.now());
                dateOfValidity.setValue(LocalDate.now().plusDays(3));
                if (ChronoUnit.YEARS.between(d1, d2) <= 14) {
                    price.setText(String.valueOf(70));
                } else {
                    price.setText(String.valueOf(130));
                }
                groupAnchorPane.setVisible(false);
            }
        }
    }

    @FXML
    private void groupTrue(ActionEvent event) {

        if (radioTrue.isSelected()) {
            groupAnchorPane.setVisible(true);
            if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_MONTH_GYM) {
                dateOfPurchase.setValue(LocalDate.now());
                dateOfValidity.setValue(LocalDate.now().plusMonths(2));
                if (ChronoUnit.YEARS.between(d1, d2) <= 14) {
                    price.setText(String.valueOf(750));
                } else {
                    price.setText(String.valueOf(1150));
                }
                instructorsComboBox.getItems().clear();
                instructorsComboBox.getItems().addAll(instructorService
                        .searchByInstructorRole(InstructorRole.INSTRUCTOR_GYM));
            } else if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_MONTH_POOL) {
                dateOfPurchase.setValue(LocalDate.now());
                dateOfValidity.setValue(LocalDate.now().plusMonths(2));
                if (ChronoUnit.YEARS.between(d1, d2) <= 14) {
                    price.setText(String.valueOf(800));
                } else {
                    price.setText(String.valueOf(1450));
                }
                instructorsComboBox.getItems().clear();
                instructorsComboBox.getItems().addAll(instructorService
                        .searchByInstructorRole(InstructorRole.INSTRUCTOR_SWIM));
            } else if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_DAY_GYM) {
                dateOfPurchase.setValue(LocalDate.now());
                dateOfValidity.setValue(LocalDate.now().plusDays(3));
                if (ChronoUnit.YEARS.between(d1, d2) <= 14) {
                    price.setText(String.valueOf(65));
                } else {
                    price.setText(String.valueOf(100));
                }
                groupAnchorPane.setVisible(false);
            } else if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_DAY_POOL) {
                dateOfPurchase.setValue(LocalDate.now());
                dateOfValidity.setValue(LocalDate.now().plusDays(3));
                if (ChronoUnit.YEARS.between(d1, d2) <= 14) {
                    price.setText(String.valueOf(70));
                } else {
                    price.setText(String.valueOf(130));
                }
                groupAnchorPane.setVisible(false);
            }
        }
    }

    @FXML
    private void selectIdGroup(ActionEvent event) {
        if (groupComboBox.getValue() != null) {
            daysClasses.setText(groupComboBox.getValue().getDaysOfWeek().getStr());
            timeClasses.setText(groupComboBox.getValue().getTimeClass().getStr());
            sumVisitorsInGroup.setText(Integer.toString(groupComboBox.getValue().getSubscriptionList().size()));
        }
    }

    @FXML
    private void selectTypeSubscription(ActionEvent event) {
        if (typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_DAY_GYM
                || typeSubscriptionComboBox.getValue() == TypeSubscription.SUBSCRIPTION_DAY_POOL)
        {
            radioTrue.setDisable(true);
            radioTrue.setDisable(true);
        }else {
            radioTrue.setDisable(false);
            radioTrue.setDisable(false);
        }
    }


}
