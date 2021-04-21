package org.tdd.tictactoe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "application.runner.enabled=false" })
class TicTacToeApplicationTests {

	@Test
	void contextLoads() {
	}

}
