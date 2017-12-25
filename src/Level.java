import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;


//Класс для создания и хранения карт
public class Level {


    //[0] Для колличества танков, вертикальных ограждений и горизонтальных ограждений
    //[1] Для координат танков,сначала X и Y чередуются identificationDigits[0].get[0]-число танков
    //[2] Для координат вертикальных препятствий X и Y чередуются identificationDigits[0].get[1]-число вертикальных препятствий
    //[3] Для координат горизонтальных препятствий X и Y чередуются identificationDigits[0].get[2]-число горизонтальных препятствий
    //[4] Для рамки либо 1 либо 0
    private ArrayList<Integer>[] identificationDigits = new ArrayList[5];//Собирается при парсе
    private ArrayList<LogicPanzer> firstPanzers = new ArrayList<>();
    private ArrayList<LogicPanzer> secondPanzers = new ArrayList<>();
    private ArrayList<GraphicPanzer> graphicPanzers = new ArrayList<>();//Графические отображения танкова
    private ArrayList<PanzerElement> elements = new ArrayList<>();//Ограждения
    private Scene scene;
    private Pane menu;
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


    public Level(String fileName) throws IOException {


        parsingInformation(fileName);

        identification();

    }

    private void parsingInformation(String fileName) throws IOException {
        FileReader fileReader = new FileReader(new File(fileName));
        for (int i = 0; i < identificationDigits.length; i++)
            identificationDigits[i] = new ArrayList<Integer>();
        int c;
        int digit = 0;
        int i = 0;//Идентификатор листа который заполняем
        boolean pars = false;
        while ((c = fileReader.read()) != -1) {
            while (pars) {
                if ((char) c == '>') {
                    identificationDigits[i].add(digit);
                    pars = false;
                    digit = 0;
                    break;
                }
                digit = digit * 10 + Character.getNumericValue(c);
                c = fileReader.read();
            }
            if ((char) c == '<')
                pars = true;

            if ((char) c == '|')
                i++;


        }

    }

    private void identification() {

        //СОЗДАНИЕ ТАНКОВ
        //!
        //!
        //!
        //ПЕРЕД ПЕРВЫМ ЗАПУСКОМ НОРМАЛЬНО РАССТАВИТЬ ИНДЕКСЫ
        firstPanzers.add(LogicPanzer.LightPanzer());
        secondPanzers.add(LogicPanzer.LightPanzer());
        firstPanzers.get(0).setTranslate(identificationDigits[1].get(0),
                identificationDigits[1].get(1));
        secondPanzers.get(0).setTranslate(identificationDigits[1].get(2),
                identificationDigits[1].get(3));

        graphicPanzers.add(new GraphicPanzer(firstPanzers.get(0), Color.GREEN,this));
        graphicPanzers.add(new GraphicPanzer(secondPanzers.get(0), Color.RED,this));

        //ИДЕНТЕФИКАЦИЯ КОМАНД
        for (LogicPanzer firstPanzer : firstPanzers)
            firstPanzer.setOpponents(secondPanzers);

        for (LogicPanzer secondPanzer : secondPanzers)
            secondPanzer.setOpponents(firstPanzers);


        //СОЗДАНИЯ И РАССТАНОВКА ПРЕПЯТСТВИЙ
        int numberCoordinate = 0;
        for (int i = 0; i < identificationDigits[0].get(1); i++) {
            elements.add(PanzerElement.generateVertical(100, identificationDigits[2].get(numberCoordinate), identificationDigits[2].get(numberCoordinate + 1)));
            numberCoordinate += 2;
        }
        numberCoordinate = 0;
        for (int i = 0; i < identificationDigits[0].get(2); i++) {
            elements.add(PanzerElement.generateGorizontal(100, identificationDigits[3].get(numberCoordinate), identificationDigits[3].get(numberCoordinate + 1)));
            numberCoordinate += 2;
        }

        if (identificationDigits[4].get(0) == 1) {
            elements.add(PanzerElement.getGorizontal());
            elements.add(PanzerElement.getGorizontal2());
            elements.add(PanzerElement.getVertical());
            elements.add(PanzerElement.getVertical2());
        }

        graphicPanzers.get(0).setOnKeyPressed(event -> {
            switch (event.getCode()) {

                case RIGHT:
                    graphicPanzers.get(0).getLogicPanzer().move(PanzerDirection.R);
                    break;
                case LEFT:
                    graphicPanzers.get(0).getLogicPanzer().move(PanzerDirection.L);
                    break;
                case DOWN:
                    graphicPanzers.get(0).getLogicPanzer().move(PanzerDirection.D);
                    break;
                case UP:
                    graphicPanzers.get(0).getLogicPanzer().move(PanzerDirection.U);
                    break;

            }
        });
        graphicPanzers.get(1).setOnKeyPressed(event -> {
            switch (event.getCode()) {

                case D:
                    graphicPanzers.get(0).getLogicPanzer().move(PanzerDirection.R);
                    break;
                case A:
                    graphicPanzers.get(0).getLogicPanzer().move(PanzerDirection.L);
                    break;
                case S:
                    graphicPanzers.get(0).getLogicPanzer().move(PanzerDirection.D);
                    break;
                case W:
                    graphicPanzers.get(0).getLogicPanzer().move(PanzerDirection.U);
                    break;
            }
        });


        gameRoot = new Pane();

        //ИДЕНТИФИКАЦИЯ ГРАФИЧЕСКИХ ПУЛЬ
        for (GraphicPanzer graphicPanzer : graphicPanzers)
            gameRoot.getChildren().addAll(graphicPanzer.getBullets());


        gameRoot.getChildren().addAll(elements);

        gameRoot.getChildren().addAll(graphicPanzers);


        scene = new Scene(gameRoot, PanzerGame.sceneWidt, PanzerGame.sceneHeight);

        identificateButtons();

    }

    private void identificateButtons() {

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {

                case RIGHT:
                    graphicPanzers.get(0).getLogicPanzer().move(PanzerDirection.R);
                    break;
                case LEFT:
                    graphicPanzers.get(0).getLogicPanzer().move(PanzerDirection.L);
                    break;
                case UP:
                    graphicPanzers.get(0).getLogicPanzer().move(PanzerDirection.U);
                    break;
                case DOWN:
                    graphicPanzers.get(0).getLogicPanzer().move(PanzerDirection.D);
                    break;
                case ENTER:
                    //myLevel.getGraphicPanzers().get(0).fire();
                    break;

                case A:
                    graphicPanzers.get(1).getLogicPanzer().move(PanzerDirection.L);
                    break;
                case D:
                    graphicPanzers.get(1).getLogicPanzer().move(PanzerDirection.R);
                    break;
                case W:
                    graphicPanzers.get(1).getLogicPanzer().move(PanzerDirection.U);
                    break;
                case S:
                    graphicPanzers.get(1).getLogicPanzer().move(PanzerDirection.D);
                    break;
                case SPACE:
                    break;

            }

        });

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

    public ArrayList<PanzerElement> getElements() {
        return elements;
    }

    public ArrayList<GraphicPanzer> getGraphicPanzers() {
        return graphicPanzers;
    }

    public ArrayList<LogicPanzer> getFirstPanzers() {
        return firstPanzers;
    }

    public ArrayList<LogicPanzer> getSecondPanzers() {
        return secondPanzers;
    }
//____________________________________________________________________________________________________________________________________
}
