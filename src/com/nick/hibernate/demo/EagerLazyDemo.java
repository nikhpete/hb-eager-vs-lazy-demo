package com.nick.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nick.hibernate.demo.entity.Course;
import com.nick.hibernate.demo.entity.Instructor;
import com.nick.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) throws ParseException {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			Instructor ins = session.get(Instructor.class, 1);
			
			System.out.println(ins);
			
			System.out.println(ins.getInstructorDetial());
			
			System.out.println(ins.getCourses());

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
