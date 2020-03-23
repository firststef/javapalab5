package com.spetrovici;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class CatalogUtil {
    public static void save(Catalog catalog)
        throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path)
        throws InvalidCatalogException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
            return (Catalog) ois.readObject();
        }
        catch (FileNotFoundException f){
            System.out.println("File not found");
            throw new InvalidCatalogException(f);
        }
        catch (IOException i){
            System.out.println("IOException error");
            throw new InvalidCatalogException(i);
        }
        catch (ClassNotFoundException cl){
            System.out.println("Class error");
            throw new InvalidCatalogException(cl);
        }
    }

    public static void view(Document doc)
        throws IOException {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(doc.getLocation()));
        }
        catch (URISyntaxException exc){
            File f = new File(doc.getLocation());
            URI s = f.toURI();
            desktop.browse(s);
        }
    }
}
