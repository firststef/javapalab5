package com.spetrovici;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

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

    public static void saveText(Catalog catalog)
            throws IOException {
        String path = catalog.getPath() + ".json";
        Files.write( Paths.get(path), catalog.toString().getBytes());
    }

    public static Catalog loadText(String path)
            throws InvalidCatalogException {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONObject obj = new JSONObject(content);
            String name = obj.getString("name");
            String pathValue = obj.getString("path");
            Catalog catalog = new Catalog(name, pathValue);
            JSONObject documents = obj.getJSONObject("documents");
            Iterator<String> keys = documents.keys();
            while(keys.hasNext()) {
                String key = keys.next();
                JSONObject doc = documents.getJSONObject(key);
                Document d = new Document(doc.getString("id"), doc.getString("name"), doc.getString("location"));
                JSONObject tags = doc.getJSONObject("tags");
                Iterator<String> keyz = tags.keys();
                while(keyz.hasNext()) {
                    String tagKey = keyz.next();
                    d.addTag(tagKey, tags.get(tagKey));
                }
                catalog.add(d);
            }
            return catalog;
        }
        catch (FileNotFoundException f){
            System.out.println("File not found");
            throw new InvalidCatalogException(f);
        }
        catch (IOException | JSONException i){
            System.out.println("IOException error");
            throw new InvalidCatalogException(i);
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
