package com.jisu.SpringHIBERNATE.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.jisu.SpringHIBERNATE.Model.Student;

@Component("service")
public class StudentDAO {

	public HibernateTemplate getHtemplate() {
		return Htemplate;
	}

	public void setHtemplate(HibernateTemplate htemplate) {
		Htemplate = htemplate;
	}

	private HibernateTemplate Htemplate;

	// Insert data
	@Transactional()
	public int insert(Student student) {

		return (Integer) this.Htemplate.save(student);

	}

	// Fetching a Student from primary key
	public Student getStudent(int id) {
		return (Student) this.Htemplate.get(Student.class, id);
	}

	// Getting all Student details
	public List<Student> getAll() {
		return (List<Student>) this.Htemplate.loadAll(Student.class);
	}

	// Delete
	@Transactional
	public void delete(int id) {

		this.Htemplate.delete(this.Htemplate.get(Student.class, id));

	}

	// Update data
	@Transactional
	public void Update(Student student) {
		this.Htemplate.update(student);
	}

}
