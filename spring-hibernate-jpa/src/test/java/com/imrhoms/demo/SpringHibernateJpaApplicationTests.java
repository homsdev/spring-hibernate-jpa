package com.imrhoms.demo;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) // Launch the entire springboot context
@SpringBootTest(classes = SpringHibernateJpaApplication.class)
class SpringHibernateJpaApplicationTests {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	void contextLoads() {
		logger.info("Test is running");
	}

}
