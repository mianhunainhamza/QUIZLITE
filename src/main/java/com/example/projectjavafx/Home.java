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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Home implements Initializable {
    @FXML
    private RadioButton btn1;
    @FXML
    private TextArea list;

    @FXML
    private RadioButton btn2;

    @FXML
    private RadioButton btn3;
    @FXML
    private ImageView image;
    @FXML
    private RadioButton btn4;

    @FXML
    private Button modeBtn;
    @FXML
    private Text userName;

    @FXML
    private void ShowQuiz(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ShowQuiz.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Show Quiz");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showStudent(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("student.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Students");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void infoCheck(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("infoCheck.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Info");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void HomeClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void AddQuiz(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("quiz.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Quiz");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Logout(ActionEvent event) throws InterruptedException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setContentText("You want to exit?");
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

    @FXML
    private void Anouce(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Announce.fxml"));
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
        System.out.println(nameStorage.getName());
        userName.setText("@" + nameStorage.getName());

        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader;
        try {
            File file1 = new File("src/main/java/com/example/projectjavafx/answer.txt");
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
                    list.setText(count+ " New Notifications in Solved Quiz!!");
                else
                    list.setText(count+ " New Notification in Solved Quiz!!");
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
    }
}