package edu.khai.applicationtracker.util;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import edu.khai.applicationtracker.model.AppUser;
import edu.khai.applicationtracker.model.AppUserPrincipal;

import static org.junit.Assert.*;


public class UserDetailsProviderUnitTest {
    final static Logger logger = Logger.getLogger(UserDetailsProviderUnitTest.class);

    private UserDetailsProvider userDetailsProvider;

    @Before
    public void setUp() throws Exception {
        //create userDetailsServiceImpl
        userDetailsProvider = new UserDetailsProvider();
        //no mocks no dependencies no nothing
    }

    @After
    public void tearDown() throws Exception {
        userDetailsProvider = null;
    }

    @Test
    public void testloadUserByUsername() throws Exception {
        //create fake Appuser
        AppUser appUser = new AppUser();
        appUser.setId(Long.valueOf(1));
        appUser.setAppUserId(Long.valueOf(1));
        appUser.setUsername("somename@mailbox.ru");
        appUser.setPassword("password");
        appUser.setAccountNonExpired(true);
        appUser.setAccountNonLocked(true);
        appUser.setCredentialsNonExpired(true);
        appUser.setEnabled(true);

        //given

        //when
        AppUserPrincipal appUserPrincipal = userDetailsProvider.getUserDetails(appUser);
        //then
        assertNotNull(appUserPrincipal);
        assertEquals("appUserPrincipal data is wrong ",
                        appUser.getUsername(),
                        appUserPrincipal.getUsername());
    }

}