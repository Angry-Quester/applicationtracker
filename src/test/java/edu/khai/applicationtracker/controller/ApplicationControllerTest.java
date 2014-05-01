package edu.khai.applicationtracker.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;





import testsetup.TestSecuritySetup;
import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.service.ApplicationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

//import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-context/service-context.xml",
                        "classpath:spring-context/controller-context.xml"})
@ActiveProfiles({"test"})
@WebAppConfiguration
public class ApplicationControllerTest {
    final static Logger logger = Logger.getLogger(ApplicationControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ApplicationService mockApplicationService;

    @Before
    public void setUp() throws Exception {
        reset(mockApplicationService);
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testApplicationsPage() throws Exception {
        /*Fake data to check call results from getApplicationsByAppUserId*/
        Application app1 = new Application();
            app1.setApplicationId(1L);
        Application app2 = new Application();
            app2.setApplicationId(2L);

        /* Place fake securityContext into static SecurityContextHolder*/
        SecurityContextHolder.setContext(TestSecuritySetup.buildMockSecurityContext());

        when(mockApplicationService.getApplicationsByAppUserId(anyLong())).thenReturn(Arrays.asList(app1, app2));

        mockMvc.perform(get("/applications"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("applications", hasSize(2)));

        verify(mockApplicationService, times(1)).getApplicationsByAppUserId(anyLong());
        verifyNoMoreInteractions(mockApplicationService);
    }

    @Test
    public void testApplicationsAddPage() throws Exception {
        /*Fake data to check call results from getApplicationsByAppUserId*/

        mockMvc.perform(get("/applications/new"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("applicationTypes", notNullValue()));
    }

    @Test
    public void testApplicationPage() throws Exception {
        /*Fake data to check call results from getApplicationsByAppUserId*/
        Application app1 = new Application();
            app1.setApplicationId(1L);

        when(mockApplicationService.getApplication(anyLong())).thenReturn(app1);

        mockMvc.perform(get("/applications/{applicationId}", 1L))
            .andExpect(status().isOk())
            .andExpect(model().attribute("application", hasProperty("applicationId", is(1L))));

        verify(mockApplicationService, times(1)).getApplication(anyLong());
        verifyNoMoreInteractions(mockApplicationService);
    }

    @Test
    public void testApplicationsAddPost() throws Exception {
        /*Fake data to check call results from getApplicationsByAppUserId*/
        Application app1 = new Application();
            app1.setGivenName("Smarty");
            app1.setMiddleName("Middle");
            app1.setFamilyName("Guy");
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                app1.setBirthDate(sdf.parse("11.12.1980"));
                app1.setCreationDate(sdf.parse("17.04.2014"));
                app1.setLastModificationDate(sdf.parse("18.04.2014"));

        when(mockApplicationService.addApplication((Application)anyObject())).thenReturn(app1);

        mockMvc.perform(post("/applications")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("givenName", "Smarty")
                        .param("middleName", "Middle")
                        .param("familyName", "Guy")
                        .param("birthDate", "11.12.1980")
                        .param("creationDate", "17.04.2014")
                        .param("lastModificationDate", "18.04.2014"))
                        .andDo(print())
            .andExpect(status().isMovedTemporarily())
            .andExpect(model().attribute("application", notNullValue()))
            ;

        verify(mockApplicationService, times(1)).addApplication(app1);
        verifyNoMoreInteractions(mockApplicationService);
    }

}