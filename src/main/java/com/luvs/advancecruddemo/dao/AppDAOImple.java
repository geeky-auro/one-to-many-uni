package com.luvs.advancecruddemo.dao;

import com.luvs.advancecruddemo.entity.Course;
import com.luvs.advancecruddemo.entity.Instructor;
import com.luvs.advancecruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImple implements AppDAO{

    // define field for entityManager
    private EntityManager entityManager;
    // Inject entity Manager using constructor injection
    @Autowired
    public AppDAOImple(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
    // Add Transactional as we are persisting/saving in the database
    entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        // this will also retrieve the instructor details object
        // Because of default behaviour of @OneToOne fetch type is eager...
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve the Instructor
        Instructor tempInstructor=entityManager.find(Instructor.class,theId);

        // get the course
        List<Course> courses=tempInstructor.getCourses();

        // braek the association of all courses for the instructor
        for(Course tempCourse:courses){
            tempCourse.setInstructor(null);
        }

        //delete the Instructor
        // This will also delete the instructor details object because of CascadeType.ALL
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // retrieve instructor detail
        InstructorDetail tempInstructorDetail=entityManager.find(InstructorDetail.class,theId);
        // remove the associated object reference
        // break the bi-directional link
        System.out.println("Some Data:"+tempInstructorDetail);
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // Create query for Finding Instructor By Id
        TypedQuery<Course> query =entityManager.createQuery(
                "from Course where instructor.id= :data",Course.class
        );
        query.setParameter("data",theId);
        //execute the query
        List<Course> course=query.getResultList();
        return course;
    }

    @Override
    public Instructor findInstructorByJoinFetch(int theId) {
        // create query
        TypedQuery<Instructor> query =entityManager.createQuery(
                "select i from Instructor i "+
                        "JOIN FETCH i.courses "+
                        "JOIN FETCH i.instructorDetail "
                + "where i.id= :data",Instructor.class);
        query.setParameter("data",theId);

        // execute query
        Instructor instructor=query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class,theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse=entityManager.find(Course.class,theId);

        // delete the course
        entityManager.remove(tempCourse);
    }


}
