package modernhealth.library.repositories;

import modernhealth.library.resources.Activity;
import modernhealth.library.resources.Section;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, String> {
    List<Activity> findAll();

    Activity findByName(String name);

    List<Activity> findBySectionName(String sectionName);

    Activity findByNameAndSectionName(String name, String sectionName);

    void delete (Activity activity);
}
