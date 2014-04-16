package edu.khai.applicationtracker.controller;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.service.ApplicationService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
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
		SecurityContextHolder.setContext(SecuritySetup.buildMockSecurityContext());

		when(mockApplicationService.getApplicationsByAppUserId(anyLong())).thenReturn(Arrays.asList(app1, app2));

		mockMvc.perform(get("/applications"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("applications", hasSize(2)));

		verify(mockApplicationService, times(1)).getApplicationsByAppUserId(anyLong());
		verifyNoMoreInteractions(mockApplicationService);
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

}