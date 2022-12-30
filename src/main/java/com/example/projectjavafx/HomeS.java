package com.example.projectjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeS implements Initializable {
    @FXML
    private TextArea list;
    @FXML
    private ImageView image;
    @FXML
    private Text userName;
    @FXML
    public void HomeClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("HomeS.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void Logout(ActionEvent event) throws InterruptedException {
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
    public void infoCheckS(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("infoCheckS.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Info");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ShowQuizS(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ShowQuizS.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Show Quiz");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void Announce(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AnnounceS.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Announcement");
        stage.setScene(scene);
        stage.show();
    }
    int flag=0;
    int count=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader;
        try {
            File file1 = new File("src/main/java/com/example/projectjavafx/marks.txt");
            FileReader file = new FileReader(file1);
            reader = new BufferedReader(file);
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] arrOfStr = line.split("-");
                for (String a : arrOfStr) {
                    if (a != null) {
                        flag=1;
                        count++;
                    }
                    else
                    {
                        list.setDisable(true);
                        list.setText("No new Notification");
                    }
                }
            }
            if (flag==1)
            {
                if (count>1)
                    list.setText(count+ " New Notification in Quiz Section!!");
                else
                    list.setText(count+ " New Notification in Quiz Section!!");
                list.setDisable(true);
            }
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            if (imageSetter.getUrl() != null)
                image.setImage(new Image(imageSetter.getUrl()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (imageSetterS.getUrl()!=null)
                image.setImage(new Image(imageSetterS.getUrl()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(nameStorage.getName());
        userName.setText("@"+nameStorage.getName());
    }
}
