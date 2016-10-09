package com.simongo.todolist.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by simon on 01/10/16.
 */
@Entity
@Table(name="building")
public class Building implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="address")
	private String address;

	@Column(name="description")
	private String description;

	@Column(name="number_of_tasks")
	private int numberOfTasks;

	@Column(name="number_of_completed")
	private int numberOfCompleted;

	public Building() {

	}

	public Building(String name, String address, String description, int numberOfTasks, int numberOfCompleted) {
		this.name = name;
		this.address = address;
		this.description = description;
		this.numberOfTasks = numberOfTasks;
		this.numberOfCompleted = numberOfCompleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberOfTasks() {
		return numberOfTasks;
	}

	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}

	public int getNumberOfCompleted() {
		return numberOfCompleted;
	}

	public void setNumberOfCompleted(int numberOfCompleted) {
		this.numberOfCompleted = numberOfCompleted;
	}

	@Override
	public String toString() {
		return "Building{" +
				"id=" + id +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", description='" + description + '\'' +
				", numberOfTasks=" + numberOfTasks +
				", numberOfCompleted=" + numberOfCompleted +
				'}';
	}
}
