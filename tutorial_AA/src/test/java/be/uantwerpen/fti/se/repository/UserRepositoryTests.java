package be.uantwerpen.fti.se.repository;

import be.uantwerpen.fti.se.tutorial.TutorialApplication;
import be.uantwerpen.fti.se.tutorial.model.User;
import be.uantwerpen.fti.se.tutorial.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TutorialApplication.class)
@WebAppConfiguration
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testSaveProduct() {
        //setup product
        User user = new User();
        user.setUserName("TestUserName");
        //save product, verify has ID value after save
        assertNull(user.getId()); //null before save
        userRepository.save(user);
        assertNotNull(user.getId()); //not null after save
        //fetch from DB
        User fetchedUser = userRepository.findById(user.getId()).orElse(null);
        //should not be null
        assertNotNull(fetchedUser);
        //should equal
        assertEquals(user.getId(), fetchedUser.getId());
        assertEquals(user.getUserName(), fetchedUser.getUserName());
        //update description and save
        fetchedUser.setUserName("NewTestUserName");
        userRepository.save(fetchedUser);
        //get from DB, should be updated
        User fetchedUpdatedUser =
                userRepository.findById(fetchedUser.getId()).orElse(null);
        assertEquals(fetchedUser.getUserName(), fetchedUpdatedUser.getUserName());
        //verify count of products in DB
        long userCount = userRepository.count();
        assertEquals(userCount, 4);
        //get all products, list should only have one more then initial value
        Iterable<User> users = userRepository.findAll();
        int count = 0;
        for (User p : users) {
            count++;
        }
        assertEquals(count, 4);// we starten reeds met 3 gebruikers in de database
    }
}

