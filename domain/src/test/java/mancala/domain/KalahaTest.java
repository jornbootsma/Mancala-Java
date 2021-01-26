package mancala.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KalahaTest {
	private Kalaha kalaha;
	
	@BeforeEach
	public void setUp() {
		kalaha = new Kalaha();
	}
	
	@Test 
    public void kalahaStartsWith0Stones() {
		int numbOfStones = kalaha.getNumberOfStones();
        assertEquals(0, numbOfStones);
    }
	
	@Test
	public void getOpposite() {
		Kalaha opposite = kalaha.getOpposite();
		assertEquals(kalaha, opposite);
	}
	
	@Test
    public void addStones() {
    	int initialAmount = kalaha.getNumberOfStones();
    	kalaha.addStones(3);
    	int newAmount = kalaha.getNumberOfStones();
    	assertEquals(initialAmount, newAmount - 3);
    }
	
}
