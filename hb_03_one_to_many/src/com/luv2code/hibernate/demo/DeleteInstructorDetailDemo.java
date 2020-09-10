package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {
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
            int theId=3;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class,theId);


            //print instructor detail
            System.out.println("\n\nGetting Instructor Detail "+tempInstructorDetail);

            //removed associated object reference
            //break bi-directional link
            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            //delete instructor detail
            System.out.println("\n\nDeleting tempInstructorDetail:" + tempInstructorDetail);
            session.delete(tempInstructorDetail);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done");
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        finally {
            //handle connection on leak issue
            session.close();
            factory.close();
        }
    }
}
