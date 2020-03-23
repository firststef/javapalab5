package com.spetrovici;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name + '\"' +
                ", \"path\":\"" + path + '\"' +
                ", \"documents\":" + new JSONObject(documents).toString() +
                '}';
    }

    public String toHtml(){
        return "<h1>Catalog:" + name +
                "</h1><br>" + "<p>" + path + "</p><br><div>" +
                documents.values().stream().map(
                        document -> "<div>" + document.toHtml() + "</div>"
                ).collect(Collectors.joining("<br>")) + "</div><br>";
    }

    public Map<String, Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Map<String, Document> documents) {
        this.documents = documents;
    }
}
