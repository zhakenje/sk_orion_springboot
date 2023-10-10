package kz.orion.sk_orion_springboot.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import kz.orion.sk_orion_springboot.utils.MenuAdmin;
import kz.orion.sk_orion_springboot.utils.MenuInstructor;
import kz.orion.sk_orion_springboot.utils.MenuVisitor;

public class AbstractController {

    @FXML
    private Label title;

    public void setTitle(MenuVisitor menuVisitor){
        this.title.setText(menuVisitor.getTitle());
    }

    public void setTitle(MenuInstructor menuInstructor){
        this.title.setText(menuInstructor.getTitle());
    }

    public void setTitle(MenuAdmin menuAdmin){
        this.title.setText(menuAdmin.getTitle());
    }
}
