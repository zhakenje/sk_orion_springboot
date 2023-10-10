package kz.orion.sk_orion_springboot.controller;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import kz.orion.sk_orion_springboot.StageListener;
import kz.orion.sk_orion_springboot.controller.adminController.AdminController;
import kz.orion.sk_orion_springboot.controller.instructorController.InstructorController;
import kz.orion.sk_orion_springboot.controller.signUp.VisitorSignUpController;
import kz.orion.sk_orion_springboot.controller.visitorController.VisitorController;
import kz.orion.sk_orion_springboot.model.entity.*;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import kz.orion.sk_orion_springboot.model.service.AdminService;
import kz.orion.sk_orion_springboot.model.service.InstructorService;
import kz.orion.sk_orion_springboot.model.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
@RequiredArgsConstructor
public class LoginController {

    public static BaseEntity loginUser;

    private final VisitorService visitorService;

    private final InstructorService instructorService;

    private final AdminService adminService;

    @FXML
    private Button closeButton;

    @FXML
    private TextField loginId;

    @FXML
    private Button loginButton;

    @FXML
    private Label message;

    @FXML
    private PasswordField password;

    @FXML
    private void close() {
        closeButton.getScene().getWindow().hide();
    }

    public void attachEvent(){
        loginId.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER){
                if (closeButton.isFocused()){
                    close();
                }

                if (loginButton.isFocused()){
                    login();
                }
            }
        });
    }

    @FXML
    private void login() {
        try {
            int vis = 0;
            int ins = 0;
            int adm =0;
            for (Visitor vs:visitorService.findAll()) {
                if (loginId.getText().equals(vs.getUsername())){
                    vis++;
                    break;
                }
            }
            for (Instructor instr:instructorService.findAll()) {
                if (loginId.getText().equals(instr.getUsername())){
                    ins++;
                    break;
                }
            }
            for (Admin ad:adminService.findAll()) {
                if (loginId.getText().equals(ad.getUsername())){
                    adm++;
                    break;
                }
            }

            //open application
            if (vis > 0)
            {
                loginUser = visitorService.login(loginId.getText(), password.getText());
                VisitorController.show();
            } else if (ins > 0) {
                loginUser = instructorService.login(loginId.getText(), password.getText());
                InstructorController.show();
            } else if (adm > 0){
                loginUser = adminService.login(loginId.getText(), password.getText());
                AdminController.show();
            } else {
                throw new PosException("Неправильные введенные данные!");
            }

//            //open application
//            if (loginUser.getRole() == Role.VISITOR){
//                VisitorController.show();
//            } else if (loginUser.getRole() == Role.INSTRUCTOR) {
//                InstructorController.show();
//            } else if (loginUser.getRole() == Role.ADMIN){
//                AdminController.show();
//            }


            // close the login-view
            close();
        } catch (PosException e) {
            message.setText(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            close();
        }

    }

    @FXML
    private void registerVisitor(){
        try {
            //open form register for visitor
            VisitorSignUpController.edit(null, this::save);
        }catch (Exception e){
            e.printStackTrace();
            close();
        }

    }

    private void save(Visitor visitor) {
        visitorService.registerVisitor(visitor);
    }

    public static void show(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(LoginController.class.getClassLoader()
                    .getResource("fxml/login-view.fxml"));
            loader.setControllerFactory(StageListener.getApplicationContext()::getBean);
            stage.setScene(new Scene(loader.load()));
//            stage.setFullScreen(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
