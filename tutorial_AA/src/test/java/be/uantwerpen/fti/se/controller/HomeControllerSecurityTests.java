package be.uantwerpen.fti.se.controller;

import be.uantwerpen.fti.se.tutorial.TutorialApplication;
import be.uantwerpen.fti.se.tutorial.controller.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =
        TutorialApplication.class)
@WebAppConfiguration
public class HomeControllerSecurityTests {
    @Autowired
    private HomeController homeController;

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testOne() {
        homeController.showHomepage();
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser(roles = {"ADMIN"})
    public void testTwo() {
        homeController.showHomepage();
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser(username = "ravan")
    public void testThree() {
        homeController.showHomepage();
    }

    @Test
    @WithUserDetails("u7")
    public void testFour() {
        homeController.showHomepage();
    }
}

