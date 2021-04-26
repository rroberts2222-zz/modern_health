package modernhealth.library.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import modernhealth.library.repositories.ProgramRepository;
import modernhealth.library.repositories.SectionRepository;
import modernhealth.library.resources.Program;
import modernhealth.library.resources.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProgramController {
    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @GetMapping("/programs")
    public List<Program> getPrograms() {
        return programRepository.findAll();
    }

    @GetMapping("/programs/{name}")
    public Program getProgram(@PathVariable String name) {
        return programRepository.findByName(name);
    }

    @PostMapping(path="/programs")
    public @ResponseBody String createProgram (@RequestBody Program program) {
        programRepository.save(program);
        return "Saved";
    }

    @PutMapping(path="/programs/{name}")
    public @ResponseBody String updateProgram (@PathVariable String name, @RequestParam(required = false) String description, @RequestParam(required = false) String sectionName) {
        Program program = programRepository.findByName(name);
        if (description != null) {
            program.setDescription(description);
            programRepository.save(program);
        }
        if (sectionName != null) {
            Section section = sectionRepository.findByName(sectionName);
            program.addSection(section);
            programRepository.save(program);
        }
        return "Saved";
    }

    @DeleteMapping(path="/programs/{name}")
    public @ResponseBody String deleteProgram (@PathVariable String name) {
        Program program = programRepository.findByName(name);
        programRepository.delete(program);
        return "Deleted";
    }

}
