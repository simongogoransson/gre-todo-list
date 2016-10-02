package com.simongo.todolist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by simon on 01/10/16.
 */
@Entity
@Table(name="building")
public class Building {

	@Id
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="address")
	private String address;

	@Column(name="description")
	private String description;

	public Building() {

	}

	public Building(int id, String name, String address, String description) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
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

	@Override
	public String toString() {
		return "Building{" +
				"id=" + id +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
