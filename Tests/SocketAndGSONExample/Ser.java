package SocketAndGSONExample;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/**
 * Для теста можно подключиться к этому серверу с помощью утилиты
 * nc localhost 5050
 */
enum MessageType {
    OK, ERROR, READY, STATE
}

class Person {
    MessageType messageType;
    String name;
    Person father;
    int age;
    int houses[];
    List<String> names;

    public Person(String name, Person father, int age, int[] houses, List<String> names) {
        messageType = MessageType.READY;
        this.father = father;
        this.names = names;
        this.houses = houses;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name: " + name + "\nage: " + age + "\nhouses: " + Arrays.toString(houses);
    }
}

public class Ser {
    Socket socket;
    String json;

    public void start() throws IOException {
        socket = new ServerSocket(5050).accept();
        Gson gson = new Gson();
        Person father = new Person("Denny", null, 30, new int[]{}, Arrays.asList());
        Person person = new Person("Alex", father,10,  new int[]{2, 3, 4, 5}, Arrays.asList("Ann", "Bill"));
        json = gson.toJson(person);
    }

    public void send() throws IOException {
        try (OutputStream outputStream = socket.getOutputStream();
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        ) {
            bufferedWriter.write(json);
            bufferedWriter.flush();
        }
    }

    public static void main(String[] args) throws Exception {
        Ser ser = new Ser();
        ser.start();
        ser.send();
    }
}
