/*
 *  Done by: Aditi Patel & Aakaash Prakash Hemdev
 */




package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ReadDisplaySongList  implements Initializable
{
	ArrayList<SongListObj> song_list = new ArrayList<SongListObj>();
	String[]parts;
	String selectedListView;
	String song;
	String artist;
	
	int defaultIndex = 0;
	
	
	@FXML
	ListView <String> song_Artist_List;
	
	ObservableList<String> items;

	
	@FXML
	Label selectedAlbum;
	
	@FXML
	Label selectedYear;
	

	@FXML
	TextField songName;
	@FXML
	TextField songArtist;
	@FXML
	TextField songAlbum;
	@FXML
	TextField songYear;
	
	
	
	
		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		setListview();
		showselectedItem();
				
	}
	
	
//****************************************************************** Delete the value *************************************************************************************888
	
	
	
	@FXML
    public void delete(ActionEvent event) throws IOException
	{
		int index = song_Artist_List.getSelectionModel().getSelectedIndex();

		
		String selectedItem = song_Artist_List.getSelectionModel().getSelectedItem();

    	String  [] newString = selectedListView.split("\\nArtist: ");

		
		if(selectedItem != null)
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + selectedItem + "?", ButtonType.YES, ButtonType.NO);
	        alert.showAndWait();
	        
	        if (alert.getResult() == ButtonType.YES)
	        {	    		
	        	for(int i = 0; i < song_list.size(); i++)
	    		{
	    			if((song_list.get(i).getSongName()).equalsIgnoreCase(newString[0]) && (song_list.get(i).getArtistName()).equalsIgnoreCase(newString[1]))
	    			{
	    				
	    				song_list.remove(i);
	    	        	song_Artist_List.getItems().remove(index);

	    				
	    			}
	    		}
	        		        	
	        	
	        	if(song_list.get(song_list.size()-1).getSongName() != newString[0]&&song_list.get(song_list.size()-1).getSongName() != newString[1])
	        	{
		        		song_Artist_List.getSelectionModel().selectNext();

	        	}
	        	
	        }
	        writeInFile(song_list);
		}
		
    }

//****************************************************************** ADD the value *************************************************************************************888
	
	
	
	
	@FXML
	public void add(ActionEvent event) throws IOException
	{
		String song, artist, year, album;
		String listviewItem;
		
		song = songName.getText();
		artist= songArtist.getText();
		year = songYear.getText();
		album = songAlbum.getText();
		
		
		
		if (song.isEmpty()||artist.isEmpty() || song.isEmpty() && artist.isEmpty() )
		{
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Invalid input");
	        alert.setContentText("In order to add, please fill out both song and artist fields");

	        alert.showAndWait();
	    } 
		else
		{
			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Add?", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();
			
	        if (alert.getResult() == ButtonType.YES)
	        {
	        	for(int i = 0; i <song_list.size(); i++)
	    		{
	    			if(song.equalsIgnoreCase(song_list.get(i).getSongName()) && artist.equalsIgnoreCase(song_list.get(i).getArtistName()))
	    			{
	    				Alert error = new Alert(Alert.AlertType.ERROR);
	    				error.setTitle("Error");
	    				error.setHeaderText("Invalid input");
	    				error.setContentText("This song is already in the library");

	    				error.showAndWait();
	    			}
	    		}
	    		
	        	if(!year.isEmpty() && !album.isEmpty())
				{
					listviewItem = song+ "\nArtist: " + artist;
					song_list.add(new SongListObj(song, artist, album, year));
					song_Artist_List.getItems().add(listviewItem);
					song_Artist_List.getSelectionModel().select(listviewItem);

					
				}
				else if(year.isEmpty() )
				{
					song_list.add(new SongListObj(song, artist, album, "N/A"));
					listviewItem = song+ "\nArtist: " + artist;
					song_Artist_List.getItems().add(listviewItem);
				}
				else if(album.isEmpty() )
				{
					song_list.add(new SongListObj(song, artist, "N/A", year));
					listviewItem = song+ "\nArtist: " + artist;
					song_Artist_List.getItems().add(listviewItem);
				}
				writeInFile(song_list);

				
			}	
	     }

			
			
		
		
	}
	
	
	
	
//****************************************************************** UPDATE EDITED VALUES *************************************************************************************888
	
	
	
	
	@FXML
	public void edit(ActionEvent event) throws IOException
	{
		int count = 0, index = 0;
		String song, artist, year, album;
		String listviewItem;
		
		song = songName.getText();
		artist= songArtist.getText();
		year = songYear.getText();
		album = songAlbum.getText();
		
		String selectedItem = song_Artist_List.getSelectionModel().getSelectedItem();
		index = song_Artist_List.getSelectionModel().getSelectedIndex();
		
    	String  [] newString = selectedItem.split("\\nArtist: ");
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Update/Edit?", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		
        if (alert.getResult() == ButtonType.YES)
        {
		
	    	if (song.isEmpty()||artist.isEmpty() || song.isEmpty() && artist.isEmpty() )
	    	{
		        Alert error1 = new Alert(Alert.AlertType.ERROR);
		        error1.setTitle("Error");
		        error1.setHeaderText("Invalid input");
		        error1.setContentText("In order to UPDATE/EDIT , please fill out both song and artist fields!");
	
		        error1.showAndWait();
		    }
	    	
				for(int i = 0; i <song_list.size(); i++)
				{
					if(song.equalsIgnoreCase(song_list.get(i).getSongName()) && artist.equalsIgnoreCase(song_list.get(i).getArtistName()))
					{
						count++;
						if(count>=1)
						{
							Alert error = new Alert(Alert.AlertType.ERROR);
							error.setTitle("Error");
							error.setHeaderText("Invalid input");
							error.setContentText("This song is already in the library");
	
							error.showAndWait();
						}
					}
				}
				
				for(int i = 0; i < song_list.size(); i++)
				{
					
					if(newString[0].equalsIgnoreCase(song_list.get(i).getSongName()) && newString[1].equalsIgnoreCase(song_list.get(i).getArtistName()))
					{
						SongListObj obj = song_list.get(i);
						if(!song.isEmpty()&& !artist.isEmpty())
						{
							obj.setSongName(song);
							obj.setArtistName(artist);
							listviewItem = song+"\nArtist: "+artist;
							song_Artist_List.getItems().set(index, listviewItem);
							
						}
						else if(!song.isEmpty())
						{
							obj.setSongName(song);
							listviewItem = song+"\nArtist: "+obj.getArtistName();
							song_Artist_List.getItems().set(index, listviewItem);
						}
						else if(!artist.isEmpty())
						{
							obj.setArtistName(artist);
							listviewItem = obj.getSongName()+"\nArtist: "+artist;
							song_Artist_List.getItems().set(index, listviewItem);
						}
	
						if(!album.isEmpty() && !year.isEmpty() )
						{
							obj.setSongAlbum(album);
							obj.setSongYear(year);
							selectedAlbum.setText(obj.getSongAlbum());
							selectedYear.setText(obj.getSongYear());
						}
						else if(!album.isEmpty())
						{
							obj.setSongAlbum(album);
							selectedAlbum.setText(obj.getSongAlbum());
						}
						else if(!artist.isEmpty())
						{
							obj.setSongYear(year);
							selectedYear.setText(obj.getSongYear());
						}
						
						
						song_list.set(i, obj);
					}
				}
				writeInFile(song_list);
        }
	}
	
//****************************************************************** WRITE IN THE FILE *************************************************************************************888

	public void writeInFile(ArrayList<SongListObj> list)throws IOException
	{
		File file = new File("SongList.csv");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		
		for(int i = 0; i < list.size(); i++)
		{
			pw.println(list.get(i).getSongName()+ "        "+ list.get(i).getArtistName()+ "        "+ list.get(i).getSongAlbum()+ "        "+ list.get(i).getSongYear());
		}
		
		pw.close();
		
	}
	
//****************************************************************** Read The File*************************************************************************************888
	
	
	
	
	public void setListview()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReadDisplaySongList.fxml"));
		ReadDisplaySongList controller = new ReadDisplaySongList();
		loader.setController(controller);
		String songArtist;
		
		String file = "SongList.csv";
		String line = "";
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));			
			while((line = reader.readLine()) != null)
			{
				String [] raw = line.split("        ");
				song_list.add(new SongListObj(raw[0], raw[1], raw[2], raw[3]));
			}
			
			sortArraylist(song_list);
			
			
			reader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		for(SongListObj obj: song_list)
		{
			songArtist = obj.getSongName() + "\nArtist: " + obj.getArtistName();
			song_Artist_List.getItems().add(songArtist);
		}
		
			
	}	
	
	
	
	
//****************************************************************** Sorting Array *************************************************************************************888
	
	
	
	
	public void sortArraylist(ArrayList<SongListObj> song_list)
	{
		Collections.sort(song_list, (p1, p2) -> p1.getSongName().compareTo(p2.getSongName()));
	}	
	
	
	
//****************************************************************** POPULATE DETAIL LIST VIEW  *************************************************************************************888
	
	
	
	
	public void populateDetailListView(String selectedListView , ArrayList<SongListObj> song_list)
	{
		String  [] newString = selectedListView.split("\\nArtist: ");
		
		for(int i = 0; i < song_list.size(); i++)
		{
			if((song_list.get(i).getSongName()).equalsIgnoreCase(newString[0]) && (song_list.get(i).getArtistName()).equalsIgnoreCase(newString[1]))
			{
				
				selectedAlbum.setText(song_list.get(i).getSongAlbum());
				selectedYear.setText( song_list.get(i).getSongYear());

				songName.setText(song_list.get(i).getSongName());
				songArtist.setText(song_list.get(i).getArtistName());
				songYear.setText(song_list.get(i).getSongYear());
				songAlbum.setText(song_list.get(i).getSongAlbum());
				
			}
		
		}

		
	}
	
	
	
	
//****************************************************************** Display the Detail List View *************************************************************************************888
	
	
	
	
	public void showselectedItem()
	{
		song_Artist_List.getSelectionModel().select(defaultIndex);
		String secondItem = song_Artist_List.getItems().get(defaultIndex);
		populateDetailListView(secondItem, song_list);
		

		song_Artist_List.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            
			if (newValue != null) {

	               selectedListView = newValue;
	              
	       		   System.out.println(selectedListView);
	       		   populateDetailListView(selectedListView, song_list);

	            }
	        });
		
		song_Artist_List.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
	           
			if (newValue != null)
            {
                defaultIndex = newValue.intValue();
            }
        });
		
		
	}
	
	
	
	

}
