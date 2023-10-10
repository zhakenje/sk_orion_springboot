package kz.orion.sk_orion_springboot.controller.adminController.notification;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class NotificationFormController {

    public static void showIfOK() {
//        Image image = new Image("/image/icons8-ok-144.png");

        String title = "Сообщение об успехе";
        String message = "Абонент еще действует!";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;

        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));


//        Notifications notifications = Notifications.create();
//        notifications.graphic(new ImageView(image));
//        notifications.text("Абонент еще действует!");
//        notifications.title("Сообщение об успехе");
//        notifications.hideAfter(Duration.seconds(3));
//        notifications.position(Pos.CENTER);
//        notifications.show();
    }

    public static void showIfError() {
        String title = "Сообщение о провале";
        String message = "Абонент не действует!";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;

        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(Duration.seconds(3));

//        Image image = new Image("/image/icons8-error-144.png");
//        Notifications notifications = Notifications.create();
//        notifications.graphic(new ImageView(image));
//        notifications.text("Абонент не действует!");
//        notifications.title("Сообщение о провале");
//        notifications.hideAfter(Duration.seconds(3));
//        notifications.position(Pos.CENTER);
//        notifications.show();
    }

    public static void showNotificationsErr(Pos pos,  String title, String text){
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(Duration.seconds(6))
                .position(pos)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println("Notification is created!");
                    }
                });
        notificationBuilder.showError();
    }

    public static void showNotificationsWarning(Pos pos,  String title, String text){
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(Duration.seconds(6))
                .position(pos)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println("Notification is created!");
                    }
                });
        notificationBuilder.showWarning();
    }

    public static void showNotificationsSuccess(Pos pos,  String title, String text){
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(Duration.seconds(6))
                .position(pos)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println("Notification is created!");
                    }
                });
        notificationBuilder.showConfirm();
    }
}
