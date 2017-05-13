package org.adani.hangman.model;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Player {

	private  String id;
	private String name;
	private LocalDateTime creationTs;

	public Player(){	
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreationTs(LocalDateTime creationTs) {
		this.creationTs = creationTs;
	}

	public Player(String name) {
		id = name + "_" + LocalDateTime.now().toString();
		this.name = name;
		creationTs = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;

	}

	public LocalDateTime getCreationTs() {
		return creationTs;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public int hashCode() {
		int result = 7;
		result = result * 31 + id.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		
		return id.equals(other.id);
	}
}
