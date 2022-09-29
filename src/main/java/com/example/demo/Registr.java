package com.example.demo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Registr {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox Femaleg;

    @FXML
    private CheckBox Maleg;

    @FXML
    private Button Register2;

    @FXML
    private TextField loginFild;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpUser;

    @FXML
    void initialize() {

        Register2.setOnAction(event -> {
            RegNewPolzovatel();

        });
    }
    private void RegNewPolzovatel() {

        Database1 data = new Database1();

        String firstName = signUpUser.getText();
        String lastName = signUpLastName.getText();
        String userName = loginFild.getText();
        String password = passwordfield.getText();
        String gender = "";
        if (Maleg.isSelected())
        gender = "Мужской";
        else
        gender = "Женский";

        User user = new User(firstName, lastName, userName, password, gender);

        data.RegPolzovatel(user);
    }

}