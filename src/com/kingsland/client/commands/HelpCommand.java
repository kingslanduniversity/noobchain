package com.kingsland.client.commands;


import com.kingsland.blockchain.Blockchain;
import com.kingsland.blockchain.Transaction;
import com.kingsland.client.annotations.Command;

import java.io.File;
import java.util.List;
import java.util.Objects;

@Command("help")
public class HelpCommand implements Executable {
    @Override
    public void execute(Blockchain blockchain) {
        System.out.println("********** HELP *********");
        System.out.println("The available commands are:");
        try {
            for (File file : Objects.requireNonNull(new File("src/com/kingsland/client/commands").listFiles())) {
                String filename = file.getName().replace(".java", "");
                Class cmdClass = Class.forName("com.kingsland.client.commands." + filename);
                if (cmdClass.isAnnotationPresent(Command.class)) {
                    Command annotation = (Command) cmdClass.getDeclaredAnnotation(Command.class);
                    String cmdAlias = annotation.value();
                    if (cmdAlias != null && cmdAlias.length() > 0) {
                        System.out.println("- " + cmdAlias);
                    }
                }
            }
        } catch(Exception e) {


        }
    }
}