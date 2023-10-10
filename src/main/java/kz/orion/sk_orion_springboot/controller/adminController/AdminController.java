package kz.orion.sk_orion_springboot.controller.adminController;

import animatefx.animation.FadeIn;
import animatefx.animation.Flash;
import com.github.sarxos.webcam.Webcam;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kz.orion.sk_orion_springboot.StageListener;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.controller.LoginController;
import kz.orion.sk_orion_springboot.controller.common.Dialog;
import kz.orion.sk_orion_springboot.utils.MenuAdmin;

public class AdminController {

    @FXML
    private VBox sideBar;

    @FXML
    private StackPane contentView;

    @FXML
    private void initialize() {
        loadView(MenuAdmin.admin_home_view);
    }

    @FXML
    public void clickMenu(MouseEvent mouseEvent) {

        Node node = (Node) mouseEvent.getSource();

        if (node.getId().equals("Exit")) {
            Dialog.DialogBuilder.builder()
                    .title("Подтверждение")
                    .message("Вы хотите выйти из программы?")
                    .okActionListener(() ->
                            {
                                sideBar.getScene().getWindow().hide();
                                if (AdminQRCodeController.webcam != null) {
                                    if (AdminQRCodeController.webcam.isOpen()) {
                                        AdminQRCodeController.webcam.close();
                                    }
                                }
                            }
                    )
                    .build().show();
//            LoginController.show();
        } else {
            if (!(node.getId().equals("admin_qrRead_view"))) {
                MenuAdmin menuAdmin = MenuAdmin.valueOf(node.getId());
                loadView(menuAdmin);
                if (AdminQRCodeController.webcam != null) {
                    if (AdminQRCodeController.webcam.isOpen()) {
                        AdminQRCodeController.webcam.close();
                    }
                }
            } else {
                MenuAdmin menuAdmin = MenuAdmin.admin_qrRead_view;
                loadView(menuAdmin);
            }
        }

    }

    private void loadView(MenuAdmin menuAdmin) {

        try {

            for (Node node : sideBar.getChildren()) {
                node.getStyleClass().remove("active");
                if (node.getId().equals(menuAdmin.name())) {
                    node.getStyleClass().add("active");
                }
            }

            contentView.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(menuAdmin.getFxml()));
            loader.setControllerFactory(StageListener.getApplicationContext()::getBean);
            Parent view = loader.load();

            AbstractController controller = loader.getController();
            controller.setTitle(menuAdmin);

            contentView.getChildren().add(view);
            new Flash(view).play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void show() {

        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(AdminController.class.getClassLoader()
                    .getResource("fxml/admin/admin-view.fxml"));
            stage.setScene(new Scene(root));
//            stage.setFullScreen(true);
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
