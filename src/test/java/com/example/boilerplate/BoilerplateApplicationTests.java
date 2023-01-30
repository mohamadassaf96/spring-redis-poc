package com.example.boilerplate;

import com.example.boilerplate.meta.annotations.TestAnnotation;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@TestAnnotation
class BoilerplateApplicationTests {

}
