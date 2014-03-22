package edu.khai.applicationtracker.service;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import edu.khai.applicationtracker.model.Application;
import edu.khai.applicationtracker.dao.ApplicationDAO;
import edu.khai.applicationtracker.service.impl.ApplicationServiceImpl;

/*Mockito imports*/
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

public class ApplicationManagerUnitTest {
	final static Logger logger = Logger.getLogger(ApplicationManagerUnitTest.class);

	private Application application;
	private ApplicationDAO mockApplicationDAO;
	private ApplicationServiceImpl applicationManagerImpl;
/*
	@Before
	public void setUp() throws Exception {
		//Делаем экземпляр UserManager'a
		applicationManagerImpl = new ApplicationServiceImpl();
		//Делаем mock объект для ApplicationDAO
		mockApplicationDAO = mock(ApplicationDAO.class);
		//Устанавливаем зависимости, которые раньше делал Spring
		applicationManagerImpl.setApplicationDAO(mockApplicationDAO);

		//Creating some random application for the test
		application = new Application();
		application.setGivenName("John");
		application.setFamilyName("Layman");
		application.setMiddleName("Doe");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				application.setBirthDate(sdf.parse("12-10-1960"));
				application.setCreationDate(sdf.parse("02-01-2014"));
				application.setLastModificationDate(sdf.parse("13-01-2014"));
	}

	@After
	public void tearDown() throws Exception {
		applicationManagerImpl = null;
		mockApplicationDAO = null;
		application = null;
	}

	@Test
	public void testSaveUserTDD() throws Exception {


		//1. stub задаём иммитацию поведения для ApplicationDAO. saveApplication() это void метод,
		//поэтому и doNothing(), т.е. ничего не делать,
		//если будут исключения, тогда и их ещё обработать можно будет.
			doNothing().when(mockApplicationDAO).saveApplication(application);
		//2. run ApplicationManagerImpl выполняет еденицу работы, которую в которой должен произойти вызов
		//ApplicationDAO.saveApplication();
			applicationManagerImpl.saveApplication(application);

		//делаем для application id, который в реальности сделал бы для него Hibernate
			application.setApplicationId(Long.valueOf(47));
		//3. assert  проверяем, произошёл ли вызов ApplicationDAO.saveApplication() в ApplicationManagerImpl
		//на самом деле это наверное избыточное действие, так как mock уже отработал в 1.stub
			verify(mockApplicationDAO, times(1)).saveApplication(application);
		logger.info(application);
	}

	@Test
	public void testSaveUserBDD() throws Exception {
		//Делаем сущность Application, с которой нам работать
		application = new Application();

		//given
		willDoNothing().given(mockApplicationDAO).saveApplication(application);
		//when
		applicationManagerImpl.saveApplication(application);
		//then
		verify(mockApplicationDAO, times(1)).saveApplication(application);
		logger.info(application);
	}

	@Test
	public void testGetUserBDD() throws Exception {
		//Делаем сущность Application, с которой нам работать
		application = new Application();

		//given
		willReturn(application).given(mockApplicationDAO).getApplication(Long.valueOf(37));
		//when
		applicationManagerImpl.getApplication(Long.valueOf(37));
		//then
		verify(mockApplicationDAO, times(1)).getApplication(Long.valueOf(37));
		logger.info(application);
	}
	*/
}