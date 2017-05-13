package org.adani.hangman.model;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Player {

	private final String id;
	private final String name;
	private final LocalDateTime creationTs;

	public Player(String name) {
		this.name = name;
		creationTs = LocalDateTime.now();
		id = name + "_" + LocalDateTime.now().toString();
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
		result = result * 31 + name.hashCode();
		result = result * 31 + creationTs.hashCode();
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
		
		return id.equals(other.id) &&
				name.equals(name) &&
				creationTs.equals(other.creationTs);
	}
}
