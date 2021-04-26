package modernhealth.library.repositories;

import modernhealth.library.resources.Program;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgramRepository extends CrudRepository<Program, String> {
    List<Program> findAll();

    Program findByName(String name);

    void delete (Program program);

}
