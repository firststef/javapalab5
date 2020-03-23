package com.spetrovici;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        App app = new App();
        //app.testCreateSave();
        //app.testLoadView();
        app.runShell();
    }

    public void testCreateSave() {
        try {
            Catalog catalog = new Catalog("Java Resources", "catalog.ser");
            Document doc = new Document("java1", "Java Course 1", "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
            //Document doc = new Document("java1", "Java Course 1", "D:\\README.md");
            doc.addTag("type", "Slides");
            catalog.add(doc);
            //CatalogUtil.save(catalog);
            CatalogUtil.saveText(catalog);
        }
        catch (IOException e){
            System.out.println("An error ocurred - " + e.getMessage());
            return;
        }
        System.out.println("Test passed");
    }

    public void testLoadView() {
        try {
            //Catalog catalog = CatalogUtil.load("catalog.ser");
            Catalog catalog = CatalogUtil.loadText("catalog.ser.json");
            Document doc = catalog.findById("java1");
            CatalogUtil.view(doc);
        }
        catch (InvalidCatalogException c){
            System.out.println("Catalog could not be loaded - " + c.getMessage());
            return;
        }
        catch (IOException e){
            System.out.println("Catalog file not found");
            return;
        }
        System.out.println("Test passed");
    }

    public void runShell(){
        Shell sh = new Shell();
        sh.add(new ReportHtmlCommand());
        sh.add(new LoadCommand());
        sh.add(new ListCommand());
        sh.add(new ViewCommand());
        sh.run();
    }
}