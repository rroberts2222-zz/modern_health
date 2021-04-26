package modernhealth.library.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import modernhealth.library.repositories.ProgramRepository;
import modernhealth.library.resources.Program;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ProgramControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProgramRepository repository;

    @Test
    public void givenNoProgram_whenCreateProgram_thenProgramCreated() throws Exception {
        Program leadershipDevProgram = new Program();
        leadershipDevProgram.setName("LeadershipDevelopmentProgram");
        mvc.perform(MockMvcRequestBuilders
                .post("/programs/LeadershipDevelopmentProgram")
                .content(new ObjectMapper().writeValueAsString(leadershipDevProgram))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
    }

}
