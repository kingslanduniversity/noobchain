package com.kingsland.client;

import com.kingsland.blockchain.*;
import com.kingsland.client.annotations.Command;
import com.kingsland.client.commands.Executable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.io.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static final String prompt = "> ";
    private static Map<String, Executable> cachedCommands = new HashMap<>();

    private static Blockchain blockchain = new Blockchain();


    public static void main(String[] args) {
        String cmd = "";
        while (true) {
            System.out.print(prompt);

            cmd = scanner.nextLine();
            parseCmd(cmd);
        }
    }

    private static void parseCmd(String cmd) {
        cmd = cmd.trim();
        cmd = cmd.replaceAll("\\s+", " ");

        try {
            if (cachedCommands.containsKey(cmd)) {
                cachedCommands.get(cmd).execute(blockchain);

                return;
            }

            for (File file : Objects.requireNonNull(new File("src/com/kingsland/client/commands").listFiles())) {
                String filename = file.getName().replace(".java", "");
                Class cmdClass = Class.forName("com.kingsland.client.commands." + filename);
                if (cmdClass.isAnnotationPresent(Command.class)) {
                    Command annotation = (Command)cmdClass.getDeclaredAnnotation(Command.class);
                    String cmdAlias = annotation.value();
                    if (cmdAlias.equals(cmd)) {
                        Executable executable = (Executable)cmdClass.newInstance();
                        executable.execute(blockchain);

                        cachedCommands.putIfAbsent(cmdAlias, executable);
                        return;
                    }
                }
            }

            System.out.println("Sorry, your command \"" + cmd + "\" is invalid.");
            System.out.println("Try \"help\".");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
