package com.nla.domain;

import java.io.Serializable;

/**
 * Pet record in the CCC.
 *
 * @author Frederic Massart
 */
public class PetCCC implements Serializable {

	public enum PetType { BIRD, CAT, DOG, HAMSTER, LIZARD, SNAKE};

	private long uuid;

	private String name;

	private PetType type;

	private long ownerUuid;

	public long getUuid() {
		return uuid;
	}

	public void setUuid(long uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PetType getType() {
		return type;
	}

	public void setType(PetType type) {
		this.type = type;
	}

	public long getOwnerUuid() {
		return ownerUuid;
	}

	public void setOwnerUuid(long ownerUuid) {
		this.ownerUuid = ownerUuid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PetCCC)) return false;

		PetCCC petCCC = (PetCCC) o;

		if (ownerUuid != petCCC.ownerUuid) return false;
		if (uuid != petCCC.uuid) return false;
		if (!name.equals(petCCC.name)) return false;
		if (type != petCCC.type) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (uuid ^ (uuid >>> 32));
		result = 31 * result + name.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + (int) (ownerUuid ^ (ownerUuid >>> 32));
		return result;
	}
}
