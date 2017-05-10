package org.adani.hangman.model;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Player")
public class Player {

	@Id
	private final String id;
	private final String name;
	private final LocalDateTime creationTs;

	public Player(String name) {
		this.name = name;
		creationTs = LocalDateTime.now();
		id = name + "_" + LocalDateTime.now().toString();
	}
	

	public String getId() {
		return null;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
