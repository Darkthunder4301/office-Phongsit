package nvc.it.phongsit.office;

import java.util.Arrays;


import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.Logger;
import nvc.it.phongsit.office.model.Department;
import nvc.it.phongsit.office.model.Employee;
import nvc.it.phongsit.office.model.Project;
import nvc.it.phongsit.office.repository.DepartmentRepository;
import nvc.it.phongsit.office.repository.EmployeeRepository;
import nvc.it.phongsit.office.repository.ProjectRepository;

@SpringBootApplication
public class OfficeApplication implements CommandLineRunner {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(
			OfficeApplication.class);
	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	private final ProjectRepository projectRepository;

	public OfficeApplication(EmployeeRepository empRepository, DepartmentRepository deptRepository, ProjectRepository projectRepository) {
		this.employeeRepository = empRepository;
		this.departmentRepository = deptRepository;
		this.projectRepository = projectRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(OfficeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Department department1 = new Department("Production");
		Department department2 = new Department("Personnel");

		Project project1 = new Project("Line balancing");
		Project project2 = new Project("Labor productivity");

		departmentRepository.saveAll(Arrays.asList(department1, department2));
		projectRepository.saveAll(Arrays.asList(project1, project2));

		employeeRepository.save(new Employee("Phongsit Nakjan", 15500, department2, project2));
		employeeRepository.save(new Employee("Sompong Mejai", 11000, department1, project1));
		employeeRepository.save(new Employee("Surasak Piamkum", 14000, department2, project2));

		
		for (Employee emp : employeeRepository.findAll()) {
			logger.info("Name: {}, Salary: {}",
			emp.getName(), emp.getSalary());
		} 

		for (Department depart : departmentRepository.findAll()) {
			logger.info("Department: {}",
			depart.getName());
		}

		for (Project project : projectRepository.findAll()) {
			logger.info("Department: {}",
			project.getName());
		}

		logger.info("================ findByName =================");
		for (Employee emp : employeeRepository.findByName("Surasak Piamkum")) {
			logger.info("Employee's name: {}, Salary: {}",
			emp.getName(), emp.getSalary());
		}

		logger.info("================ findBySalaryGreaterThan =================");
		for (Employee emp : employeeRepository.findBySalaryGreaterThan(14000)) {
			logger.info("Employee's name: {}, Salary: {}",
			emp.getName(), emp.getSalary());
		}
		
		logger.info("================ findByNameContaining =================");
		for (Project project : projectRepository.findByNameContaining("The")) {
			logger.info("Project's name: {}",
			project.getName());
		}
	}
}