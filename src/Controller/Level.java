package Controller;

import Graphic.GraphicPanzer;
import Graphic.PanzerDirection;
import Logic.LogicPanzer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
    private ArrayList<GraphicPanzer.PanzerElement> elements;//Ограждения
    private Scene scene;
    private Pane gameRoot;

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


    Level(String fileName) throws IOException {


        parsingInformation(fileName);

        identification();

    }


    //Считывание информации об уровне из текстового файла
    private void parsingInformation(String fileName) throws IOException {
        FileReader fileReader = new FileReader(new File(fileName));
        int c = 0;


        //Считываем число танков
        panzerDigit = findParametr(fileReader, c);
        panzerType = new int[panzerDigit];

        //Считываем типы танков
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
        graphicPanzers = new GraphicPanzer[panzerDigit];
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
            graphicPanzers[i] = new GraphicPanzer(logicPanzers[i], Color.RED, this);
        }

        for (LogicPanzer logicPanzer : logicPanzers)
            logicPanzer.setMyLevel(this);


        //TODO
        graphicPanzers[0].setTranslateLabel(50, 0);
        graphicPanzers[1].setTranslateLabel(50, 30);


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
        elements = new ArrayList<>();
        //Создание вертикальныз элементов
        for (int i = 0; i < verticalEl; i++)
            elements.add(GraphicPanzer.PanzerElement.generateVertical(100, translateVerticalX[i], translateVerticalY[i]));


        //Создание горизонтальных элементов
        for (int i = 0; i < gorizontalEl; i++)
            elements.add(GraphicPanzer.PanzerElement.generateGorizontal(100, translateGorizontalX[i], translateGorizontalY[i]));


        if (ramka == 1) {
            elements.add(GraphicPanzer.PanzerElement.getUpGorizontal());
            elements.add(GraphicPanzer.PanzerElement.getDownGorizontal());
            elements.add(GraphicPanzer.PanzerElement.getLeftVertical());
            elements.add(GraphicPanzer.PanzerElement.getRightVertical());
        }


    }

    //ДОБАВЛЕНИЕ ГРАФИЧЕСКИХ ЭЛЕМЕНТОВ В gameRoot
    private void addGraphicElements() {
        gameRoot = new Pane();

        for (GraphicPanzer graphicPanzer : graphicPanzers)
            gameRoot.getChildren().addAll(graphicPanzer.getBullets());


        gameRoot.getChildren().addAll(elements);

        gameRoot.getChildren().addAll(graphicPanzers);

        for (GraphicPanzer graphicPanzer : graphicPanzers)
            gameRoot.getChildren().add(graphicPanzer.getHealthLabel());


        scene = new Scene(gameRoot, Game.sceneWidt, Game.sceneHeight);

    }

    //УСТАНОВКА КНОПОК УПРАВЛЕНИЯ
    private void identificationButtons() {

        scene.setOnKeyPressed(event -> {

            switch (event.getCode()) {

                case RIGHT:
                    graphicPanzers[0].getLogicPanzer().move(PanzerDirection.R);
                    break;
                case LEFT:
                    graphicPanzers[0].getLogicPanzer().move(PanzerDirection.L);
                    break;
                case UP:
                    graphicPanzers[0].getLogicPanzer().move(PanzerDirection.U);
                    break;
                case DOWN:
                    graphicPanzers[0].getLogicPanzer().move(PanzerDirection.D);
                    break;
                case ENTER:
                    logicPanzers[0].fire();
                    break;

                case A:
                    graphicPanzers[1].getLogicPanzer().move(PanzerDirection.L);
                    break;
                case D:
                    graphicPanzers[1].getLogicPanzer().move(PanzerDirection.R);
                    break;
                case W:
                    graphicPanzers[1].getLogicPanzer().move(PanzerDirection.U);
                    break;
                case S:
                    graphicPanzers[1].getLogicPanzer().move(PanzerDirection.D);
                    break;
                case R:
                    logicPanzers[1].fire();
                    break;


                case H:
                    graphicPanzers[2].getLogicPanzer().move(PanzerDirection.D);
                    break;
                case G:
                    graphicPanzers[2].getLogicPanzer().move(PanzerDirection.L);
                    break;
                case J:
                    graphicPanzers[2].getLogicPanzer().move(PanzerDirection.R);
                    break;
                case Y:
                    graphicPanzers[2].getLogicPanzer().move(PanzerDirection.U);
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

    public ArrayList<GraphicPanzer.PanzerElement> getElements() {
        return elements;
    }

    public LogicPanzer[] getLogicPanzers() {
        return logicPanzers;
    }

    public GraphicPanzer[] getGraphicPanzers() {
        return graphicPanzers;
    }
    //____________________________________________________________________________________________________________________________________
}
