package lol;

import static org.junit.Assert.assertEquals;

import org.gradle.HamsterBreedingSimulation;
import org.junit.Test;

public class HamsterBreedingSimulationTest {
	
	private HamsterBreedingSimulation hs = new HamsterBreedingSimulation();
	private final int INITIAL_POPULATION = (int)hs.getPopulation();
	
	@Test
	public void testYearsPassed() {
		hs.forwardTime(1);
		assertEquals(1, hs.getYearsPassed());
		hs.forwardTime(10);
		assertEquals(11, hs.getYearsPassed());
	}
	
	@Test
	public void testPopulationGrowth() {
		hs.forwardTime(1);
		assertEquals(INITIAL_POPULATION * hs.MULTIPLIER, hs.getPopulation(), .001);
		hs.forwardTime(4);
		assertEquals(INITIAL_POPULATION * (int)Math.pow(hs.MULTIPLIER, 5), hs.getPopulation(), .001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeForwarding() {
		hs.forwardTime(-1);
	}
	
	@Test
	public void testZeroForwarding() {
		hs.forwardTime(0);
		assertEquals(INITIAL_POPULATION, hs.getPopulation(), .001);
	}
}
