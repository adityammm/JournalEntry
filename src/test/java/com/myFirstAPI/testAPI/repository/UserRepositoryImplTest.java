package com.myFirstAPI.testAPI.repository;

import com.myFirstAPI.testAPI.entity.User;
import org.bson.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
public class UserRepositoryImplTest {

    @MockBean
    UserRepositoryImpl userRepositoryImpl;

    @Test
    public void getAllUsersForSATest(){

        Mockito.when(userRepositoryImpl.getAllUsersForSA()).thenReturn(List.of());
        Assertions.assertNotNull(userRepositoryImpl.getAllUsersForSA());
    }
}
