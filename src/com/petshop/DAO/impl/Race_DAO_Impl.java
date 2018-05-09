package com.petshop.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petshop.DAO.DAO;
import com.petshop.con_factory.Connectivity;
import com.petshop.models.Race;

public class Race_DAO_Impl implements DAO<Race>
{
	private List<Race> raceList;
	
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
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public List<Race> getList() 
	{	
		String sql = "SELECT id, name FROM races";
		
		raceList = new ArrayList<>();
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) 
			{
				Race race = new Race();
				race.setId(rs.getInt("id"));
				race.setName(rs.getString("name"));
				
				raceList.add(race);
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
		
		return raceList;
	}

	@Override
	public void add(Race race) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Race race) 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(Race race) 
	{
		
	}

	@Override
	public List<Race> find(int id) 
	{
		String sql = "SELECT id, name FROM races WHERE id=?";
		
		raceList = new ArrayList<>();
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) 
			{
				Race race = new Race();
				race.setId(rs.getInt("id"));
				race.setName(rs.getString("name"));
				
				raceList.add(race);
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
		
		return raceList;
	}

	@Override
	public List<Race> find(String name) 
	{
		String sql = "SELECT id, name FROM races WHERE name=?";
		
		raceList = new ArrayList<>();
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, name);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) 
			{
				Race race = new Race();
				race.setId(rs.getInt("id"));
				race.setName(rs.getString("name"));
				
				raceList.add(race);
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
		
		return raceList;
	}
	
	public boolean exists(Race race) 
	{	
		String sql = "SELECT id,name FROM races WHERE name=?";
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, race.getName());
			
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






























