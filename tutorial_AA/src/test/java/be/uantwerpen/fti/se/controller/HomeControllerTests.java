package be.uantwerpen.fti.se.controller;

import be.uantwerpen.fti.se.tutorial.controller.HomeController;
import be.uantwerpen.fti.se.tutorial.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeControllerTests {
    @Mock
    private UserService userService;
    @InjectMocks
    private HomeController homeController;
    private MockMvc mockMvc;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }
    @Test
    public void viewHomepageTest() throws Exception
    { mockMvc.perform(get("/home"))
            .andExpect(view().name("homepage"));
    }
}
