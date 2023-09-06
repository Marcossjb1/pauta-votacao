package com.vote.schedule.service.impl;

import com.vote.schedule.model.User;
import com.vote.schedule.repository.UserRepository;
import com.vote.schedule.validation.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserValidator userValidator;

    @Test
    public void shouldCreateUser() {
        var user = new User();
        user.setId(1L);
        user.setName("Marcos");
    }


}
