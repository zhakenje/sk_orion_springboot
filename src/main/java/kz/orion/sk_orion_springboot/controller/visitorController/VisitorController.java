package kz.orion.sk_orion_springboot.controller.visitorController;

import animatefx.animation.FadeIn;
import animatefx.animation.Flash;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import kz.orion.sk_orion_springboot.StageListener;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.controller.common.Dialog;
import kz.orion.sk_orion_springboot.utils.MenuVisitor;

public class VisitorController {

    @FXML
    private VBox sideBar;

    @FXML
    private StackPane contentView;

    @FXML
    private void initialize() {
        loadView(MenuVisitor.visitor_home_view);
    }

    @FXML
    public void clickMenu(MouseEvent mouseEvent) {

        Node node = (Node) mouseEvent.getSource();

        if (node.getId().equals("Exit")) {
            Dialog.DialogBuilder.builder()
                    .title("Подтверждение")
                    .message("Вы хотите выйти из программы?")
                    .okActionListener(() -> sideBar.getScene().getWindow().hide())
                    .build().show();
        } else {
            MenuVisitor menuVisitor = MenuVisitor.valueOf(node.getId());
            loadView(menuVisitor);
        }

    }

    private void loadView(MenuVisitor menuVisitor) {

        try {

            for (Node node : sideBar.getChildren()) {
                node.getStyleClass().remove("active");
                if (node.getId().equals(menuVisitor.name())){
                    node.getStyleClass().add("active");
                }
            }

            contentView.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(menuVisitor.getFxml()));
            loader.setControllerFactory(StageListener.getApplicationContext()::getBean);
            Parent view = loader.load();

            AbstractController controller = loader.getController();
            controller.setTitle(menuVisitor);

            contentView.getChildren().add(view);
            new Flash(view).play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void show() {

        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(VisitorController.class.getClassLoader()
                    .getResource("fxml/visitor/visitor-view.fxml"));
            stage.setScene(new Scene(root));
//            stage.setFullScreen(true);
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
