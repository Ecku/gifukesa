package main;

import java.io.Serializable;

public class Course implements Serializable, PrintInfo {
    private int maxNumberOfStudents;
    private String name;
    private String id;

    public Course(int maxNumberOfStudents, String name, String id) {
        this.maxNumberOfStudents = maxNumberOfStudents;
        this.name = name;
        this.id = id;
    }
    
    
    
    @Override
    public String getInformation() {
        return id.toString() + " " + name.toString();
    } 
}
