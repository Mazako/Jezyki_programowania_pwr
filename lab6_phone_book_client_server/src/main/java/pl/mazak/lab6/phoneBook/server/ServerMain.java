/*
 *  Laboratorium 6
 *
 *   Autor: Michal Maziarz, 263913
 *    Data: Styczeń 2023 r.
 */
package pl.mazak.lab6.phoneBook.server;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        new PhoneBookServer().start();
    }
}
