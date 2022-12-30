package com.example.projectjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Login implements Initializable {
    //Login page all functions and the variable

    @FXML
    private TextField name;
    @FXML
    private Text text;
    @FXML
    private PasswordField pass;

    @FXML
    public void SignUp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void loginAction(KeyEvent event) throws IOException {
        if (Objects.equals(event.getCode().toString(), "ENTER"))
        {
            String val = name.getText();
            String pas = pass.getText();
            nameStorage nameStorage=new nameStorage();
            nameStorage.setName(val);
            BufferedReader reader;
            int flag = -1;
            int flag1 = -1;
            try {
                File file1 = new File("src/main/java/com/example/projectjavafx/user.txt");
                FileReader file = new FileReader(file1);
                reader = new BufferedReader(file);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    String[] arrOfStr = line.split("-");
                    for (String a : arrOfStr) {
                        if (Objects.equals(val, a)) {
                            flag = 1;
                        }
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                File file1 = new File("src/main/java/com/example/projectjavafx/pass.txt");
                FileReader file = new FileReader(file1);
                reader = new BufferedReader(file);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    String[] arrOfStr = line.split("-");
                    for (String a : arrOfStr) {
                        if (Objects.equals(pas, a)) {
                            flag1 = 1;
                        }
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if ((Objects.equals(val, "admin") && Objects.equals(pas, "admin")) || (flag == 1 && flag1 == 1)) {
                if (Objects.equals(val, "admin") && Objects.equals(pas, "admin")) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Home.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 950, 600);
                    Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                    stage.setTitle("Home");
                    stage.setScene(scene);
                    stage.show();
                } else if (flag == 1 && flag1 == 1) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("HomeS.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 950, 600);
                    Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                    stage.setTitle("Home");
                    stage.setScene(scene);
                    stage.show();
                }
            } else {
                pass.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-background-radius:5px");
                name.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-background-radius:5px");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setContentText("Please enter the correct Username/Password!!");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.CANCEL) {
                        event.consume();
                    } else {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("login.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 950, 600);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                        stage.setTitle("Login");
                        stage.setScene(scene);
                        stage.show();
                    }
                });
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Glow glow = new Glow();
        glow.setLevel(20);
        text.setEffect(glow);
    }
}