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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Student implements Initializable {
    @FXML
    private ImageView image;
    @FXML
    private Button home;

    @FXML
    private ListView<String> list;
    @FXML
    private ListView<String> list1;
    @FXML
    private Button logout;
    @FXML
    private Text userName;
    @FXML
    private Button quiz;

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
            File file1 = new File("src/main/java/com/example/projectjavafx/user.txt");
            FileReader file = new FileReader(file1);
            reader = new BufferedReader(file);
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] arrOfStr = line.split("-");
                for (String a : arrOfStr) {
                    list.getItems().add("Student:");
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
    void DelStudent(ActionEvent event) throws IOException {
        String itemm = list.getSelectionModel().getSelectedItem();
        if (list != null && itemm != null) {
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
                    fxmlLoader.setLocation(getClass().getResource("student.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 950, 600);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                    stage.setTitle("Student");
                    stage.setScene(scene);
                    stage.show();
                }
            });
        }
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
    void EditStudent(ActionEvent event) {
        String itemm = list.getSelectionModel().getSelectedItem();
        if (list != null && itemm != "Student:" && itemm != null) {
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
            stage.setTitle("Add Value");
            stage.setScene(scene);
            stage.show();
            btn.setOnAction(event1 -> {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("student.fxml"));
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
                list.getItems().remove("Student:");
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
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("student.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 950, 600);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                    stage.setTitle("Student");
                    stage.setScene(scene);
                    stage.show();
                }
            });
        }
    }
    public void updateFile(String item) throws FileNotFoundException {
        File file = new File("src/main/java/com/example/projectjavafx/user.txt");
        PrintWriter printWriter = new PrintWriter(file);
        for (int i = 0; i < list.getItems().size(); i++) {
            if (!list.getItems().get(i).equals(item) && !list.getItems().get(i).equals("Student:")) {
                printWriter.println(list.getItems().get(i)+"-");
            }
        }
        printWriter.close();
    }
    @FXML
    void AddStudent(ActionEvent event)
    {
        String itemm = list.getSelectionModel().getSelectedItem();
        if (itemm == null)
        {
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
            stage.setTitle("Add Value");
            stage.setScene(scene);
            stage.show();
            btn.setOnAction(event1 -> {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("student.fxml"));
                try {
                    Scene scene1 = new Scene(fxmlLoader.load(), 950, 600);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                scene.getWindow().hide();
                stage.close();
                String item = list.getSelectionModel().getSelectedItem();
                String newValue = textField.getText();
                list.getItems().add(newValue);
//                list.getItems().add()
//            list.getItems().add(textField.getText());
                try {
                    updateFile(item);
                    updateFilePass(newValue);
                } catch (FileNotFoundException ex) {
                    System.out.println("File Not Found.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please un-select the cell first!!");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.CANCEL) {
                    event.consume();
                } else {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("student.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 950, 600);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                    stage.setTitle("Student");
                    stage.setScene(scene);
                    stage.show();
                }
            });
        }
        }
    public void updateFilePass(String item) throws IOException {
        File file=new File("src/main/java/com/example/projectjavafx/pass.txt");
        FileWriter fileWriter=new FileWriter(file,true);
        BufferedWriter bf=new BufferedWriter(fileWriter);
        bf.write(item);
        bf.write("-");
        bf.newLine();
        bf.close();
    }
    }