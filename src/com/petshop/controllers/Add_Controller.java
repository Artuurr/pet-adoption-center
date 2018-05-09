package com.petshop.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.petshop.DAO.DAO;
import com.petshop.DAO.register.DAO_Register;
import com.petshop.models.Client;
import com.petshop.models.Dog;
import com.petshop.models.Race;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Add_Controller implements Initializable 
{
	private AbstractApplicationContext  daoConfig;
	
	private DAO_Register daoReg;
	
	private DAO<Dog> dogDao;
	
	private DAO<Client> clientDao;
	
	private DAO<Race> raceDao;
	
    @FXML
    private TextField txt_dog_name;

    @FXML
    private ComboBox<String> cmb_race;

    @FXML
    private Button btn_confirm_dog;

    @FXML
    private TextField txt_first_name;

    @FXML
    private Button btn_confirm_client;

    @FXML
    private TextField txt_last_name;
    
    private ObservableList<String> raceList;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		initializeConfig();
		
		raceList = FXCollections.observableArrayList();
		
		for(Race race : raceDao.getList()) 
		{
			raceList.add(race.getName());
		}
		
		cmb_race.setItems(raceList);
	}
	
	private void initializeConfig() 
	{
		daoConfig = new FileSystemXmlApplicationContext("/src/com/petshop/config/spring-dao.xml");
		daoReg = daoConfig.getBean(DAO_Register.class);
		dogDao = daoReg.getDogDao();
		clientDao = daoReg.getClientDao();
		raceDao = daoReg.getRaceDao();
		
		daoConfig.registerShutdownHook();
	}
	public void confirmDog() 
	{
		String name = txt_dog_name.getText().trim();
		
		Race race = raceDao.find(cmb_race.getSelectionModel().getSelectedItem()).get(0);
		
		int id_race = race.getId();
		
		Dog dog = new Dog();
		dog.setName(name);
		dog.setRace_id(id_race);
		
		dogDao.add(dog);
		
		txt_dog_name.clear();
	}
	
	public void confirmClient() 
	{
		String first_name = txt_first_name.getText().trim();
		String last_name = txt_last_name.getText().trim();
		
		Client client = new Client();
		client.setFirst_name(first_name);
		client.setLast_name(last_name);
		
		clientDao.add(client);
		
		txt_first_name.clear();
		txt_last_name.clear();
	}
}

















