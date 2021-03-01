package com.wade.springcucumber;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages ="com.test.springbdd")
class TestConfiguration {
// defines a webdriver Bean
}