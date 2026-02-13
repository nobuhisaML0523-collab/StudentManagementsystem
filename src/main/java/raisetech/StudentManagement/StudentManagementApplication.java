package raisetech.StudentManagement;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	@Autowired
	private StudentRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@GetMapping("/student")
	public String getAllStudents() {
		List<Student> studentList = repository.allStudents();
		StringBuilder result = new StringBuilder();

		for (Student student : studentList) {
			result.append(student.getName())
					.append(" ")
					.append(student.getAge())
					.append("歳")
					.append("\n");
		}

		return result.toString();
	}

	@GetMapping("/studentseach")
	public String getStudents(@RequestParam int id) {
		Student student = repository.searchByName(id);

		return student.getName() + " " + student.getAge() +"歳";
	}

	@PostMapping("/student")
	public void registerStudent(@RequestParam int id, @RequestParam String name, @RequestParam int age) {

		repository.regsiterStudent(id, name,age);
	}

	@PatchMapping("/studentname")
	public void setStudentName(@RequestParam int id, @RequestParam String name){
		repository.updateName(id, name);
	}

	@PatchMapping("/studentage")
	public void setStudentName(@RequestParam int id, @RequestParam int age){
		repository.updateAge(id, age);
	}

	@DeleteMapping("/student")
	public void deleteStudent(@RequestParam int id){
		repository.deleteStudent(id);
	}

}