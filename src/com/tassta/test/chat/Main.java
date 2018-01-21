package com.tassta.test.chat;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private ListView<Message> history_list_view;
    private TextArea text;
    private ObservableList<Message> history_observable_list;
    private ListView<String> user_list_view;
    private ObservableList<String> user_observable_list;
    private FileChooser fileChooser = new FileChooser();

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 604, 450);

        text = new TextArea();
        text.setLayoutX(5);
        text.setLayoutY(400);
        text.setPrefSize(385, 45);
        text.requestFocus();
        text.setWrapText(true);
        root.getChildren().add(text);

        history_list_view = new ListView<Message>();
        history_list_view.setLayoutX(5);
        history_list_view.setLayoutY(5);
        history_list_view.setEditable(false);
        history_list_view.setPrefSize(440, 390);
        history_observable_list = FXCollections.observableArrayList();
        history_list_view.setItems(history_observable_list);

        Button sendButton = new Button("Send");
        sendButton.setLayoutX(395);
        sendButton.setLayoutY(400);
//        sendButton.setText("");
        sendButton.setPrefSize(45, 44);
        root.getChildren().add(sendButton);
//        history_list_view.setCellFactory((ListView<Message> l) -> new MessageCell());

        root.getChildren().add(history_list_view);

        user_list_view = new ListView<String>();
        user_list_view.setLayoutX(450);
        user_list_view.setLayoutY(5);
        user_list_view.setPrefSize(150, 440);
        user_observable_list = FXCollections.observableArrayList();
        user_list_view.setItems(user_observable_list);
        user_list_view.setStyle("");
//        user_list_view.setCellFactory((ListView<String> l) -> new UserCell());
        root.getChildren().add(user_list_view);

        history_list_view.prefWidthProperty().bind(root.widthProperty().subtract(user_list_view.widthProperty()).subtract(10));
        history_list_view.prefHeightProperty().bind(root.heightProperty().subtract(text.heightProperty().add(15)));
        user_list_view.layoutXProperty().bind(root.widthProperty().subtract(155));
        user_list_view.prefHeightProperty().bind(root.heightProperty().subtract(10));
        text.layoutYProperty().bind(root.heightProperty().subtract(text.heightProperty().add(5)));
        text.prefWidthProperty().bind(root.widthProperty().subtract(user_list_view.widthProperty().add(20).add(sendButton.widthProperty())));
        sendButton.layoutXProperty().bind(user_list_view.layoutXProperty().subtract(sendButton.widthProperty()).subtract(5));
        sendButton.layoutYProperty().bind(root.heightProperty().subtract(sendButton.heightProperty()).subtract(6));
////        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
////        stage.setTitle("Hello World");
////        stage.setScene(new Scene(root, 300, 275));
        stage.setScene(scene);
        stage.show();
        history_list_view.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
        });
        text.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
//                    try {
//                        client.sendMessage(text.getText());
                        text.clear();
                        ke.consume();
//                    } catc

                }
            }
        });

        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                File file = fileChooser.showOpenDialog(stage);
//                if(file != null){
//                    try {
//                        Image image = new Image("file:///" + file.getAbsolutePath());
////                        client.sendPhoto(image);
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                }
            }
        });

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
