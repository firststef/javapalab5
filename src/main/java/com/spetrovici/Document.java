package com.spetrovici;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Document implements Serializable {
    private String id;
    private String name;
    private String location;

    private Map<String, Object> tags = new HashMap<>();

    public void addTag(String key, Object obj){
        tags.put(key, obj);
    }

    public Document(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String toHtml(){
        return "<h1>" + name +
                "</h1><br>" + "<p>" + id + "</p><br>" +
                "<p>" + location + "</p><br><div>" +
                tags.entrySet().stream().map(
                        (e) -> "<div><p>Tag:" + e.getKey() + "</p><br>" + e.getValue().toString() + "</div>"
                ).collect(Collectors.joining("<br>")) +
                "</div>";
    }
}
