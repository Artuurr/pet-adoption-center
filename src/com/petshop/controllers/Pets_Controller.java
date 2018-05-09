package com.petshop.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.petshop.DAO.DAO;
import com.petshop.DAO.register.DAO_Register;
import com.petshop.models.Dog;
import com.petshop.models.Race;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Pets_Controller implements Initializable 
{
	private ObservableList<Dog> dogList;

	private ApplicationContext daoConfig;
	
	private DAO_Register daoReg;
	
	private DAO<Dog> dogDao;
	
	private DAO<Race> raceDao;
	
    @FXML
    private ListView<Dog> list_dogs;
    
    @FXML
    private TextField txt_search;
    
    @FXML
    private Label lbl_name;

    @FXML
    private Label lbl_race;

    @FXML
    private Label lbl_owner;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		initializeConfig();
		
		dogList = FXCollections.observableArrayList(dogDao.getList());
		
		list_dogs.setItems(dogList);
		
		list_dogs.setCellFactory(param -> new ListCell<Dog>() 
		{
			@Override
			protected void updateItem(Dog item, boolean empty) 
			{
				super.updateItem(item, empty);
				
				if(empty || item == null || item.getName() == null) 
				{
					setText(null);
				}
				else 
				{
					setText(item.getName());
				}
			}			
		});
		
		list_dogs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dog>() 
		{
			@Override
			public void changed(ObservableValue<? extends Dog> observable, Dog oldValue, Dog newValue) 
			{
				showInfo(newValue);
			}
		});
	}
	
	private void initializeConfig() 
	{
		daoConfig = new FileSystemXmlApplicationContext("/src/com/petshop/config/spring-dao.xml");
		daoReg = daoConfig.getBean(DAO_Register.class);
		dogDao = daoReg.getDogDao();
		raceDao = daoReg.getRaceDao();
	}
	
	public void search() 
	{
		dogList.clear();
		
		String name = txt_search.getText().trim();
		
		for(Dog dog : dogDao.find(name)) 
		{
			dogList.add(dog);
		}
		
		if(name.trim().isEmpty()) 
		{
			for(Dog dog : dogDao.getList()) 
			{
				dogList.add(dog);
			}
		}
	}
	
	public void showInfo(Dog dog) 
	{
		String dogName = dog.getName();
		String adopted = "No";
		String raceName = raceDao.find(dog.getRace_id()).get(0).getName();
		
		if(dog.getOwner_id() > 0) 
		{
			adopted = "Yes";
		}
		
		lbl_name.setText(dogName);
		lbl_owner.setText(adopted);
		lbl_race.setText(raceName);
	}
}






























