package ru.job4j.io.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.log.UsageSlf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageSlf4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String message = null;
                    String string = input.readLine();
                    if (string.contains("msg=")) {
                        message = string.substring(string.indexOf("=") + 1, string.lastIndexOf(" "));
                    }
                    if ("Exit".equalsIgnoreCase(message)) {
                        output.flush();
                        server.close();
                    } else if (message != null) {
                        if ("Hello".equalsIgnoreCase(message)) {
                            output.write("Hello, dear friend.".getBytes());
                        } else {
                            output.write(message.getBytes());
                        }
                        output.flush();
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in socket/bot", e);
        }
    }
}
