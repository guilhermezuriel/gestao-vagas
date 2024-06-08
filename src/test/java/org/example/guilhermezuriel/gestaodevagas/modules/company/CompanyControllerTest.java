package org.example.guilhermezuriel.gestaodevagas.modules.company;

import java.util.UUID;

import org.example.guilhermezuriel.gestaodevagas.entities.company.CompanyEntity;
import org.example.guilhermezuriel.gestaodevagas.enums.LevelEnum;
import org.example.guilhermezuriel.gestaodevagas.modules.utils.TestUtils;
import org.example.guilhermezuriel.gestaodevagas.repositories.CompanyRepository;
import org.example.guilhermezuriel.gestaodevagas.service.job.dto.CreateJobDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.example.guilhermezuriel.gestaodevagas.modules.utils.TestUtils.objectToJson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CompanyControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }


    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {
        var company = CompanyEntity.builder()
                .description("test-company")
                .email("test@company.com")
                .name("Test Company")
                .website("test.com")
                .username("testcompany")
                .password("12345678901").build();
        var savedCompany = companyRepository.saveAndFlush(company);

        var createJob = CreateJobDto.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION TEST")
                .level(LevelEnum.JUNIOR)
                .build();
        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(createJob))
                        .header("Authorization", TestUtils.generateToken(company.getId(), "JAVAGAS_@123#")))
                        .andExpect(MockMvcResultMatchers.status().isOk());
        System.out.println(result);
    }


}
