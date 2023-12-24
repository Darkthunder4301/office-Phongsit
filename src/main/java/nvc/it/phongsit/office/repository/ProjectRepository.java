package nvc.it.phongsit.office.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import nvc.it.phongsit.office.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer>{
    List<Project> findByNameContaining(String name);
}