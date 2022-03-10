package ru.netology.javacore;

import com.google.gson.*;

import java.io.*;
import java.net.*;

public class TodoServer {
    protected int port;
    protected Todos todos;

    public TodoServer(int port, Todos todos) {
        this.todos = todos;
        this.port = port;
    }

    public void start() throws IOException, NullPointerException {
        System.out.println("Starting server at port " + port);
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                System.out.println("New connection accepted");
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                String taskLine;
                taskLine = in.readLine();
                if (taskLine.equals("0")) {
                    break;
                }
                todos = gson.fromJson(taskLine, Todos.class);
                String type = todos.getAction();
                String task = todos.getTask();

                switch (type) {
                    case "ADD":
                        todos.addTask(task);
                        break;
                    case "REMOVE":
                        todos.removeTask(task);
                        break;
                }
                String tasks = todos.getAllTasks();
                out.println("Ваши задачи: " + tasks);
            }
        }
    }
}