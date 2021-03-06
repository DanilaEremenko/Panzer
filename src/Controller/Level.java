package Controller;


import Graphic.GraphicObstacles;
import Graphic.GraphicPanzer;
import Logic.LogicPanzer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import Controller.*;

import java.io.*;
import java.util.ArrayList;



//Класс, который считывает информацию из текстового файла и записывает себе в поля
//Здесь хранятся все переменные нужные для игры
public class Level {

    private int panzerDigit;//Колличество танков
    private int[] panzerType;//Типы танков
    private int verticalEl;//Колличество вертикальных элементонв
    private int gorizontalEl;//Колличество горизонтальных элементов
    private int[] translatePanzersX;//Начальные координаты танков по оси X
    private int[] translatePanzersY;//Начальные координаты танков по оси Y
    private int[] teamOne;//TODO
    private int[] teamTwo;//TODO
    private int[] translateVerticalX;//Координаты вертикальных препятствий по X
    private int[] translateVerticalY;//Координаты вертикальных препятствий по Y
    private int[] translateGorizontalX;//Координаты горизонтальных препятствий по X
    private int[] translateGorizontalY;//Координаты горизонтальных препятствий по Y
    private int ramka;//Наличие рамки

    private LogicPanzer logicPanzers[];//Логические отображения танков
    private GraphicPanzer graphicPanzers[];//Графические отображения танкова
    private ArrayList<GraphicObstacles> obstacles;//Ограждения
    private int sceneWidt;
    private int sceneHeight;
    private Scene scene;
    private Pane gameRoot;
    //TODO
//    private final MultiplePressedKeysEventHandler keyHandler =
//            new MultiplePressedKeysEventHandler(new MultiplePressedKeysEventHandler.MultiKeyEventHandler() {
//
//                public void handle(MultiplePressedKeysEventHandler.MultiKeyEvent ke) {
//
//                    if (ke.isPressed(KeyCode.LEFT) || ke.isPressed(KeyCode.UP)) {
//                        logicPanzers[0].rotate(-Math.PI / 20);
//                        graphicPanzers[0].transformPanzer(-Math.PI/20);
//                        logicPanzers[0].accelerate();
//                    }
//                    if (ke.isPressed(KeyCode.RIGHT)  || ke.isPressed(KeyCode.UP)) {
//                        logicPanzers[0].rotate(Math.PI / 20);
//                        graphicPanzers[0].transformPanzer(Math.PI/20);
//                        logicPanzers[0].accelerate();
//                    }
//                    if (ke.isPressed(KeyCode.RIGHT)&&  ke.isPressed(KeyCode.ENTER)) {
//                        logicPanzers[0].rotate(Math.PI / 20);
//                        graphicPanzers[0].transformPanzer(Math.PI/20);
//                        logicPanzers[0].fire();
//                    }
//
//                    if (ke.isPressed(KeyCode.LEFT) && ke.isPressed(KeyCode.ENTER)) {
//                        logicPanzers[0].rotate(-Math.PI / 20);
//                        graphicPanzers[0].transformPanzer(-Math.PI/20);
//                        logicPanzers[0].fire();
//                    }
//                    if (ke.isPressed(KeyCode.DOWN) && ke.isPressed(KeyCode.ENTER)) {
//                        logicPanzers[0].backAccelerate();
//                        logicPanzers[0].fire();
//                    }
//                    if (ke.isPressed(KeyCode.UP) && ke.isPressed(KeyCode.ENTER)) {
//                        logicPanzers[0].accelerate();
//                        logicPanzers[0].fire();
//                    }
//                }
//            });
    //TODO



//____________________________________________________________________________________________________________________________________
    //К
    //О
    //Н
    //С
    //Т
    //Р
    //У
    //К
    //Т
    //О
    //Р


    public Level(String fileName) throws IOException {


        parsingInformation(fileName);

        identification();

    }

//________________________________________________________________________________________________________________________________________
    //Считывание информации об уровне из текстового файла
    private void parsingInformation(String fileName) throws IOException {
        FileReader fileReader = new FileReader(new File(fileName));
        int c = 0;


        //Считываем число танков
        panzerDigit = findParametr(fileReader, c);


        //Считываем типы танков
        panzerType = new int[panzerDigit];
        for (int i = 0; i < panzerType.length; i++)
            panzerType[i] = findParametr(fileReader, c);


        //Считываем число вертикальных препятствий
        verticalEl = findParametr(fileReader, c);

        //Считываем число горизонтальных препятствий
        gorizontalEl = findParametr(fileReader, c);

        //Считываем координаты танков
        translatePanzersX = new int[panzerDigit];
        translatePanzersY = new int[panzerDigit];
        for (int i = 0; i < panzerDigit; i++) {
            translatePanzersX[i] = findParametr(fileReader, c);
            translatePanzersY[i] = findParametr(fileReader, c);
        }

        //Считываем координаты вертикальных препятствий
        translateVerticalX = new int[verticalEl];
        translateVerticalY = new int[verticalEl];
        for (int i = 0; i < verticalEl; i++) {
            translateVerticalX[i] = findParametr(fileReader, c);
            translateVerticalY[i] = findParametr(fileReader, c);
        }

        //Считываем координаты горизонтальных препятствий
        translateGorizontalX = new int[gorizontalEl];
        translateGorizontalY = new int[gorizontalEl];
        for (int i = 0; i < verticalEl; i++) {
            translateGorizontalX[i] = findParametr(fileReader, c);
            translateGorizontalY[i] = findParametr(fileReader, c);

        }

        ramka = findParametr(fileReader, c);

        sceneWidt=findParametr(fileReader,c);
        sceneHeight=findParametr(fileReader,c);

    }

    //Используется в parsingInformation
    private int findParametr(FileReader fileReader, int c) throws IOException {
        int parametr = 0;

        do c = fileReader.read();
        while (c != '<');

        while ((c = fileReader.read()) != '>')
            parametr = parametr * 10 + Character.getNumericValue(c);


        return parametr;
    }
//________________________________________________________________________________________________________________________________________

    //Вызывается после того, как вся информация из текстовго файла считана
    private void identification() {

        //СОЗДАНИЕ ТАНКОВ
        panzerCreating();

        //СОЗДАНИЯ И РАССТАНОВКА ПРЕПЯТСТВИЙ
        areaCreating();


        //ДОБАВЛЕНИЕ ГРАФИЧЕСКИХ ЭЛЕМЕНТОВ НА ПАНЕЛЬ
        addGraphicElements();

        //ИДЕНТЕФИКАЦИЯ КНОПОК ДЛЯ УПРАВЛЕНИЯ ТАНКАМИ
        identificationButtons();

    }

    //СОЗДАНИЕ ТАНКОВ
    private void panzerCreating() {

        //Инициализация танков
        logicPanzers = new LogicPanzer[panzerDigit];
        graphicPanzers = new GraphicPanzer [panzerDigit];
        for (int i = 0; i < panzerDigit; i++) {

            switch (panzerType[i]) {
                case 0:
                    logicPanzers[i] = LogicPanzer.LightPanzer();
                    break;
                case 1:
                    logicPanzers[i] = LogicPanzer.HeavyPanzer();
                    break;
            }

        }


        //Установка кооодинат и инициализация графических танков
        for (int i = 0; i < panzerDigit; i++) {
            logicPanzers[i].setTranslate(translatePanzersX[i], translatePanzersY[i]);
            graphicPanzers[i] = new GraphicPanzer (logicPanzers[i], Color.RED, this);
        }

        for (LogicPanzer logicPanzer : logicPanzers)
            logicPanzer.setMyLevel(this);

        //TODO
        //ИДЕНТЕФИКАЦИЯ КОМАНД
//        for (Logic.LogicPanzer logicPanzer1 : logicPanzers)
//            for (Logic.LogicPanzer logicPanzer2 : logicPanzers) {
//                if (logicPanzer1 != logicPanzer2)
//                    logicPanzer1.addOpponent(logicPanzer2);

//            }

        //TODO
        logicPanzers[0].addOpponent(logicPanzers[1]);
        logicPanzers[1].addOpponent(logicPanzers[0]);
    }

    //РАССТАНОВКА ПРЕПЯТСТВИЙ
    private void areaCreating() {
        obstacles = new ArrayList<>();
        //Создание вертикальныз элементов
        for (int i = 0; i < verticalEl; i++)
            obstacles.add(GraphicObstacles.generateVertical(100, translateVerticalX[i], translateVerticalY[i]));


        //Создание горизонтальных элементов
        for (int i = 0; i < gorizontalEl; i++)
            obstacles.add(GraphicObstacles.generateGorizontal(100, translateGorizontalX[i], translateGorizontalY[i]));


        if (ramka == 1) {
            obstacles.add(GraphicObstacles.getUpGorizontal(sceneWidt,sceneHeight));
            obstacles.add(GraphicObstacles.getDownGorizontal(sceneWidt,sceneHeight));
            obstacles.add(GraphicObstacles.getLeftVertical(sceneWidt,sceneHeight));
            obstacles.add(GraphicObstacles.getRightVertical(sceneWidt,sceneHeight));
        }


    }

    //ДОБАВЛЕНИЕ ГРАФИЧЕСКИХ ЭЛЕМЕНТОВ В gameRoot
    private void addGraphicElements() {
        gameRoot = new Pane();

        for ( GraphicPanzer graphicPanzer : graphicPanzers)
            gameRoot.getChildren().addAll(graphicPanzer.getBullets());


        gameRoot.getChildren().addAll(obstacles);

        gameRoot.getChildren().addAll(graphicPanzers);

        scene = new Scene(gameRoot, sceneWidt, sceneHeight);

    }

    //УСТАНОВКА КНОПОК УПРАВЛЕНИЯ
    private void identificationButtons() {

        scene.setOnKeyPressed(event -> {

            //TODO
//            keyHandler.handle(event);

            switch (event.getCode()) {

                case RIGHT:
                    logicPanzers[0].rotate(Math.PI / 20);
                    graphicPanzers[0].transformPanzer(Math.PI/20);
                    break;
                case LEFT:
                    logicPanzers[0].rotate(-Math.PI / 20);
                    graphicPanzers[0].transformPanzer(-Math.PI/20);
                    break;
                case UP:
                    logicPanzers[0].accelerate();
                    break;
                case DOWN:
                    logicPanzers[0].backAccelerate();
                    break;
                case ENTER:
                    logicPanzers[0].fire();
                    break;


                case D:
                    logicPanzers[1].rotate(Math.PI / 20);
                    graphicPanzers[1].transformPanzer(Math.PI / 20);
                    break;
                case A:
                    logicPanzers[1].rotate(-Math.PI / 20);
                    graphicPanzers[1].transformPanzer(-Math.PI/20);
                    break;
                case W:
                    logicPanzers[1].accelerate();
                    break;
                case S:
                    logicPanzers[1].backAccelerate();
                    break;
                case R:
                    logicPanzers[1].fire();
                    break;




                case H:
                    logicPanzers[2].rotate(Math.PI / 20);
                    break;
                case G:
                    logicPanzers[2].rotate(-Math.PI / 20);
                    break;
                case J:
                    logicPanzers[2].accelerate();
                    break;
                case Y:
                    logicPanzers[2].backAccelerate();
                    break;
                case I:
                    logicPanzers[2].fire();
                    break;



                case M:
                    reload();
                    for (LogicPanzer logicPanzer : logicPanzers)
                        logicPanzer.reload();
                    break;


            }

        });

    }


    //ВОЗВРАТ ТАНКОВ НА ИСХОДНЫЕ ПОЗИЦИИ
    private void reload() {
        int number = 0;
        for (LogicPanzer logicPanzer : logicPanzers) {
            logicPanzer.setTranslate(translatePanzersX[number], translatePanzersY[number]);
            number++;

        }


    }

//____________________________________________________________________________________________________________________________________
    //Г
    //Е
    //Т
    //Е
    //Р
    //Ы


    public Scene getScene() {
        return scene;
    }

    public ArrayList<GraphicObstacles> getObstacles() {
        return obstacles;
    }

    public LogicPanzer[] getLogicPanzers() {
        return logicPanzers;
    }

    public GraphicPanzer[] getGraphicPanzers() {
        return graphicPanzers;
    }
//____________________________________________________________________________________________________________________________________
}
