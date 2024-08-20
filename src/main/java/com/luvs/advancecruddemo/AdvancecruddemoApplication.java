package com.luvs.advancecruddemo;

import com.luvs.advancecruddemo.dao.AppDAO;
import com.luvs.advancecruddemo.entity.Course;
import com.luvs.advancecruddemo.entity.Instructor;
import com.luvs.advancecruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AdvancecruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancecruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
//			createinstructor(appDAO);
//			findInstructor(appDAO);
//			deleteinstructorById(appDAO);
//			System.out.println("It is working !");
//			findInstructorDetail(appDAO);
//			deleteInstructorDetail(appDAO);
//			createInstructorWithCourses(appDAO);
//			findInstructorWithCourses(appDAO);

//			findCoursesforInstructor(appDAO);

//			findInstructorWithJoinFetch(appDAO);
//			updateInstructor(appDAO);

//			updateCourse(appDAO);

//			deleteInstructorDetail(appDAO);
			deleteCourse(appDAO);
		};



	}

	private void deleteCourse(AppDAO appDAO) {
		int theId=10;
		System.out.println("Deleting course id: "+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {

		int theId=10;
		System.out.println("FInding course ID : "+theId);
		Course tempCourse=appDAO.findCourseById(theId);

		// update the course
		System.out.println("Updating course id: "+theId);
		tempCourse.setTitle("Enjoying Simple Things");
		appDAO.update(tempCourse);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId=1;

		// find the instructor
		System.out.println("Finding instructor id: "+theId);

		Instructor tempInstructor=appDAO.findInstructorById(theId);

		// update the instructor
		System.out.println("Updating the instructorid: "+theId);
		tempInstructor.setLastName("Tesster");
		appDAO.update(tempInstructor);
		System.out.println("Instructor Updated ");
	}

	private void findInstructorWithJoinFetch(AppDAO appDAO) {

		int theId=1;

		// find the instructor
		System.out.println("Finding instructor id:"+theId);
		Instructor tempInstructor=appDAO.findInstructorByJoinFetch(theId);

		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associated courses: "+tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesforInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Find instructor id: "+ theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor : "+tempInstructor);

		// find the courses for instructor
		System.out.println("Finding courses for instructor id: "+theId);
		List<Course> courses =appDAO.findCoursesByInstructorId(theId);
		tempInstructor.setCourses(courses);
		System.out.println("the associated courses: "+tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId=1;
		System.out.println("Find instructor id: "+ theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor : "+tempInstructor);
		System.out.println("the associated courses: "+tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create the instructor
		Instructor tempInstructor = new Instructor("Susan","public","susan.public@gmail.com");

		// create the instructor detail
		InstructorDetail tempinstructorDetail = new InstructorDetail("www.google.com/susan","Video Games");

		// associate the objects
		tempInstructor.setInstructorDetail(tempinstructorDetail);

		// create somecourses
		Course tempCourse1=new Course("Air Guitar - THe Ultimate Guide");
		Course tempCourse2=new Course("The Pinball Masterclass");
		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		System.out.println("Saving Instructor"+tempInstructor);
		System.out.println("The Course"+tempInstructor.getCourses());
		appDAO.save(tempInstructor);

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId=1;
		System.out.println("DEleting Instructor of Id:"+theId);

		appDAO.deleteInstructorDetailById(theId);


	}

	private void findInstructorDetail(AppDAO appDAO) {
		// get the instructor detail object
		int theId=2;
		InstructorDetail tempInstructorDetail=appDAO.findInstructorDetailById(theId);
		// print the instructor detail
		System.out.println(tempInstructorDetail);
		// print the associated instructor
		System.out.println(tempInstructorDetail);

	}

	private void deleteinstructorById(AppDAO appDAO) {
		int theId=1;
		System.out.println("Deleteing the Id "+theId);
		appDAO.deleteInstructorById(theId);

	}

	private void findInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor Id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor:"+tempInstructor);
		System.out.println("the associated instructorDetail only: "+tempInstructor.getInstructorDetail());
	}

	private void createinstructor(AppDAO appDAO) {
		// create the instructor
		Instructor tempInstructor = new Instructor("Auro Saswat","Raj","auro.raj@gmail.com");

		// create the instructor detail
		InstructorDetail tempinstructorDetail = new InstructorDetail("www.google.com/auro","Loves to sleep");

		// associate the objects
		tempInstructor.setInstructorDetail(tempinstructorDetail);

		// save the instructor
		// NOTE: this will also save the details object
		// because of CascadeType.ALL
		System.out.println("Saving Instructor "+tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done!");

	}

}
