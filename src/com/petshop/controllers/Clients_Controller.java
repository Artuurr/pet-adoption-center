package com.petshop.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.petshop.DAO.DAO;
import com.petshop.DAO.register.DAO_Register;
import com.petshop.models.Client;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Clients_Controller implements Initializable
{
	private ObservableList<Client> clientList;

	private ApplicationContext daoConfig;
	
	private DAO_Register daoReg;
	
	private DAO<Dog> dogDao;
	
	private DAO<Client> clientDao;
	
	private DAO<Race> raceDao;
	
    @FXML
    private TextField txt_search;

    @FXML
    private Label lbl_name;

    @FXML
    private ListView<Client> list_clients;

    @FXML
    private TableView<Dog> table_pets;

    @FXML
    private TableColumn<Dog, String> column_name;

    @FXML
    private TableColumn<Dog, Integer> column_race;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		initializeConfig();
		
		clientList = FXCollections.observableArrayList(clientDao.getList());
		
		list_clients.setItems(clientList);
		list_clients.setCellFactory(param -> new ListCell<Client>() 
		{
			@Override
			protected void updateItem(Client item, boolean empty) 
			{
				super.updateItem(item, empty);
				
				if(empty || item == null || item.getFirst_name() == null) 
				{
					setText(null);
				}
				else 
				{
					setText(item.getFirst_name() + " " + item.getLast_name());
				}
			}
		});
		
		list_clients.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>() 
		{
			@Override
			public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) 
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
		clientDao = daoReg.getClientDao();
		raceDao = daoReg.getRaceDao();
	}
	
	public void search() 
	{
		clientList.clear();
		
		String name = txt_search.getText().trim();
		
		for(Client client : clientDao.find(name)) 
		{
			clientList.add(client);
		}
		
		if(name.trim().isEmpty()) 
		{
			for(Client client : clientDao.getList()) 
			{
				clientList.add(client);
			}
		}
	}
	
	private void showInfo(Client client) 
	{
		lbl_name.setText(client.getFirst_name() + " " + client.getLast_name());
		
		ObservableList<Dog> tempDoglist = FXCollections.observableArrayList();
		
		tempDoglist.clear();
		
		for(Dog dog : dogDao.getList()) 
		{
			if(dog.getOwner_id() == client.getId()) 
			{
				tempDoglist.add(dog);
			}
		}
		
		table_pets.setItems(tempDoglist);
		
		column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		column_race.setCellValueFactory(new PropertyValueFactory<>("race_id"));
		column_race.setCellFactory(param -> new TableCell<Dog,Integer>() 
				{
					@Override
					protected void updateItem(Integer item, boolean empty) 
					{
						super.updateItem(item, empty);
						
						if(empty || item == null) 
						{
							setText(null);
						}
						else 
						{
							setText(raceDao.find(item).get(0).getName());
						}
					}
				});
	}
}
































