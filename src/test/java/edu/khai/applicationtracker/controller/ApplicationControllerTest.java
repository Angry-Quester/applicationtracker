package edu.khai.applicationtracker.controller;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserPrincipal;
import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.service.AppUserService;
import edu.khai.applicationtracker.service.ApplicationService;
import edu.khai.applicationtracker.service.impl.UserDetailsServiceImpl;
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
	private ApplicationService applicationService;

	@Autowired
	private UserDetailsServiceImpl userDetailService;

	@Before
	public void setUp() throws Exception {

		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testApplicationsPage() throws Exception {
		/*Fake data to check call results from getApplicationsByAppUserId*/
		Application app1 = new Application();
			app1.setApplicationId(1l);
		Application app2 = new Application();
			app2.setApplicationId(2l);


		/* This code helps to test method where i explicitly use SecurityContextHolder
		 * to get a principal from my Spring Security context
		 */
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);

		/* This is fake AppUserPrincipal to use inside the test*/
		AppUserPrincipal aup = new AppUserPrincipal(1l,
													"name@mail.net",
													"pass",
													true,
													true,
													true,
													true,
													Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
		/* Mock calls to the security infrastructure */
		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(securityContext.getAuthentication().getPrincipal()).thenReturn(aup);

		/* Place fake securityContext into static SecurityContextHolder*/
		SecurityContextHolder.setContext(securityContext);

		when(applicationService.getApplicationsByAppUserId(anyLong())).thenReturn(Arrays.asList(app1, app2));

		mockMvc.perform(get("/applications"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("applications", hasSize(2)));

		verify(applicationService, times(1)).getApplicationsByAppUserId(anyLong());
		verifyNoMoreInteractions(applicationService);
	}

}