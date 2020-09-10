package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {
    public static void main(String[] args) {
        //create SessionFactory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .buildSessionFactory();

        //create session
        Session session=factory.getCurrentSession();

        try{
            //use session to save Java object
            //create object

            //begin transaction
            session.beginTransaction();
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class,theId);

            System.out.println("\n\nluv 2 code Instructor :"+tempInstructor);

            System.out.println("\n\nluv 2 code Courses :"+tempInstructor.getCourses());

            session.getTransaction().commit();

            //close session
            session.close();
            System.out.println("LUv2 code : The session is now closed!\n\n");

            //option 1 : call getter method while session is open
            //

            //get course for the instructor
            System.out.println("\n\nluv 2 code Courses :"+tempInstructor.getCourses());

            System.out.println("luv2code Done");
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
