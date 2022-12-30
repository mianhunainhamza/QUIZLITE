package com.example.projectjavafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUp implements Initializable {
    //Sign up page all functions and var
    @FXML
    private Button back;
    @FXML
    private TextField name;
    @FXML
    private Text text;
    @FXML
    private Text textView;
    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField pass1;

    @FXML
    private TextField regNo;

    @FXML
    private Button signup;
    @FXML
    public void Back(ActionEvent event) throws InterruptedException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setContentText("You want to exit?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.CANCEL) {
                event.consume();
            }
            else{
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

    @FXML
    public void SetSignUp(KeyEvent event) throws IOException {
        if (event.getCode().toString().equals("ENTER"))
        {
            String text1=name.getText();
            String pas1=pass1.getText();
            String pas2=pass.getText();
            String reg=regNo.getText();
            String dash="-";
            if(text1.equals("")||pas1.equals("")||pas2.equals("")||reg.equals(""))
            {
                pass.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-background-radius: 3, 2;");
                pass1.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-background-radius: 3, 2;");
                regNo.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-background-radius: 3, 2;");
                name.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-background-radius: 3, 2;");
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please enter all the fields!!");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.CANCEL) {
                        event.consume();
                    }
                    else{
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("SignUp.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 950, 600);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                        stage.setTitle("SignUp");
                        stage.setScene(scene);
                        stage.show();
                    }
                });

            } else if ((Objects.equals(text1,"admin"))&&Objects.equals(pass,"admin")) {

                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please enter the name/pass other than admin!!");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.CANCEL) {
                        event.consume();
                    }
                    else{
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("SignUp.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 950, 600);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                        stage.setTitle("SignUp");
                        stage.setScene(scene);
                        stage.show();
                    }
                });

            } else if (Objects.equals(pas1,pas2))
            {
                if (!(pas1.length()<5))
                {
                    File file=new File("src/main/java/com/example/projectjavafx/user.txt");
                    FileWriter fileWriter=new FileWriter(file,true);
                    BufferedWriter bf=new BufferedWriter(fileWriter);
                    File file1=new File("src/main/java/com/example/projectjavafx/pass.txt");
                    FileWriter fileWriter1=new FileWriter(file1,true);
                    BufferedWriter bf1=new BufferedWriter(fileWriter1);
                    bf.write(text1);
                    bf.write(dash);
                    bf1.write(pas1);
                    bf1.write(dash);
                    bf1.newLine();
                    bf.newLine();
                    bf.close();
                    bf1.close();
                    fileWriter1.close();
                    fileWriter.close();
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Congratulations");
                    alert.setContentText("Sign up Successfully");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.CANCEL) {
                            event.consume();
                        }
                        else{
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("SignUp.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load(), 950, 600);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                            stage.setTitle("SignUp");
                            stage.setScene(scene);
                            stage.show();
                        }
                    });
                }
                else
                {
                    pass.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-background-radius:5px");
                    pass1.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-background-radius:5px");
                    Alert alert=new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Password does not match or Length is less than 5 characters!!!");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.CANCEL) {
                            event.consume();
                        }
                        else{
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("SignUp.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load(), 950, 600);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                            stage.setTitle("SignUp");
                            stage.setScene(scene);
                            stage.show();
                        }
                    });
                }
            }
        }
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Glow glow = new Glow();
        glow.setLevel(40);
        text.setEffect(glow);
        textView.setEffect(glow);
    }
}