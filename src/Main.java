import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() {
        try {
            Catalog catalog = new Catalog("Java Resources", "catalog.ser");
            Document doc = new Document("java1", "Java Course 1", "D:\\facultate\\anul_2\\javapa lab\\javapalab5\\README.md");
            doc.addTag("type", "Slides");
            catalog.add(doc);
            CatalogUtil.save(catalog);
        }
        catch (IOException e){
            System.out.println("An error ocurred - " + e.getMessage());
            return;
        }
        System.out.println("Test passed");
    }

    private void testLoadView() {
        try {
            Catalog catalog = CatalogUtil.load("catalog.ser");
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
}
