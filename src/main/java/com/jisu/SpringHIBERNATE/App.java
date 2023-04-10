package com.jisu.SpringHIBERNATE;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jisu.SpringHIBERNATE.Dao.StudentDAO;
import com.jisu.SpringHIBERNATE.Model.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

		StudentDAO service = (StudentDAO) context.getBean("service", StudentDAO.class);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = 0;
		do {

			System.out.println("=================================================");
			System.out.println("1 for add Student");
			System.out.println("2 for Delete Student");
			System.out.println("3 for Update Student");
			System.out.println("4 for Seach a  Student");
			System.out.println("5 for View all the  Student");
			System.out.println("6 for Exit");
			System.out.println("=================================================");

			try {

				input = Integer.parseInt(br.readLine());
				switch (input) {

					case 1:
						System.out.println("Enter Student Details:");
						System.out.println("Enter SudentId");
						int id = Integer.parseInt(br.readLine());
						System.out.println("Enter StudentName");
						String name = br.readLine().toString();
						System.out.println("Enter StudentCity");
						String city = br.readLine().toString();

						Student s = new Student(id, name, city);

						service.insert(s);

						System.out.println(name + " added Successfully");
						break;

					case 2:
						System.out.println("Enter Studentid for delete student details:");
						id = Integer.parseInt(br.readLine());
						name = service.getStudent(id).getName();
						service.delete(id);
						System.out.println(name + " Deteled Successfully");

						break;

					case 3:
						System.out.println("Enter Student Id for update");
						id = Integer.parseInt(br.readLine());
						s = service.getStudent(id);
						System.out.println("Enter StudentName");
						name = br.readLine().toString();
						System.out.println("Enter StudentCity");
						city = br.readLine().toString();
						s.setName(name);
						s.setCity(city);
						service.Update(s);

						System.out.println("Details Updated Successfully");
						break;

					case 4:
						System.out.println("Enter StudentId");
						id = Integer.parseInt(br.readLine());
						s = service.getStudent(id);
						System.out.println(s);
						break;

					case 5:
						List<Student> list = new ArrayList<Student>();
						list = service.getAll();

						for (Student stu : list) {
							System.out.println(stu);
						}
						break;

					case 6:
						break;

				}

			} catch (Exception e) {
				System.out.println("Invaild Input");
				System.out.println(e.getMessage());
			}
		} while (input != 6);

	}
}
