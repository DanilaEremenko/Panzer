package Controller;

import Graphic.GraphicPanzer;
import Logic.LogicPanzer;
import Server.Action;
import Server.PanzerPos;
import Server.State;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class OnlineGame extends Application {

    private Socket socket;
    //Один для входящих сообщений,
    //другой для исходящих
    private Gson gsonW;
    private Gson gsonR;
    private Level level;
    private int myId;
    private String name;
    //Нужен для ожидания инициализации Level
    private CompletableFuture<Level> completableFuture;

    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            completableFuture = new CompletableFuture<>();
            name = "Ant";
            socket = new Socket("localhost", 5050);
            gsonW = new Gson();
            gsonR = new Gson();
        } catch (IOException e) {
            System.out.println("Беда в конструкторе");
        }

        openSession();
        loop();
        completableFuture.get().getScene().setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            Action action;
            switch (code) {
                case W:
                    action = new Action(1, 0, false);
                    break;
                case S:
                    action = new Action(-1, 0, false);
                    break;
                case A:
                    action = new Action(0, -1, false);
                    break;
                case D:
                    action = new Action(0, 1, false);
                    break;
                case SPACE:
                    action = new Action(0, 0, true);
                    break;
                default:
                    action = new Action(0, 0, false);
                    break;
            }
            sendAction(action);
        });

        Level level = completableFuture.get();
        primaryStage.setScene(level.getScene());
        primaryStage.show();
    }

    private void openSession() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            System.out.println("Получаем карту");
            String mapName = bufferedReader.readLine();

            level = new Level("levels/" + mapName.replace("\"", "") + ".txt");

            System.out.println("Level got");
            completableFuture.complete(level);

            //Вторым приходит сообщение с ID пользователя
            myId = Integer.parseInt(bufferedReader.readLine().replace("\"", ""));

            System.out.println("Id got");

            //Отправдяем свое имя
            System.out.println("Ждем получание карты");
            bufferedWriter.write(name + "\n");
            bufferedWriter.flush();
            System.out.println("Имя отправлено");
        } catch (IOException e) {
            System.out.println("Беда при открытии сессии");
        }
    }

    //TODO
    private void setState(String message) {
        LogicPanzer[] logicPanzers = level.getLogicPanzers();
        State state = gsonR.fromJson(message, State.class);
        List<PanzerPos> panzerPoses = state.getPanzerPoses();
        for (int i = 0; i < panzerPoses.size(); i++) {
            PanzerPos panzerPos = panzerPoses.get(i);
            logicPanzers[i].setTranslate(panzerPos.getX(), panzerPos.getY());
            logicPanzers[i].setRotation(panzerPos.getAngle() * (180 / Math.PI));
        }
        for (GraphicPanzer graphicPanzer : level.getGraphicPanzers()) {
            graphicPanzer.drawing();
        }
    }

    private void loop() {
        new Thread(() -> {
            try {
                while (true) {
                    String message = bufferedReader.readLine();
                    if (message == null) continue;
                    setState(message);
                }
            } catch (IOException e) {
                System.out.println("Беда в loop");
            }
        }).start();
    }

    private void sendAction(Action action) {
        try {
            String message = gsonW.toJson(action);
            bufferedWriter.write(message + "\n");
            bufferedWriter.flush();
            System.out.println("Сообщение отправлено");
        } catch (IOException e) {
            System.out.println("Беда при отправке Action");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
