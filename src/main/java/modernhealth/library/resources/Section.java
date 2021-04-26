package modernhealth.library.resources;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Section {
    @Id
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String imagePath;
    @NotNull
    private int orderIndex;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "program_name")
    private Program program;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
    private List<Activity> activities;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public int getOrderIndex() {
        return this.orderIndex;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Program getProgram() {
        return this.program;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        if (activities == null) {
            activities = new ArrayList<>();
        }
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return this.activities;
    }

}

