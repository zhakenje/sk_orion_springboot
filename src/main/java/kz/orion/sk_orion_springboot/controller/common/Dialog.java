package kz.orion.sk_orion_springboot.controller.common;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kz.orion.sk_orion_springboot.controller.LoginController;

public class Dialog {

    @FXML
    private Label title;

    @FXML
    private Label message;

    @FXML
    private Button okButton;

    @FXML
    private Button closeButton;

    private Stage stage;

    @FXML
    private void cancel() {
        okButton.getScene().getWindow().hide();
    }

    public void show() {
        stage.show();
    }

    public static class DialogBuilder {

        private String title;
        private String message;

        private ActionListener okActionListener;

        private DialogBuilder() {
        }

        public DialogBuilder okActionListener(ActionListener okActionListener) {
            this.okActionListener = okActionListener;
            return this;
        }

        public DialogBuilder message(String message) {
            this.message = message;
            return this;
        }

        public DialogBuilder title(String title) {
            this.title = title;
            return this;
        }

        public Dialog build() {

            try {

                Stage stage = new Stage(StageStyle.UNDECORATED);
                FXMLLoader loader = new FXMLLoader(Dialog.class.getClassLoader()
                        .getResource("fxml/common/dialog.fxml"));
                Parent view = loader.load();
                stage.setScene(new Scene(view));

                Dialog controller = loader.getController();
                controller.stage = stage;

                controller.title.setText(this.title);
                controller.message.setText(this.message);

                if (null != okActionListener) {
                    controller.okButton.setOnAction(event -> {
                        controller.cancel();
                        okActionListener.doAction();
                        LoginController.show();
                    });
                } else {
                    controller.okButton.setVisible(false);
                    controller.closeButton.setText("ЗАКРЫТЬ");
                }

                return controller;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        }

        public static DialogBuilder builder() {
            return new DialogBuilder();
        }
    }

    public static interface ActionListener {
        void doAction();
    }
}
