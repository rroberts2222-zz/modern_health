package modernhealth.library.resources;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import org.springframework.boot.context.properties.bind.Name;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Program {
    @NotNull
    @Id
    private String name;
    private String description;
    @JsonManagedReference
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "program")
    private List<Section> sections;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        if (sections == null) {
            sections = new ArrayList<>();
        }
        sections.add(section);
    }

    public void removeSection(Section section) {
        if (sections != null) {
            sections.remove(section);
        }
    }

    public List<Section> getSections() {
        return this.sections;
    }
}

