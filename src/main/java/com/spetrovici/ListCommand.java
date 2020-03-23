package com.spetrovici;

import java.util.Scanner;
import java.util.stream.Collectors;

public class ListCommand implements GenericCommand {
    @Override
    public void run() throws InvalidCatalogException {
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();

        try {
            Catalog catalog = CatalogUtil.loadText(path);
            System.out.println(catalog.getDocuments().values().stream().map(Object::toString).collect(Collectors.joining(",")));
        }
        catch (InvalidCatalogException c){
            System.out.println("Catalog could not be loaded - " + c.getMessage());
            throw c;
        }
    }

    @Override
    public String getName() {
        return "list";
    }
}
