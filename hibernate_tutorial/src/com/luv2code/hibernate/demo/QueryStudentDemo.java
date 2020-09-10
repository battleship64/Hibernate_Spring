package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        //create SessionFactory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();

        //create session
        Session session=factory.getCurrentSession();

        try{
            //use session to query Java object


            //begin transaction
            session.beginTransaction();

            //query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            //display students
            displayStudents(theStudents);

            //query students last name ='Doe'
            theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
            System.out.println("\n\nStudent with last name Doe");
            displayStudents(theStudents);

            //query students lastName = 'doe' or firstName='Daffy'
            System.out.println("\n\n Student with lastName = 'doe' or firstName='Daffy'");
            theStudents = session.createQuery("from Student s where" +" s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
            displayStudents(theStudents);

            //query student where eamil LIKE '%luv2code.com'
            System.out.println("\n\n student where eamil LIKE '%luv2code.com'");
            theStudents = session.createQuery("from Student s where"+" s.email LIKE '%luv2code.com'").getResultList();
            displayStudents(theStudents);

            session.getTransaction().commit();
            System.out.println("Done");
        }
        finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for(Student tempStudent:theStudents){
            System.out.println(tempStudent);
        }
    }
}
