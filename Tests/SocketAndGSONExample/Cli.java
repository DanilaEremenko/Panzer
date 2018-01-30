package SocketAndGSONExample;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cli {
    Socket socket;

    Cli() throws IOException {
        socket = new Socket("localhost", 5050);
    }

    public void read() throws IOException {
        try (InputStream inputStream = socket.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String str = bufferedReader.readLine();
            Gson gson = new Gson();
            Person person = gson.fromJson(gson.toJson(gson.fromJson(str, Person.class).father), Person.class);
            System.out.println(str);
            System.out.println(person.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        Cli cli = new Cli();
        cli.read();
    }
}
