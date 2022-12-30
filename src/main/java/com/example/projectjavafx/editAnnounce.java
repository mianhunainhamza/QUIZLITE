package com.example.projectjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
public class editAnnounce implements Initializable {
    @FXML
    private ListView<String> list;
    @FXML
    private Text userName;
    @FXML
    private ImageView image;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (imageSetter.getUrl()!=null)
                image.setImage(new Image(imageSetter.getUrl()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(nameStorage.getName());
        userName.setText("@"+nameStorage.getName());
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader;
        try {
            File file1 = new File("src/main/java/com/example/projectjavafx/message.txt");
            FileReader file = new FileReader(file1);
            reader = new BufferedReader(file);
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] arrOfStr = line.split("-");
                for (String a : arrOfStr) {
                    list.getItems().add("Message:");
                    lines.add(a);
                    list.getItems().add(a);
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void DelList(ActionEvent event) throws IOException {
        String itemm = list.getSelectionModel().getSelectedItem();
        if (list != null && itemm != null && !itemm.equals("Message:")) {
            // Get Selected Item
            String item = list.getSelectionModel().getSelectedItem().toString();

            // Remove the Selected Item
            list.getItems().remove(item);

            // Update the file
            try {
                updateFile(item);
            } catch (FileNotFoundException ex) {
                System.out.println("File Not Found.");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please select the cell first!!");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.CANCEL) {
                    event.consume();
                } else {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("editAnnounce.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 950, 600);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                    stage.setTitle("Edit Announce");
                    stage.setScene(scene);
                    stage.show();
                }
            });
        }
    }

    @FXML
    void EditList(ActionEvent event) throws FileNotFoundException {
        String itemm = list.getSelectionModel().getSelectedItem();
        if (list != null && !Objects.equals(itemm, "Message:") && itemm != null) {
            Group layout = new Group();
            TextField textField = new TextField();
            Button btn = new Button("Submit");
            layout.getChildren().add(textField);
            layout.getChildren().add(btn);
            textField.setLayoutX(120);
            textField.setLayoutY(140);
            btn.setLayoutX(170);
            btn.setLayoutY(200);
            Scene scene = new Scene(layout, 400, 300);
            Stage stage = new Stage();
            stage.setTitle("Add Message");
            stage.setScene(scene);
            stage.show();
            btn.setOnAction(event1 -> {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("editAnnounce.fxml"));
                try {
                    Scene scene1 = new Scene(fxmlLoader.load(), 950, 600);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                scene.getWindow().hide();
                stage.close();
                String item = list.getSelectionModel().getSelectedItem();
                String newValue = textField.getText();
                list.getItems().remove(item);
                list.getItems().remove("Message:");
                list.getItems().add(newValue);
//                list.getItems().add()
//            list.getItems().add(textField.getText());
                try {
                    updateFile(item);
                } catch (FileNotFoundException ex) {
                    System.out.println("File Not Found.");
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please select the cell first!!");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.CANCEL) {
                    event.consume();
                } else {
                   event.consume();
                }
            });
        }
    }

    @FXML
    void EditAnnounce(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("editAnnounce.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Show Quiz");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Announce(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Announce.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Announcement");
        stage.setScene(scene);
        stage.show();
    }

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
    public void updateFile (String item) throws FileNotFoundException {
        File file = new File("src/main/java/com/example/projectjavafx/message.txt");
        PrintWriter printWriter = new PrintWriter(file);
        for (int i = 0; i < list.getItems().size(); i++) {
            if (!list.getItems().get(i).equals(item) && !list.getItems().get(i).equals("Message:")) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String value=(dtf.format(now));
                printWriter.println(list.getItems().get(i)+"              "+value+"-");
            }
        }
        printWriter.close();
    }
}