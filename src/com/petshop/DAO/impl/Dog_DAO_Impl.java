package com.petshop.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petshop.DAO.DAO;
import com.petshop.con_factory.Connectivity;
import com.petshop.models.Dog;

public class Dog_DAO_Impl implements DAO<Dog>
{
	private List<Dog> dogList;
	
	private Connection conn = null;
	
	private PreparedStatement pstm = null;
	
	@Override
	public void init() 
	{
		new Connectivity();
		conn = Connectivity.Connect();
	}
	
	@Override
	public void destroy() 
	{
		try 
		{	
			conn.close();
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public List<Dog> getList() 
	{	
		String sql = "SELECT id,name,race_id,owner_id FROM dogs";
		
		dogList = new ArrayList<>();
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) 
			{
				Dog dog = new Dog();
				dog.setId(rs.getInt("id"));
				dog.setName(rs.getString("name"));
				dog.setRace_id(rs.getInt("race_id"));
				dog.setOwner_id(rs.getInt("owner_id"));
				
				dogList.add(dog);
			}
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			
			return null;
		}
		finally 
		{
			try 
			{
				pstm.close();
			}
			catch(Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		
		return dogList;
	}

	@Override
	public void add(Dog dog) 
	{
		String sql = "INSERT INTO dogs(name,race_id) values(?,?)";
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, dog.getName());
			pstm.setInt(2, dog.getRace_id());
			
			pstm.execute();
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		finally 
		{
			try 
			{
				pstm.close();
			}
			catch(Exception ex) 
			{
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Dog dog) 
	{
		String sql = "DELETE FROM dogs WHERE id=?";
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, dog.getId());
			
			pstm.execute();
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		finally 
		{
			try 
			{
				pstm.close();
			}
			catch(Exception ex) 
			{
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	public void update(Dog dog) 
	{

	}

	@Override
	public List<Dog> find(int id) 
	{
		String sql = "SELECT id, name, race_id, owner_id FROM dogs WHERE id=?";
		
		dogList = new ArrayList<>();
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) 
			{
				Dog dog = new Dog();
				dog.setId(rs.getInt("id"));
				dog.setName(rs.getString("name"));
				dog.setOwner_id(rs.getInt("owner_id"));
				dog.setRace_id(rs.getInt("race_id"));
				
				dogList.add(dog);
			}
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			
			return null;
		}
		finally 
		{
			try 
			{
				pstm.close();
			}
			catch(Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		
		return dogList;
	}
	
	@Override
	public List<Dog> find(String name) 
	{
		String sql = "SELECT id, name, race_id, owner_id FROM dogs WHERE name=?";
		
		dogList = new ArrayList<>();
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, name);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) 
			{
				Dog dog = new Dog();
				dog.setId(rs.getInt("id"));
				dog.setName(rs.getString("name"));
				dog.setOwner_id(rs.getInt("owner_id"));
				dog.setRace_id(rs.getInt("race_id"));
				
				dogList.add(dog);
			}
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			
			return null;
		}
		finally 
		{
			try 
			{
				pstm.close();
			}
			catch(Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		
		return dogList;
	}
	
	@Override
	public boolean exists(Dog dog) 
	{		
		String sql = "SELECT id, name, race_id, owner_id FROM dogs WHERE name=? && race_id=? && owner_id=?";
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, dog.getName());
			pstm.setInt(2, dog.getRace_id());
			pstm.setInt(3, dog.getOwner_id());
			
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()) 
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			
			return false;
		}
		finally 
		{
			try 
			{
				pstm.close();
			}
			catch(Exception ex) 
			{
				ex.printStackTrace();
			}
		}
	}

}



































