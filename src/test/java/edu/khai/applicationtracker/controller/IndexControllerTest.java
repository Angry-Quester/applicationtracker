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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context/service-context.xml",
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

	@Before
	public void setUp() throws Exception {

		mockMvc = webAppContextSetup(webApplicationContext).build();
		//mockMvc = standaloneSetup(new IndexController()).build();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetContextIndex() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"));
	}

}