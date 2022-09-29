package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField Password;

    @FXML
    private Button Reg;

    @FXML
    private TextField UserName;

    @FXML
    private Button Vhod;

    @FXML
    void Reg(ActionEvent event) {

    }

    @FXML
    void app(ActionEvent event) {

    }

    @FXML
    void initialize() {
        Vhod.setOnAction(event -> {
            String loginText = UserName.getText().trim();
            String loginPassword = Password.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals(""))
                loginUsers(loginText, loginPassword);
            else
                System.out.println("Заполните пустые поля");
                });

        Reg.setOnAction(event -> {
            Reg.getScene().getWindow().hide();

            FXMLLoader loader =  new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo/Reg.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
         });

    }

    private void loginUsers(String loginText, String loginPassword) {
        Database1 data = new Database1();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = data.getUser(user);

        int cont = 0;

        try {
            while (result.next()) {
                cont++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (cont >= 1) {
            System.out.println("Success!");
        }
    }
}