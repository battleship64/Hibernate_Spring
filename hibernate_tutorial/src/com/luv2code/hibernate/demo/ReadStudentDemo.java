package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args) {
        //create SessionFactory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();

        //create session
        Session session=factory.getCurrentSession();

        try{
            //use session to save Java object
            //create object
            System.out.println("Create new student object...");
            Student tempStudent =new Student("Daffy","Duck","daffy@luv2code.com");


            //begin transaction
            session.beginTransaction();

            //save student object
            System.out.println("Saving student");
            System.out.println(tempStudent);
            session.save(tempStudent);

            //commit transaction
            session.getTransaction().commit();


            //retreive student
            //find primary key
            System.out.println("saved student Generated id "+tempStudent.getId());

            //get new session start transaction
            session=factory.getCurrentSession();
            session.beginTransaction();


            //retrieve student based on id primary key
            System.out.println("\nGetting student with id "+tempStudent.getId());

            Student myStudent = session.get(Student.class,tempStudent.getId());
            System.out.println("Get complete " +myStudent);

            //commit transaction
            session.getTransaction().commit();


            System.out.println("Done");

        }
        finally {
            factory.close();
        }
    }
}
