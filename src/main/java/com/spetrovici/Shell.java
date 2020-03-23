package com.spetrovici;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shell {
    public List<GenericCommand> commands = new ArrayList<>();

    public void add (GenericCommand command){
        commands.add(command);
    }

    public void remove (GenericCommand command){
        commands.remove(command);
    }

    public void run(){
        try {
            while (true) {
                Scanner in = new Scanner(System.in);
                String line = in.nextLine();

                boolean solved = false;
                for (int i = 0; i < commands.size(); i++) {
                    if (line.equals(commands.get(i).getName())) {
                        commands.get(i).run();
                        solved = true;
                        break;
                    }
                }

                if (!solved) {
                    System.out.println("Unknown command");
                    break;
                }
            }
        }
        catch (InvalidCatalogException c){
            System.out.println("The command failed " + c.getMessage());
        }
    }
}

