package edu.khai.applicationtracker.controller;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.service.AppUserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-context/service-context.xml",
						"classpath:spring-context/controller-context.xml"})
@ActiveProfiles({"test"})
@WebAppConfiguration
public class IndexControllerTest {
	final static Logger logger = Logger.getLogger(IndexControllerTest.class);

	private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

	@Autowired
	IndexController ic;

	@Autowired
	private AppUserService appUserService;

	@Before
	public void setUp() throws Exception {

		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetContextIndex() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"))
			.andExpect(model().attribute("welcomeMessage", is(instanceOf(String.class))));

/*=====================================================================*/
		AppUser appUser = new AppUser();
				appUser.setAppUserId(1L);
				appUser.setUsername("smartPants");

		when(appUserService.getAppUser(1L)).thenReturn(appUser);

		mockMvc.perform(get("/appUsers/{appUserId}", 1L))
			.andExpect(status().isOk())
			.andExpect(model().attribute("appUser", instanceOf(AppUser.class)))
			.andExpect(model().attribute("appUser", hasProperty("appUserId", equalTo(1L))))
			.andExpect(model().attribute("appUser", hasProperty("username",equalTo("smartPants"))));

		verify(appUserService, times(1)).getAppUser(1L);
	}

}