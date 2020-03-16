import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Catalog implements Serializable {
    private String name;
    private String path;

    private Map<String, Document> documents = new HashMap<>();

    public void add (Document doc){
        documents.put(doc.getId(), doc);
    }

    public Document findById(String id){
        return documents.get(id);
    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
