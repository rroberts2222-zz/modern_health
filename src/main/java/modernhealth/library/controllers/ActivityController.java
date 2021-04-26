package modernhealth.library.controllers;

import modernhealth.library.repositories.ActivityRepository;
import modernhealth.library.repositories.SectionRepository;
import modernhealth.library.resources.Activity;
import modernhealth.library.resources.Program;
import modernhealth.library.resources.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityController {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @GetMapping("/activities")
    public List<Activity> getActivities() {
        return activityRepository.findAll();
    }

    @GetMapping("/activities/{name}")
    public Activity getActivity(@PathVariable String name) {
        return activityRepository.findByName(name);
    }

    @GetMapping("/programs/{programName}/sections/{sectionName}/activities")
    public List<Activity> getActivitiesBySectionName(@PathVariable String sectionName) {
        return activityRepository.findBySectionName(sectionName);
    }

    @GetMapping("/programs/{programName}/sections/{sectionName}/activities/{activityName}")
    public Activity getActivity(@PathVariable String sectionName, @PathVariable String activityName) {
        return activityRepository.findByNameAndSectionName(activityName, sectionName);
    }

    @PostMapping(path="/programs/{programName}/sections/{sectionName}/activities")
    public @ResponseBody String createActivity (@PathVariable String sectionName, @RequestBody Activity activity) {
        Section section = sectionRepository.findByName(sectionName);
        //TODO: throw exception if can't find section
        activity.setSection(section);
        activityRepository.save(activity);
        return "Saved";
    }

    @PutMapping(path="/programs/{programName}/sections/{sectionName}/activities/{activityName}")
    public @ResponseBody String updateActivity (@PathVariable String sectionName, @PathVariable String activityName,
                                               @RequestParam(required = false) String htmlContent,
                                               @RequestParam(required = false) String question,
                                               @RequestParam(required = false) String newSectionName) {
        Activity activity = activityRepository.findByNameAndSectionName(activityName, sectionName);
        if (htmlContent != null) {
            activity.setHtmlContent(htmlContent);
        }
        if (question != null) {
            activity.setQuestion(question);
        }
        if (newSectionName != null) {
            Section section = sectionRepository.findByName(newSectionName);
            activity.setSection(section);
        }
        activityRepository.save(activity);
        return "Saved";
    }


    @DeleteMapping(path="/programs/{programName}sections/{sectionName}/activities/{activityName}")
    public @ResponseBody String deleteActivity (@PathVariable String sectionName, @PathVariable String activityName) {
        Activity activity = activityRepository.findByNameAndSectionName(activityName, sectionName);
        activityRepository.delete(activity);
        return "Deleted";
    }
}
