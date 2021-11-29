package cundi.edu.co.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class LineaIiiApplicationTests {

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Test
	void contextLoads() {
	}

	@Test
	void vaerificarClave() {
		System.out.println("Resultado:--------------------  " + bcrypt.encode("123456"));
		assertTrue(true);
	}
}
