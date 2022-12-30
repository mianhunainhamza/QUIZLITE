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
import java.util.ResourceBundle;

public class solveAns implements Initializable {
    @FXML
    private ImageView image;
    @FXML
    private Text userName;
    @FXML
    private ListView<String> list;

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
            File file1 = new File("src/main/java/com/example/projectjavafx/answer.txt");
            FileReader file = new FileReader(file1);
            reader = new BufferedReader(file);
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] arrOfStr = line.split("-");
                for (String a : arrOfStr) {
                    list.getItems().add("Answer:");
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
    void AddQuiz(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("quiz.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Quiz");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void HomeClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Home");
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
    void showStudent(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("student.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setTitle("Students");
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

    public void infoCheck(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("infoCheck.fxml"));
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        stage.setTitle("Info");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SolveAns(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("solveAns.fxml"));
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        stage.setTitle("Info");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void giveMarks(ActionEvent event) {
        String item = list.getSelectionModel().getSelectedItem();
        if (item != null && !item.equals("Answer:")) {
            Group layout = new Group();
            TextField textField = new TextField();
            TextField textField1 = new TextField();
            Button btn = new Button("Submit");
            layout.getChildren().add(textField1);
            layout.getChildren().add(textField);
            layout.getChildren().add(btn);
            textField1.setPromptText("Enter Obtain Makrs!!");
            textField.setPromptText("Enter Total Marks!!");
            textField.setLayoutX(120);
            textField.setLayoutY(140);
            textField1.setLayoutX(120);
            textField1.setLayoutY(100);
            btn.setLayoutX(170);
            btn.setLayoutY(200);
            Scene scene = new Scene(layout, 400, 300);
            Stage stage = new Stage();
            stage.setTitle("Add Marks");
            stage.setScene(scene);
            stage.show();
            btn.setOnAction(event1 -> {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("solveAns.fxml"));
                try {
                    Scene scene1 = new Scene(fxmlLoader.load(), 950, 600);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                scene.getWindow().hide();
                stage.close();
                int newValue = Integer.parseInt(textField.getText());
                int newValue1= Integer.parseInt(textField1.getText());
                String dash = "-";
                if (newValue>=newValue1)
                {
                    File file = new File("src/main/java/com/example/projectjavafx/marks.txt");
                    FileWriter fileWriter = null;
                    try {
                        fileWriter = new FileWriter(file, true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    BufferedWriter bf = new BufferedWriter(fileWriter);
                    try {
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");
                        String val = dtf.format(now);
                        String item1 = list.getSelectionModel().getSelectedItem();
                        String value=textField1.getText();
                        String value1=textField.getText();
                        bf.write(value);
                        bf.write("/");
                        bf.write(value1);
                        bf.write("      Date Added is: ");
                        bf.write(val);
                        list.getItems().set(list.getSelectionModel().getSelectedIndex(), "Marks Submitted!");
//                            list.getItems().remove("Q:");
                        bf.write(dash);
                        list.getItems().remove(item1);
                        list.getItems().remove("Answer:");
                        updateFile(item1);
                        bf.newLine();
                        bf.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please enter correct marks!!");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.CANCEL) {
                            event.consume();
                        } else
                            event.consume();
                    });
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please select the correct cell");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.CANCEL) {
                    event.consume();
                } else
                    event.consume();
            });
        }
    }
    public void updateFile(String item) throws FileNotFoundException {
        File file = new File("src/main/java/com/example/projectjavafx/answer.txt");
        PrintWriter printWriter = new PrintWriter(file);
        for (int i = 0; i < list.getItems().size(); i++) {
            if (!list.getItems().get(i).equals(item) && !list.getItems().get(i).equals("Marks Submitted!")) {
                printWriter.println(list.getItems().get(i));
            }
        }
        printWriter.close();
    }
}