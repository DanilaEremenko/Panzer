package Server;

import Controller.Level;
import Graphic.GraphicBullet;
import Graphic.GraphicPanzer;
import Logic.LogicBullet;
import Logic.LogicPanzer;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Сервер с фиксированным количеством игроков
 * Для каждого игрока по два потока, один на
 * прием сообщений, другой на отправку.
 * Сама игра будет проходить в основном потоке.
 * <p>
 * <p>
 * ПОРЯДОК ПОЛУЧЕНИЯ И ОТПРАВКИ ПЕРВЫХ СООБЩЕНИЙ ОЧЕНЬ ВАЖЕН!!!
 * <p>
 * Этапы сессии с игроком
 * 1    Подключение клиента к серверу
 * 2    Отправка сервером клиенту названия карты
 * 3    Получение имени клиента
 * 4    Ожидание подключения второго игрока, если тот
 * _____еще не подключился.
 * 5    Начало игры. Получение ходов от игрока и отправка
 * _____состояния карты.
 */

public class Server implements ActionListener {

    private Socket firstPlayer;
    private String firstPlayerName;
    private BufferedWriter bw1;

    private Socket secondPlayer;
    private String secondPlayerName;
    private BufferedWriter bw2;

    private CompletableFuture<Boolean> firstPlayerReady;
    private CompletableFuture<Boolean> secondPlayerReady;

    private Timer timer;

    private ServerSocket serverSocket;

    private ExecutorService pool;
    private Gson gson;

    private final String mapName = "LevelTwo";

    private Level level;

    Server() throws IOException {
        firstPlayerReady = new CompletableFuture<>();
        secondPlayerReady = new CompletableFuture<>();
        level = new Level("levels/" + mapName + ".txt");
        pool = Executors.newFixedThreadPool(2);
        gson = new Gson();
        //Открываем сокет на порту 5050
        serverSocket = new ServerSocket(5050);
    }

    void start() {
        //Подключение клиентов
        connect();
        //Запуск игры
        try {
            firstPlayerReady.get();
            secondPlayerReady.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action();
    }

    //TODO
    //НЕТ ДВИЖЕНИЯ ПО ОСИ OY
    private void action() {
        List<PanzerPos> panzerPoses = new ArrayList<>();
        List<BulletPos> bulletPoses = new ArrayList<>();

        for (LogicPanzer panzer : level.getLogicPanzers()) {
            panzer.move();
            panzerPoses.add(new PanzerPos(panzer.getTranslateX(), panzer.getTranslateY(), panzer.getAngleOfMove()));
            for (LogicBullet bullet : panzer.getBullets()) {
                bullet.move();
                bulletPoses.add(new BulletPos(bullet.getTranslateX(), bullet.getTranslateY()));
            }
        }

        for (GraphicPanzer panzer : level.getGraphicPanzers()) {
            panzer.drawing();
            for (GraphicBullet graphicBullet : panzer.getBullets())
                graphicBullet.drawing();
        }
        //Отправляем клиентам состояние игры
        sendState(new State(bulletPoses, panzerPoses));
    }

    private void connect() {
        try {
            firstPlayer = serverSocket.accept();
            bw1 = new BufferedWriter(new OutputStreamWriter(firstPlayer.getOutputStream()));
            startDialogWithFirstPlayer();

            secondPlayer = serverSocket.accept();
            bw2 = new BufferedWriter(new OutputStreamWriter(secondPlayer.getOutputStream()));
            startDialogWithSecondPlayer();

            sendMapName();
        } catch (IOException e) {
            System.out.println("Беда в connect");
        }
    }

    private void startDialogWithFirstPlayer() {
        new Thread(() -> {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(firstPlayer.getInputStream()))) {
                //Ожидаем получение сообщения с именем игрока
                String line = br.readLine();
                firstPlayerName = line.replace("\"", "");
                System.out.println("First player name: " + firstPlayerName);
                firstPlayerReady.complete(true);
                secondPlayerReady.get();
                //Здесь читаем действия игрока в бесконечном цикле
                LogicPanzer panzer = level.getLogicPanzers()[0];
                GraphicPanzer gPanzer = level.getGraphicPanzers()[0];
                Action action;
                while (true) {
                    String message = br.readLine();
                    if (message == null) continue;
                    action = gson.fromJson(message, Action.class);
                    System.out.println(message);
                    boolean isFire = action.isFire();
                    int acceleration = action.getAcceleration();
                    int rotate = action.getRotate();

                    if (isFire) panzer.fire();
                    switch (acceleration) {
                        case -1:
                            panzer.backAccelerate();
                            break;
                        case 1:
                            panzer.accelerate();
                            break;
                    }
                    switch (rotate) {
                        case -1:
                            gPanzer.transformPanzer(-Math.PI / 20);
                            break;
                        case 1:
                            gPanzer.transformPanzer(Math.PI / 20);
                            break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Первый игрок, беда");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void startDialogWithSecondPlayer() {
        new Thread(() -> {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(secondPlayer.getInputStream()))) {
                //Ожидаем получение сообщения с именем игрока
                String line = br.readLine();
                secondPlayerName = line.replace("\"", "");
                System.out.println("Second player name: " + secondPlayerName);
                secondPlayerReady.complete(true);
                firstPlayerReady.get();
                //Здесь читаем действия игрока в бесконечном цикле
                LogicPanzer panzer = level.getLogicPanzers()[1];
                GraphicPanzer gPanzer = level.getGraphicPanzers()[1];
                Action action;
                while (true) {
                    String message = br.readLine();
                    if (message == null) continue;
                    action = gson.fromJson(message, Action.class);
                    System.out.println(message);
                    boolean isFire = action.isFire();
                    int acceleration = action.getAcceleration();
                    int rotate = action.getRotate();

                    if (isFire) panzer.fire();
                    switch (acceleration) {
                        case -1:
                            panzer.backAccelerate();
                            break;
                        case 1:
                            panzer.accelerate();
                            break;
                    }
                    switch (rotate) {
                        case -1:
                            gPanzer.transformPanzer(-Math.PI / 20);
                            break;
                        case 1:
                            gPanzer.transformPanzer(Math.PI / 20);
                            break;
                    }
                }

            } catch (IOException e) {
                System.out.println("Второй игрок, беда");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void sendMapName() {
        try {
            //Отправляем клиентам название карты
            String jsonStrMapName = gson.toJson(mapName);
            bw1.write(jsonStrMapName + "\n");
            bw1.flush();
            bw2.write(jsonStrMapName + "\n");
            bw2.flush();

            //Отправляем клиентам их ID
            bw1.write("1\n");
            bw1.flush();
            bw2.write("2\n");
            bw2.flush();

            System.out.println("Map and ID sent");
        } catch (IOException e) {
            System.out.println("Беда в sendMapName");
        }
    }

    private void sendState(State state) {
        try {
            String json = gson.toJson(state);
            bw1.write(json + "\n");
            bw1.flush();
            bw2.write(json + "\n");
            bw2.flush();
        } catch (IOException e) {
            System.out.println("Беда в sendState");
            timer.stop();
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start();
    }
}
