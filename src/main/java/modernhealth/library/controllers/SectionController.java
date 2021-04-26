package modernhealth.library.controllers;

import modernhealth.library.repositories.ProgramRepository;
import modernhealth.library.repositories.SectionRepository;
import modernhealth.library.resources.Program;
import modernhealth.library.resources.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SectionController {
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ProgramRepository programRepository;

    @GetMapping("/sections")
    public List<Section> getSections() {
        return sectionRepository.findAll();
    }

    @GetMapping("/sections/{name}")
    public Section getSection(@PathVariable String name) {
        return sectionRepository.findByName(name);
    }

    @GetMapping("/programs/{programName}/sections")
    public List<Section> getSectionsByProgramName(@PathVariable String programName) {
        return sectionRepository.findByProgramName(programName);
    }

    @GetMapping("/programs/{programName}/sections/{sectionName}")
    public Section getSection(@PathVariable String programName, @PathVariable String sectionName) {
        return sectionRepository.findByNameAndProgramName(sectionName, programName);
    }

    @PostMapping(path="/programs/{programName}/sections")
    public @ResponseBody String createSection (@PathVariable String programName, @RequestBody Section section) {
        Program program = programRepository.findByName(programName);
        //TODO: throw exception if can't find program
        section.setProgram(program);
        sectionRepository.save(section);
        return "Saved";
    }

    @PutMapping(path="/programs/{programName}/sections/{sectionName}")
    public @ResponseBody String updateSection (@PathVariable String programName, @PathVariable String sectionName,
                                               @RequestParam(required = false) String description,
                                               @RequestParam(required = false) String imagePath,
                                               @RequestParam(required = false) Integer orderIndex,
                                               @RequestParam(required = false) String newProgramName) {
        Section section = sectionRepository.findByNameAndProgramName(sectionName, programName);
        if (description != null) {
            section.setDescription(description);
        }
        if (imagePath != null) {
            section.setImagePath(imagePath);
        }
        if (orderIndex != null) {
            section.setOrderIndex(orderIndex);
        }
        if (newProgramName != null) {
            Program program = programRepository.findByName(newProgramName);
            section.setProgram(program);
        }
        sectionRepository.save(section);
        return "Saved";
    }


    @DeleteMapping(path="/programs/{programName}sections/{sectionName}")
    public @ResponseBody String deleteSection (@PathVariable String programName, @PathVariable String sectionName) {
        Section section = sectionRepository.findByNameAndProgramName(sectionName, programName);
        sectionRepository.delete(section);
        return "Deleted";
    }
}
