package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndStudentsDemo {
    public static void main(String[] args) {
        //create SessionFactory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .addAnnotatedClass(Review.class)
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();

        //create session
        Session session=factory.getCurrentSession();

        try{
            //use session to save Java object
            //create object

            //begin transaction
            session.beginTransaction();

            //create a course
            Course tempCourse = new Course("Pacman - How to Score One Million Points");

            //save the course
            System.out.println("\nSave course complete :"+tempCourse);
            session.save(tempCourse);

            //create students
            Student tempStudent1 = new Student("John","Doe","john@luv2code.com");
            Student tempStudent2 = new Student("Mary","Public","mary@luv2code.com");
            //add students to course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);
            //save students
            System.out.println("\nSaving students");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved students "+tempCourse.getStudents());

            session.getTransaction().commit();
            System.out.println("Done");
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
