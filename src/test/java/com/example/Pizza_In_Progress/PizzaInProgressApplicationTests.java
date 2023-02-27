package com.example.Pizza_In_Progress;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// по умилчанию спринг стартует на порту 8080 но во ремня тестирования этот порт может уже быть занят
// WebEnvironment.RANDOM_PORT говорит спрингу чтобы для тестирования приложение стартовало на каком нибудь радномном посту
@RunWith(SpringRunner.class)
class PizzaInProgressApplicationTests {

	@Value(value = "${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void validPizzaIsCreated() throws Exception{
		HttpHeaders headers = new HttpHeaders();
	}

	@Test
	void contextLoads() {
	}

}
