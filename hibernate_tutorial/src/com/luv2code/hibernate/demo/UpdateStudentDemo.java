package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args) {
        //create SessionFactory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();

        //create session
        Session session=factory.getCurrentSession();

        try{
            int studentId = 1;

            //get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retreive object based on the id : primary key
            System.out.println("\n\nGetting student with id"+studentId);

            Student myStudeent = session.get(Student.class,studentId);

            System.out.println("Get complet"+myStudeent);

            System.out.println("\n\nUpdating student..");
            myStudeent.setFirstName("Scooby");

            //commit transaction
            session.getTransaction().commit();

            //new code
            session = factory.getCurrentSession();
            session.beginTransaction();


            System.out.println("\n\nUpdate emails for all students");
            session.createQuery("update Student set email='foo@gmail.com'")
                                    .executeUpdate();
            session.getTransaction().commit();


            System.out.println("Done");

        }
        finally {
            factory.close();
        }
    }
}
