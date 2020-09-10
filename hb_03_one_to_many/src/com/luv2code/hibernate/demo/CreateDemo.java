package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {
        //create SessionFactory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .buildSessionFactory();

        //create session
        Session session=factory.getCurrentSession();

        try{
            //use session to save Java object
            //create object

            /*
            Instructor tempInstructor =
                    new Instructor("Chad","Darby","darby@luv2code.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail("http://www.luv2code.com/youtube",
                            "Luv 2 code!!!");
            */
            Instructor tempInstructor =
                    new Instructor("Maadhu","Patel","madhu@luv2code.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail("http://www.youtube.com",
                            "Guitar");

            //associate objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //begin transaction
            session.beginTransaction();

            //save instructor object
            //note: this will also save the details object
            //because of CascadeType.ALL
            //
            System.out.println("\n\nSaving instructor "+tempInstructor);
            session.save(tempInstructor);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done");
        }
        finally {
            factory.close();
        }
    }
}
