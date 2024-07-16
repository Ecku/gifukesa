package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Gifu {


     private String university;
     private ArrayList<Course> courses = new ArrayList<>();
     private ArrayList<Enrollment> enrollments = new ArrayList<>();
     private ArrayList<Student> students = new ArrayList<>();
     final private String FILENAME, FILENAME2, FILENAME3;
     EventLogger eventLogger;
 
     public Gifu(String university, EventLogger eventLogger) {
         this.university = university;
         this.FILENAME = "course.data";
         this.FILENAME2 = "enrollment.data";
         this.FILENAME3 = "student.data";
         this.eventLogger = eventLogger;
     }
     
     
     public void addCourse(Course course) {
         courses.add(course);
     }
     
     public Course getCourse(int id) {
         return courses.get(id);
     }
     
     public ArrayList<Course> getCourses(){
         return courses;
     }
     
     public void listCourses(){
         int i = 0;
         for (Course course : courses) {
             System.out.println(i++ + ") " + course.getInformation());
         }
 
     }
     
     public void addStudent(Student student) {
         students.add(student);
     }
     
     public Student getStudent(int id){
         return students.get(id);
     }
     
     public void listStudents() {
         int i = 0;
         for (Student student : students) {
             System.out.println(i++ + ") " + student.getInformation());
         }      
     }
     
     public void enrollStudent(Student student, Course course) {
         Enrollment enrollment = new Enrollment(student, course);
         enrollments.add(enrollment);
     }
     
     public ArrayList<Enrollment> getEnrollments(Course courseParameter) {
         // Returns enrollments of the course
         ArrayList<Enrollment> studentsOnCourse = new ArrayList<>();
         int i = 0;
         for (Enrollment enrollment : enrollments) {
             if(enrollment.getCourse() == courseParameter) {
                 studentsOnCourse.add(enrollment);
 //                System.out.println(i++ + ") " + enrollment.getCourse().getInformation());
 //                System.out.println(i++ + ") " + enrollment.getStudent().getInformation());
             }
         }
                    
         return studentsOnCourse;
     }
     
     public ArrayList<Enrollment> getEnrollments(Student studentParameter) {
         // Returns enrollments of the student
         ArrayList<Enrollment> coursesOfStudent = new ArrayList<>();
         int i = 0;
         for (Enrollment enrollment : enrollments) {
             if(enrollment.getStudent() == studentParameter) {
                 coursesOfStudent.add(enrollment);
 //                System.out.println(i++ + ") " + enrollment.getCourse().getInformation());
 //                System.out.println(i++ + ") " + enrollment.getStudent().getInformation());
             }
             
         }
 
         return coursesOfStudent;
     }

    public void saveAll() {
        try {
            ObjectOutputStream courseWriter = new ObjectOutputStream(new FileOutputStream(FILENAME));
            courseWriter .writeObject(courses);
            courseWriter.close();
            eventLogger.writeLog("Kurssit tallennettu tiedostoon");
        } catch (IOException e) {
            System.out.println("kurssien tallentaminen ei onnistunut");
            e.printStackTrace();
        }

        try {
            ObjectOutputStream enrollmentWriter = new ObjectOutputStream(new FileOutputStream(FILENAME2));
            enrollmentWriter .writeObject(enrollments);
            enrollmentWriter.close();
            eventLogger.writeLog("Ilmoittautumiset tallennettu tiedostoon");
        } catch (IOException e) {
            System.out.println("Ilmoiuttautumisien tallentaminen ei onnistunut");
            e.printStackTrace();
        }

        try {
            ObjectOutputStream studentWriter = new ObjectOutputStream(new FileOutputStream(FILENAME3));
            studentWriter .writeObject(students);
            studentWriter.close();
            eventLogger.writeLog("Opiskelijat tallennettu tiedostoon");
        } catch (IOException e) {
            System.out.println("Opiskelioiden tallentaminen ei onnistunut");
            e.printStackTrace();
        }
    }

    public void loadAll() {
        try {
            ObjectInputStream courseReader = new ObjectInputStream(new FileInputStream(FILENAME));
            courses = (ArrayList<Course>) courseReader.readObject();
            courseReader.close();
            eventLogger.writeLog("Kurssit ladattu tiedostosta");
            for (Course course : courses) {
                // eventloggeria ei voida serialoida, asetetaan osoitteet manuaalisesti
                //student.putEventLogger(eventLogger);
            } 

        } catch (FileNotFoundException e) {
            System.out.println("Kurssien lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Kurssien lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Kurssien lukeminen ei onnistunut");
            e.printStackTrace();
        }


        try {
            ObjectInputStream enrollementReader = new ObjectInputStream(new FileInputStream(FILENAME2));
            enrollments = (ArrayList<Enrollment>) enrollementReader.readObject();
            enrollementReader.close();
            eventLogger.writeLog("Ilmoittautumiset ladattu tiedostosta");
            for (Enrollment enrollment : enrollments) {
                // eventloggeria ei voida serialoida, asetetaan osoitteet manuaalisesti
                //student.putEventLogger(eventLogger);
            } 

        } catch (FileNotFoundException e) {
            System.out.println("Ilmoittautumisien lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ilmoittautumisien lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Ilmoittautumisien lukeminen ei onnistunut");
            e.printStackTrace();
        }



        try {
            ObjectInputStream studentReader = new ObjectInputStream(new FileInputStream(FILENAME3));
            students = (ArrayList<Student>) studentReader.readObject();
            studentReader.close();
            eventLogger.writeLog("Opiskelijat ladattu tiedostosta");
            for (Student student : students) {
                // eventloggeria ei voida serialoida, asetetaan osoitteet manuaalisesti
                //student.putEventLogger(eventLogger);
            } 

        } catch (FileNotFoundException e) {
            System.out.println("Opiskelijat lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Opiskelijoiden lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Opiskelijoiden lukeminen ei onnistunut");
            e.printStackTrace();
        }

    }
     
     
     
}
