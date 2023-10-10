package kz.orion.sk_orion_springboot.controller.info;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import kz.orion.sk_orion_springboot.StageListener;
import kz.orion.sk_orion_springboot.controller.signUp.VisitorSignUpController;
import kz.orion.sk_orion_springboot.model.entity.Subscription;
import kz.orion.sk_orion_springboot.model.entity.enums.TypeSubscription;

public class ElectronSubscriptionShow {

    @FXML
    private TextField FIO;

    @FXML
    private TextField address;

    @FXML
    private TextField dateOfPurchase;

    @FXML
    private TextField dateOfValidity;

    @FXML
    private TextField idSubscription;

    @FXML
    private TextField numberVisits;

    @FXML
    private TextField price;

    @FXML
    private ImageView qrImageView;

    @FXML
    private TextField typeGroup;

    @FXML
    private TextField visitsLeft;

    private Subscription subscription;

    private void init(Subscription subscription){
        this.subscription = subscription;
        Image qrImage = new Image(this.subscription.getQrCode());
        qrImageView.setImage(qrImage);
        idSubscription.setText(this.subscription.getId().toString());
        FIO.setText(this.subscription.getVisitor().toString());
        address.setText("");
        typeGroup.setText(this.subscription.getTypeSubscription().toString());
        price.setText(Integer.toString(this.subscription.getPrice()));
        dateOfPurchase.setText(this.subscription.getDateOfPurchase().toString());
        dateOfValidity.setText(this.subscription.getDateOfValidity().toString());
        if (this.subscription.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_DAY_POOL ||
            this.subscription.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_DAY_GYM)
        {
            numberVisits.setText(Integer.toString(1));
        } else if (this.subscription.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_MONTH_POOL ||
                this.subscription.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_MONTH_GYM) {
            numberVisits.setText(Integer.toString(12));
        }
        visitsLeft.setText(Integer.toString(this.subscription.getNumberOfVisits()));

    }

    public static void show(Subscription subscription){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(VisitorSignUpController.class.getClassLoader()
                    .getResource("fxml/info/showSubscription.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            ElectronSubscriptionShow controller = loader.getController();
            controller.init(subscription);
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
