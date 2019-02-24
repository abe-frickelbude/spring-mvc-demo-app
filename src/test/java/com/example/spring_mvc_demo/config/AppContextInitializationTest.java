package com.example.spring_mvc_demo.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * A very basic SpringBoot-enabled test. Here we just test the overall integrity of our application setup -
 * if DI, configuration values etc. are configured correctly, this test should succeed by fully starting the
 * application context.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppContextInitializationTest {

    @Test
    public void contextInitOk() {
    }
}
