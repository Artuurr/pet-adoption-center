package com.petshop.DAO.register;

import com.petshop.DAO.DAO;
import com.petshop.models.Client;
import com.petshop.models.Dog;
import com.petshop.models.Race;

public class DAO_Register 
{
	private DAO<Dog> dogDao;
	
	private DAO<Client> clientDao;
	
	private DAO<Race> raceDao;

	public void setDogDao(DAO<Dog> dogDao) 
	{
		this.dogDao = dogDao;
	}

	public void setClientDao(DAO<Client> clientDao) 
	{
		this.clientDao = clientDao;
	}

	public void setRaceDao(DAO<Race> raceDao) 
	{
		this.raceDao = raceDao;
	}

	public DAO<Dog> getDogDao() 
	{
		return dogDao;
	}
	
	public DAO<Client> getClientDao()
	{
		return clientDao;
	}
	
	public DAO<Race> getRaceDao()
	{
		return raceDao;
	}
}
