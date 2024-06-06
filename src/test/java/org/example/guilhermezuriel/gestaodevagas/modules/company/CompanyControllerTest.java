package org.example.guilhermezuriel.gestaodevagas.modules.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.aspectj.lang.annotation.Before;
import org.example.guilhermezuriel.gestaodevagas.enums.LevelEnum;
import org.example.guilhermezuriel.gestaodevagas.service.job.dto.CreateJobDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class CompanyControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before("")
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {

        var createJob = CreateJobDto.builder().benefits("BENEFITS_TEST").description("DESCRIPTION TEST").level(LevelEnum.JUNIOR).build();
        var result = mvc.perform(MockMvcRequestBuilders
                        .post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(createJob)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        System.out.println(result);
    }

    private static String objectToJson(Object object) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
