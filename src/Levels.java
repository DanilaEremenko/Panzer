import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;


//Класс для создания и хранения карт
public class Levels {


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
    private Scene menu;
    private Pane root;

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


    public Levels(String fileName) throws IOException {


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
        int numberCoordinate = 0;
        firstPanzers.add(LogicPanzer.LightPanzer());
        secondPanzers.add(LogicPanzer.LightPanzer());
        firstPanzers.get(0).setTranslate(identificationDigits[1].get(numberCoordinate),
                identificationDigits[1].get(numberCoordinate + 1));
        secondPanzers.get(0).setTranslate(identificationDigits[1].get(numberCoordinate),
                identificationDigits[1].get(numberCoordinate + 1));

        graphicPanzers.add(new GraphicPanzer(firstPanzers.get(0), Color.GREEN));
        graphicPanzers.add(new GraphicPanzer(secondPanzers.get(0), Color.RED));

        //ИДЕНТЕФИКАЦИЯ КОМАНД
        for (LogicPanzer firstPanzer : firstPanzers)
            firstPanzer.setOpponents(secondPanzers);

        for (LogicPanzer secondPanzer : secondPanzers)
            secondPanzer.setOpponents(firstPanzers);


        //СОЗДАНИЯ И РАССТАНОВКА ПРЕПЯТСТВИЙ
        numberCoordinate = 0;
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


        root = new Pane();
        //ИДЕНТИФИКАЦИЯ ГРАФИЧЕСКИХ ПУЛЬ

        for (GraphicPanzer graphicPanzer : graphicPanzers)
            root.getChildren().addAll(graphicPanzer.getBullets());


        for (PanzerElement element : elements)
            root.getChildren().addAll(element);

        for (GraphicPanzer graphicPanzer : graphicPanzers)
            root.getChildren().addAll(graphicPanzer);


        scene = new Scene(root, PanzerGame.sceneWidt, PanzerGame.sceneHeight);
        menu = new Scene(PanzerMenu.menuBox, PanzerGame.sceneWidt, PanzerGame.sceneHeight);

    }

//____________________________________________________________________________________________________________________________________

    //Г
    //Е
    //Т
    //Е
    //Р
    //Ы


    public Scene getMenu() {
        return menu;
    }

    public Scene getScene() {
        return scene;
    }

    public ArrayList<PanzerElement> getElements() {
        return elements;
    }

    public ArrayList<GraphicPanzer> getGraphicPanzers() {
        return graphicPanzers;
    }

//____________________________________________________________________________________________________________________________________
}
