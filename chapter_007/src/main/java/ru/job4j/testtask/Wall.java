package ru.job4j.testtask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.Scanner;

/*package ru.job4j.testtask;

public class Wall implements Runnable {
    private int x;
    private int y;

    Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public void run() {

    }
}*/
import java.io.*;
import java.net.*;
import java.util.*;
public class Wall {
    public static void main(String[] args) throws IOException {


        try (ServerSocket s = new ServerSocket(8189)) {
            try (Socket incoming = s.accept()) {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();
                try (Scanner in = new Scanner(inStream)) {
                    PrintWriter out = new PrintWriter(outStream, true); // автоматическая очистка
                    out.println("Hello! Enter BYE to exit.");
                    boolean done = false;
                    while (!done && in.hasNextLine()) {
                        String line = in.nextLine();
                        out.println("Echo: " + line);
                        if (line.trim().equals("BYE")) done = true;
                    }
                }
            }
        }
    }
}

