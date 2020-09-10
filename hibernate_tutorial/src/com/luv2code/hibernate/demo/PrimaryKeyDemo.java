package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
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
            //create 3 student object
            System.out.println("Create new student object...");
            Student tempStudent1 =new Student("John","Doe","john@luv2code.com");
            Student tempStudent2 =new Student("Mary","public","mary@luv2code.com");
            Student tempStudent3 =new Student("Bonita","Applebum","bonita@luv2code.com");


            //begin transaction
            session.beginTransaction();

            //save student object
            System.out.println("Saving student");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done");
        }
        finally {
            factory.close();
        }
    }


}
