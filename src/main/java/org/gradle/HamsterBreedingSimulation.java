package org.gradle;

public class HamsterBreedingSimulation {
	
	public final int MULTIPLIER = 4;
	public final float HAMSTER_WEIGHT = .12f;  //In kilograms.
	public final double EARTH_WEIGHT = 5.972 * Math.pow(10, 24);  //In kilograms.
	private double population = 2;
	private int yearsPassed = 0;
	
	public double getPopulation() {
		return population;
	}
	
	public int getYearsPassed() {
		return yearsPassed;
	}

	public void forwardTime(int years) {
		if(years < 0) throw new IllegalArgumentException("Argument cannot be negative.");
		yearsPassed += years;
		population *= Math.pow(MULTIPLIER, years);
	}
	
	public void forwardUntilHamstersOutweighTheEarth() {
		while(population * HAMSTER_WEIGHT < EARTH_WEIGHT)
			forwardTime(1);
	}
	
	public static void main(String[] args) {
		HamsterBreedingSimulation hs = new HamsterBreedingSimulation();
		hs.forwardUntilHamstersOutweighTheEarth();
		System.out.println("It takes " + hs.getYearsPassed() + " years for the hamsters to outweigh the earth.");
	}
}
