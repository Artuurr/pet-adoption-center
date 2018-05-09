package com.petshop.DAO;

import java.util.List;

public interface DAO<T> 
{
	public void init();
	
	public void destroy();
	
	public List<T> getList();
	
	public void add(T t);
	
	public void delete(T t);

	public void update(T t);
	
	public List<T> find(int id);
	
	public List<T> find(String name);
	
	public boolean exists(T t);
}
