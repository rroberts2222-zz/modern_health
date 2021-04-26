package modernhealth.library.resources;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Activity {
    @Id
    @NotNull
    private String name;
    private String htmlContent;
    private String question;
    @JsonBackReference
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="section_name")
    private Section section;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setHtmlContent(String content) {
        this.htmlContent = content;
    }

    public String getHtmlContent() {
        return this.htmlContent;
    }

    //todo: question class
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Section getSection() {
        return this.section;
    }

}