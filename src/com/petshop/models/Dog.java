package com.petshop.models;

public class Dog
{

	private int id;
	private String name;
	private int race_id;
	private int owner_id;
	
	public void setId(int id) 
	{
		this.id = id;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public void setRace_id(int race_id) 
	{
		this.race_id = race_id;
	}

	public void setOwner_id(int owner_id)
	{
		this.owner_id = owner_id;
	}

	public int getId() 
	{
		return id;
	}

	public String getName() 
	{
		return name;
	}

	public int getRace_id() 
	{
		return race_id;
	}

	public int getOwner_id() 
	{
		return owner_id;
	}

}
