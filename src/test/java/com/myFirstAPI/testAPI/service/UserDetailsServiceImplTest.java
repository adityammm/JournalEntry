package com.myFirstAPI.testAPI.service;

import com.myFirstAPI.testAPI.entity.User;
import com.myFirstAPI.testAPI.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserByUsernameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("muskan").password("adi").role(new ArrayList<>()).build());
        UserDetails userDetails = userDetailsService.loadUserByUsername("muskan");

        Assertions.assertNotNull(userDetails);
    }
}
