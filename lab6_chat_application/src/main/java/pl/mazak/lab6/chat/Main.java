package pl.mazak.lab6.chat;

import pl.mazak.lab6.chat.client.ClientMain;
import pl.mazak.lab6.chat.server.ServerMain;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> ClientMain.main(args));
        executorService.execute(() -> {
            try {
                ServerMain.main(args);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }
}
