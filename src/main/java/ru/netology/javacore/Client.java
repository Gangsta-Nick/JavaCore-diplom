package ru.netology.javacore;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    public static char pickRandomChar() {
        String chars = "ABCDEFG";
        return chars.charAt(new Random().nextInt(chars.length()));
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[]{"Спорт", "Уборка", "Магазин", "Прогулка", "Уроки", "Сон", "Кино", "Еда"};
        int action;
        String actionStr;
        int taskNumber;
        while (true) {
            try (Socket socket = new Socket("localhost", 8989);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                System.out.println("1 Добавить задачу\n"
                        + "2 Удалить задачу\n"
                        + "0 Выход\n");
                System.out.print("Выберите действие: ");
                action = scanner.nextInt();
                if (action == 0) {
                    break;
                }
                for (int i = 0; i < tasks.length; i++) {
                    System.out.println(i + 1 + " " + tasks[i]);
                }
                System.out.print("Введите номер задачи: ");
                taskNumber = scanner.nextInt();
                actionStr = (action == 1) ? "ADD" : "REMOVE";
                out.println("{ \"type\": \"" + actionStr + "\", \"task\": \"" + tasks[taskNumber - 1] + "\" }");
                System.out.println(in.readLine());
            }
        }
    }
}
