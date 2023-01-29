package com.example.boilerplate.meta.annotations;

import com.example.boilerplate.config.MockRepositories;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
@ActiveProfiles("test")
@Import(MockRepositories.class)
@AutoConfigureMockMvc(addFilters = false)
@ComponentScan("com.example.boilerplate.advice.CustomControllerAdvice")
public @interface ControllerTest {
}
