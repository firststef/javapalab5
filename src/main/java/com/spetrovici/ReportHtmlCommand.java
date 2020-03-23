package com.spetrovici;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReportHtmlCommand implements GenericCommand {
    @Override
    public void run() throws InvalidCatalogException {
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();

        try {
            Catalog catalog = CatalogUtil.loadText(path);
            String str = "<!doctype html>\n" + "<title>LAB 5</title>" + catalog.toHtml();
            Files.write( Paths.get(path + ".html"), str.getBytes());
        }
        catch (InvalidCatalogException | IOException c){
            System.out.println("Catalog could not be loaded - " + c.getMessage());
            throw  new InvalidCatalogException(c);
        }
        System.out.println("Object has been dumped to html successfully");
    }

    @Override
    public String getName() {
        return "report html";
    }
}
