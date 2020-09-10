package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
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

            //start a transaction
            session.beginTransaction();
           //get instructor by primary key/id
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class,theId);

            System.out.println("\n\nFound instructor "+tempInstructor);
            //delete the instructors
            if(tempInstructor!=null){
                System.out.println("\n\nDeleting "+tempInstructor);

                //Note: this will also delete associated details object because of CascadeType.ALL
                session.delete(tempInstructor);
            }

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done");
        }
        finally {
            factory.close();
        }
    }
}
