package com.petshop.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class Main_Controller implements Initializable
{
	
    @FXML
    private AnchorPane child_pane;
    
    private AnchorPane petsPane;
    
    private AnchorPane clientsPane;
    
    private AnchorPane addPane;
    
    @FXML
    private JFXButton btn_pets;

    @FXML
    private JFXButton btn_clients;
    
    @FXML
    private JFXButton btn_add;

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{	
		try 
		{
			petsPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/petshop/views/Pets.fxml"));
			
			petsPane.prefWidthProperty().bind(child_pane.widthProperty());
			petsPane.prefHeightProperty().bind(child_pane.heightProperty());
			
			child_pane.getChildren().setAll(petsPane);
			
			clientsPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/petshop/views/Clients.fxml"));
			
			addPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/petshop/views/Add.fxml"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	public void manageStages(ActionEvent e) throws IOException 
	{
		if(e.getSource().equals(btn_pets)) 
		{	
			petsPane.prefWidthProperty().bind(child_pane.widthProperty());
			petsPane.prefHeightProperty().bind(child_pane.heightProperty());
			
			child_pane.getChildren().setAll(petsPane);
		}
		
		if(e.getSource().equals(btn_clients)) 
		{
			clientsPane.prefWidthProperty().bind(child_pane.widthProperty());
			clientsPane.prefHeightProperty().bind(child_pane.heightProperty());
			
			child_pane.getChildren().setAll(clientsPane);
		}
		
		if(e.getSource().equals(btn_add))
		{
			addPane.prefWidthProperty().bind(child_pane.widthProperty());
			addPane.prefHeightProperty().bind(child_pane.heightProperty());
			
			child_pane.getChildren().setAll(addPane);
		}
	}
}





















