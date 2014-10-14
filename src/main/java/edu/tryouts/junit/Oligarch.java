package edu.tryouts.junit;

import java.util.ArrayList;
import java.util.List;

import edu.tryouts.java8.Person;

/**
 * Class Oligarch.
 *
 * @author Erwin Dupont
 * @since 2014-10-14
 */
public class Oligarch {
	private String name;
	private List<String> vehicles = new ArrayList<>();
	private Soul soul= new Soul();

	public Oligarch(String name) {
		this.name = name;
		this.getVehicles().add("yacht");
		this.getSoul().setOwner(new Person().named("The Devil"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<String> vehicles) {
		this.vehicles = vehicles;
	}

	public Soul getSoul() {
		return soul;
	}

	public void setSoul(Soul soul) {
		this.soul = soul;
	}
}
