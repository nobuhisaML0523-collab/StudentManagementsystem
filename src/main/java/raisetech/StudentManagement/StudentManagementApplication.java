package raisetech.StudentManagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

	@GetMapping("/studentsearch")
	public ResponseEntity<String> getStudents(@RequestParam int id) {
		Student student = repository.searchById(id);

		if (student == null) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("指定されたIDの学生は存在しません");
		}

		return ResponseEntity.ok(
				student.getName() + " " + student.getAge() + "歳"
		);
	}

	@PostMapping("/student")
	public ResponseEntity<String> registerStudent(@RequestParam int id, @RequestParam String name, @RequestParam int age) {

		repository.registerStudent(id, name,age);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("登録しました");
	}

	@PatchMapping("/studentname")
	public ResponseEntity<String> setStudentName(@RequestParam int id, @RequestParam String name){
		Student student = repository.searchById(id);

		if (student == null) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("指定されたIDの学生は存在しません");
		}

		repository.updateName(id, name);

		return ResponseEntity.ok("名前を更新しました");
	}

	@PatchMapping("/studentage")
	public ResponseEntity<String> setStudentName(@RequestParam int id, @RequestParam int age){
		Student student = repository.searchById(id);

		if (student == null) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("指定されたIDの学生は存在しません");
		}

		repository.updateAge(id, age);

		return ResponseEntity.ok("年齢を更新しました");
	}

	@DeleteMapping("/student")
	public ResponseEntity<String> deleteStudent(@RequestParam int id){

		Student student = repository.searchById(id);

		if (student == null) {
			return ResponseEntity.notFound().build();
		}

		repository.deleteStudent(id);

		return ResponseEntity.noContent().build();
	}

}