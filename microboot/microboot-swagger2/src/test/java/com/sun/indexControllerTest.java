package com.sun;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SwaggerApplication.class)//指定启动类
@WebAppConfiguration
public class indexControllerTest {
	@Resource
	private com.sun.controller.indexController indexController;
	@Test
	public void test() {
		TestCase.assertEquals("index", indexController.index());
	}

}
