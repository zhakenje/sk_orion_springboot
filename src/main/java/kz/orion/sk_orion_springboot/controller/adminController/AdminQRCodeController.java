package kz.orion.sk_orion_springboot.controller.adminController;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.controller.adminController.notification.NotificationFormController;
import kz.orion.sk_orion_springboot.controller.common.Dialog;
import kz.orion.sk_orion_springboot.model.entity.Statistic;
import kz.orion.sk_orion_springboot.model.entity.Subscription;
import kz.orion.sk_orion_springboot.model.service.StatisticService;
import kz.orion.sk_orion_springboot.model.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
@Component
@RequiredArgsConstructor
public class AdminQRCodeController extends AbstractController implements Runnable, ThreadFactory {

    @FXML
    private Pane qrWeb;

    @FXML
    private TextField result;

    @FXML
    private SwingNode swingNode;

    @FXML
    private Label title;

    private final SubscriptionService subscriptionService;

    private final StatisticService statisticService;

    private WebcamPanel panel = null;

    public static Webcam webcam = null;

    private Executor executor = Executors.newSingleThreadExecutor(this);

    private boolean activeReadQR = true;


    @FXML
    private void initialize() {
        initWebcam();
        createAndSetSwingContent(swingNode);
        executor.execute(this);
    }


    private void createAndSetSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JPanel jPanel = new JPanel();
//                jPanel.setLayout(null);
                jPanel.setSize(440, 400);
                jPanel.add(panel);
                swingNode.setContent(jPanel);
            }
        });
    }

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Logger.getLogger(AdminQRCodeController.class.getName()).log(Level.SEVERE, null, e);
            }

            Result result1 = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result1 = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                Logger.getLogger(AdminQRCodeController.class.getName()).log(Level.SEVERE, null, e);
            }

            if (result1 != null) {
                webcam.close();
                result.setText(result1.getText());
                Subscription subscription = subscriptionService.findById(Integer.parseInt(String.valueOf(result1)));
                int nbVisitAfter = subscription.getNumberOfVisits() - 1;
                if (nbVisitAfter >= 0){
                    Platform.runLater(() -> NotificationFormController.showIfOK());
                    Statistic statistic = new Statistic();
                    statistic.setTypeSubscription(subscription.getTypeSubscription());
                    statistic.setDate(LocalDateTime.now());
                    statisticService.save(statistic);
                    subscription.setNumberOfVisits(nbVisitAfter);
                    subscription.addStatisticToSubscription(statistic);
                    subscriptionService.saveAfterCheck(subscription);
                    qrWeb.setVisible(false);
                }else {
                    Platform.runLater(() -> NotificationFormController.showIfError());
                    qrWeb.setVisible(false);
//                    subscriptionService.saveAfterCheck(subscription);
                }
                activeReadQR = false;
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
}
