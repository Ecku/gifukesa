package main;

public class Enrollment {
    private int grade;
    private Student student;
    private Course course;
    public void gradeCourse(int grade) {
        this.grade = grade;
    }

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.grade = -1;
    }
    
    public Student getStudent() {
        return this.student;
    }

    public int getGrade() {
        return grade;
    }

    public Course getCourse() {
        return course;
    }
    
}
