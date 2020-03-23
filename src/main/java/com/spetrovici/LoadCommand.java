package com.spetrovici;

import java.io.IOException;
import java.util.Scanner;

public class LoadCommand implements GenericCommand {
    @Override
    public void run() throws InvalidCatalogException {
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();

        try {
            Catalog catalog = CatalogUtil.loadText(path);
        }
        catch (InvalidCatalogException c){
            System.out.println("Catalog could not be loaded - " + c.getMessage());
            throw c;
        }
        System.out.println("Object has been loaded successfully");
    }

    @Override
    public String getName() {
        return "load";
    }
}
