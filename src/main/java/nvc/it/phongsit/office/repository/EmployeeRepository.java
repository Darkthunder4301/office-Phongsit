package nvc.it.phongsit.office.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import nvc.it.phongsit.office.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findByName(String name);
    List<Employee> findBySalaryGreaterThan(int salary);
}