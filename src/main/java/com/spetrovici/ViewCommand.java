package com.spetrovici;

import java.io.IOException;
import java.util.Scanner;

public class ViewCommand implements GenericCommand {
    @Override
    public void run() throws InvalidCatalogException {
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        String document = in.nextLine();

        try {
            Catalog catalog = CatalogUtil.loadText(path);
            System.out.println(catalog.getDocuments().toString());
            Document doc = catalog.findById(document);
            CatalogUtil.view(doc);
        }
        catch (InvalidCatalogException | IOException c){
            System.out.println("Catalog could not be loaded - " + c.getMessage());
            throw new InvalidCatalogException(c);
        }
        System.out.println("Object has been opened successfully");
    }

    @Override
    public String getName() {
        return "view";
    }
}
