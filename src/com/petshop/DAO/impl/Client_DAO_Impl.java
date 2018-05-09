package com.petshop.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petshop.DAO.DAO;
import com.petshop.con_factory.Connectivity;
import com.petshop.models.Client;

public class Client_DAO_Impl implements DAO<Client>
{
	private List<Client> ownerList;
	
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
			pstm.close();
			
			conn.close();
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}

	@Override
	public List<Client> getList() 
	{
		String sql = "SELECT id, first_name, last_name FROM clients";
		
		ownerList = new ArrayList<>();
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) 
			{
				Client owner = new Client();
				owner.setId(rs.getInt("id"));
				owner.setFirst_name(rs.getString("first_name"));
				owner.setLast_name(rs.getString("last_name"));
				
				ownerList.add(owner);
			}
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			
			return null;
		}
		
		return ownerList;
	}

	@Override
	public void add(Client owner) 
	{
		String sql = "INSERT INTO clients(first_name,last_name) values(?,?)";
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, owner.getFirst_name());
			pstm.setString(2, owner.getLast_name());
			
			pstm.execute();
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}

	@Override
	public void delete(Client owner)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Client owner) 
	{
		
	}
	
	@Override
	public List<Client> find(int id) 
	{
		String sql = "SELECT id, first_name, last_name FROM clients WHERE id=?";
		
		ownerList = new ArrayList<>();
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) 
			{
				Client owner = new Client();
				owner.setId(rs.getInt("id"));
				owner.setFirst_name(rs.getString("first_name"));
				owner.setLast_name(rs.getString("last_name"));
				
				ownerList.add(owner);
			}
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			
			return null;
		}
		return ownerList;
	}
	
	@Override
	public List<Client> find(String name) 
	{	
		String sql = "SELECT id, first_name, last_name FROM clients WHERE first_name=?";
		
		ownerList = new ArrayList<>();
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, name);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) 
			{
				Client owner = new Client();
				owner.setId(rs.getInt("id"));
				owner.setFirst_name(rs.getString("first_name"));
				owner.setLast_name(rs.getString("last_name"));
				
				ownerList.add(owner);
			}

		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			
			return null;
		}
		
		return ownerList;
	}
	
	@Override
	public boolean exists(Client owner) 
	{	
		String sql = "SELECT id,first_name,last_name FROM clients WHERE first_name=? && last_name=?";
		
		try 
		{
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, owner.getFirst_name());
			pstm.setString(2, owner.getLast_name());
			
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
	}
}











