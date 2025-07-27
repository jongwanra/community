package com.community.medium;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.community.medium.config.TestDatabaseConfig;
import com.community.medium.config.TestRedisConfig;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
@Import({TestDatabaseConfig.class, TestRedisConfig.class})
public @interface MediumTest {
}
