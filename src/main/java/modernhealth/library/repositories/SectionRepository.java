package modernhealth.library.repositories;

import modernhealth.library.resources.Program;
import modernhealth.library.resources.Section;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SectionRepository extends CrudRepository <Section, String> {
    List<Section> findAll();

    Section findByName(String name);

    List<Section> findByProgramName(String programName);

    Section findByNameAndProgramName(String name, String programName);

    void delete (Section section);
}
