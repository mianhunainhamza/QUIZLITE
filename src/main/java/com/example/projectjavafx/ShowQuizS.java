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

public class ShowQuizS implements Initializable {
    @FXML
    private ImageView image;
    @FXML
    private Text userName;
    static double flag=0;
    @FXML
    private  ListView<String> list;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (imageSetterS.getUrl()!=null)
                image.setImage(new Image(imageSetterS.getUrl()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(nameStorage.getName());
        userName.setText("@"+nameStorage.getName());

            ArrayList<String> lines = new ArrayList<>();
            BufferedReader reader;
            try {
                File file1 = new File("src/main/java/com/example/projectjavafx/question.txt");
                FileReader file = new FileReader(file1);
                reader = new BufferedReader(file);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    String[] arrOfStr = line.split("-");
                    for (String a : arrOfStr) {
                        list.getItems().add("Q:");
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
    void HomeClick(ActionEvent event) throws IOException {
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

    public void infoCheck(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("infoCheckS.fxml"));
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        stage.setTitle("Info");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SolveQstn(ActionEvent event) {
        {
            double sizeList = list.getItems().size();
            double sizeL = sizeList % 2;
            String itemm = list.getSelectionModel().getSelectedItem();

            if (flag >= (sizeList / 2)) {
                list.getItems().add("");
                list.getItems().add("Answers has been Submitted.");
            }


            if (itemm != null && !itemm.equals("Q:") && flag >= 0 && flag <= (sizeList / 2)) {
                if (flag >= (sizeList / 2)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("You already have submitted the Answer!!");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.CANCEL) {
                            event.consume();
                        } else if (response == ButtonType.OK) {
                            event.consume();
                        }
                        ((Node) event.getSource()).setDisable(true);
                    });
                } else {
                    //                sizeL++;
//                System.out.println(sizeL);
                    if (!itemm.equals("ANSWER SUBMITTED!")) {
//                        Text text=new Text("Name:");
//                        Text text1=new Text("Answer:");
                        Group layout = new Group();
                        TextField textField = new TextField();
                        Button btn = new Button("Submit");
                        TextField textField1 = new TextField();
//                        layout.getChildren().add(text);
//                        layout.getChildren().add(text1);
//                        layout.getChildren().add(textField1);
                        layout.getChildren().add(textField);
                        layout.getChildren().add(btn);
//                        textField1.setPromptText("Enter your name:");
//                        textField.setPromptText("Enter your Answer:");
//                        text.setLayoutX(70);
////                        text1.setLayoutX(70);
//                        text.setLayoutY(115);
//                        text1.setLayoutY(155);
                        textField.setLayoutX(120);
                        textField.setLayoutY(140);
//                        textField1.setLayoutX(120);
//                        textField1.setLayoutY(100);
                        btn.setLayoutX(170);
                        btn.setLayoutY(200);
                        Scene scene = new Scene(layout, 400, 300);
                        Stage stage = new Stage();
                        stage.setTitle("Add Value");
                        stage.setScene(scene);
                        stage.show();
                        btn.setOnAction(event1 -> {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("ShowQuizS.fxml"));
                            try {
                                Scene scene1 = new Scene(fxmlLoader.load(), 950, 600);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            scene.getWindow().hide();
                            stage.close();
                            String dash = "-";
                            if (!Objects.equals(textField.getText(), ""))
                            {
//                                System.out.println(textField1.getText());
                                File file = new File("src/main/java/com/example/projectjavafx/answer.txt");
                                FileWriter fileWriter = null;
                                try {
                                    fileWriter = new FileWriter(file, true);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                BufferedWriter bf = new BufferedWriter(fileWriter);
                                try {
                                    LocalDateTime now = LocalDateTime.now();
                                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");
                                    String val=dtf.format(now);
                                    String item = list.getSelectionModel().getSelectedItem();
                                    String newValue = textField.getText();
                                    bf.write(newValue);
                                    bf.write("   __ Student Name is : ");
                                    bf.write(nameStorage.getName());
                                    bf.write("   __ Date Submitted is : ");
                                    bf.write(val);
                                    list.getItems().set(list.getSelectionModel().getSelectedIndex(), "ANSWER SUBMITTED!");
//                            list.getItems().remove("Q:");
                                    bf.write(dash);
                                    bf.newLine();
                                    bf.close();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                flag++;}
                            else
                            {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setContentText("Enter the right Credentials!!");
                                alert.showAndWait().ifPresent(response -> {
                                    if (response == ButtonType.CANCEL) {
                                        event.consume();
                                    } else if (response == ButtonType.OK) {
                                        event.consume();
                                    }
                                });
                            }
                        });
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("You already have submitted the Answer!!");
                        alert.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.CANCEL) {
                                event.consume();
                            } else if (response == ButtonType.OK) {
                                event.consume();
                            }
                        });
                    }

                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please select the correct cell!!");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.CANCEL) {
                        event.consume();
                    } else if (response == ButtonType.OK) {
                        event.consume();
                    }
                });
            }
        }
        System.out.println(flag);
    }
    @FXML
    private void ShowMarks(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("marks.fxml"));
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        stage.setTitle("Marks");
        stage.setScene(scene);
        stage.show();
    }
}