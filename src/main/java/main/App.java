package main;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Tervetuloa Gifu-järjestelmään"); 
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        Gifu gifu = null;
        Course course = null;
        Student student = null;
        int courseNbr;
        String courseNbrString;
        String studentNbrString;
        int studentNbr,i;
        EventLogger eventLogger = new EventLogger();
    
        
        eventLogger.writeLog("Ohjelma käynnistyy");
        System.out.println("Mille yliopistolle haluat ottaa järjestelmän käyttöön?");
        String universityString = sc.nextLine();
        gifu = new Gifu(universityString, eventLogger);

        while(!exit) {
            System.out.println("1) Luo uusi kurssi, 2) Luo uusi opiskelija, 3) Listaa kurssit, 4) Listaa opiskelijat, 5) Lisää opiskelija kurssille, 6) Anna kurssiarvosanat, 7) Listaa kurssilla olevat opiskelijat, 8) Listaa opiskelijan arvosanat, 9) Listaa kaikkien kurssien kaikkien opiskelijoiden arvosanat, 0) Lopeta ohjelma");

            if(sc.hasNext()) {
                i = 0;
                String stringInput = sc.nextLine();
                i = Integer.parseInt(stringInput);

                switch(i) {
                    case 1:
                        // Luo uusi kurssi
                        System.out.println("Anna kurssin nimi:");
                        String courseString = sc.nextLine();
                        System.out.println("Anna kurssin ID:");
                        String courseIdString = sc.nextLine();
                        System.out.println("Anna kurssin maksimi opiskelijamäärä:");
                        String maxString = sc.nextLine();
                        int max = Integer.parseInt(maxString);
                        course = new Course(max, courseString, courseIdString);
                        gifu.addCourse(course);
                        break;
                    case 2:
                        // Luo uusi opiskelija
                        System.out.println("Anna opiskelijan nimi:");
                        String studentNameString = sc.nextLine();
                        System.out.println("Anna opiskelijan opiskelijanumero:");
                        String studentNumberString = sc.nextLine();
                        student = new Student(studentNameString, studentNumberString);
                        //System.out.println(student.getInformation());
                        gifu.addStudent(student);
                        break;
                    case 3:
                        gifu.listCourses();
                        break;
                    case 4:
                        gifu.listStudents();
                        break;
                    case 5:
                        gifu.listCourses();
                        System.out.println("Mille kurssille haluat lisätä opiskelijan? Syötä kurssin numero:");
                        courseNbrString = sc.nextLine();
                        courseNbr = Integer.parseInt(courseNbrString);
                        course = gifu.getCourse(courseNbr);
                        gifu.listStudents();
                        System.out.println("Minkä opiskelijan haluat lisätä kurssille? Syötä opiskelijan numero:");
                        studentNbrString = sc.nextLine();
                        studentNbr = Integer.parseInt(studentNbrString);
                        student = gifu.getStudent(studentNbr);
                        gifu.enrollStudent(student, course);
                        break;
                    case 6:
                        gifu.listCourses();
                        System.out.println("Minkä kurssin haluat arvostella? Syötä kurssin numero:");
                        courseNbrString = sc.nextLine();
                        courseNbr = Integer.parseInt(courseNbrString);
                        course = gifu.getCourse(courseNbr);
                        ArrayList<Enrollment> enrollmentsOfCourse = gifu.getEnrollments(course);
                        for (Enrollment enrollmentOfCourse : enrollmentsOfCourse) {
                            System.out.println("Anna arvosana opiskelijalle " + enrollmentOfCourse.getStudent().getInformation());
                            courseNbrString = sc.nextLine();
                            int courseGrade = Integer.parseInt(courseNbrString);
                            enrollmentOfCourse.gradeCourse(courseGrade);                           
                        }
                        break;
                    case 7:
                        gifu.listCourses();
                        System.out.println("Minkä kurssin opiskelijat haluat listata? Syötä kurssin numero:");
                        courseNbrString = sc.nextLine();
                        courseNbr = Integer.parseInt(courseNbrString);
                        course = gifu.getCourse(courseNbr); 
                        ArrayList<Enrollment> enrollmentsOfCourse1 = gifu.getEnrollments(course);
                        boolean enrollmentsOfCourse1IsEmpty = enrollmentsOfCourse1.isEmpty();
                        if (!enrollmentsOfCourse1IsEmpty) {
                            for (Enrollment enrollmentOfCourse : enrollmentsOfCourse1) {
                                System.out.println(enrollmentOfCourse.getStudent().getInformation() +", arvosana: " + enrollmentOfCourse.getGrade());;                           
                            }
                        }
                        break;
                    case 8:
                        gifu.listStudents();
                        System.out.println("Minkä opiskelijan arvosanat haluat listata? Syötä opiskelijan numero:");
                        studentNbrString = sc.nextLine();
                        studentNbr = Integer.parseInt(studentNbrString);
                        student = gifu.getStudent(studentNbr); 
                        System.out.println("Opiskelijan " + student.getInformation() + " arvosanat:");
                        ArrayList<Enrollment> enrollmentsOfStudent = gifu.getEnrollments(student);
                        boolean enrollmentsOfStudentIsEmpty = enrollmentsOfStudent.isEmpty();
                        if (!enrollmentsOfStudentIsEmpty) {
                            for (Enrollment enrollmentOfStudent : enrollmentsOfStudent) {
                                System.out.println(enrollmentOfStudent.getCourse().getInformation() +", arvosana: " + enrollmentOfStudent.getGrade());                           
                            }
                        } 
                        break;
                    case 9:
                        ArrayList<Course> courses = gifu.getCourses();
                        ArrayList<Enrollment> enrollments = null;
                        for (Course courseEelement : courses) {
                            System.out.println(courseEelement.getInformation());
                            enrollments = gifu.getEnrollments(courseEelement);
                            for(Enrollment enrollmentElement: enrollments) {
                               System.out.println(enrollmentElement.getStudent().getInformation() + ", arvosana:" + enrollmentElement.getGrade());
                            }
                        
                        }
                        break;

                    case 10:
                        gifu.saveAll();
                        break;

                    case 11: 
                        gifu.loadAll();
                        break;
                    case 0:
                        System.out.println("Kiitos ohjelman käytöstä.");
                        exit = true;
                        break;
                    default:
                        System.out.println("Syöte oli väärä");
                        break;

                }
            }

        }
        sc.close();
        eventLogger.writeLog("Ohjelma sulkeutuu");


    }
}
