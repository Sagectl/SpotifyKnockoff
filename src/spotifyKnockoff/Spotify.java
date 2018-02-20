package spotifyKnockoff;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Spotify {
	
	public DefaultTableModel searchSongs(String searchTerm){
		
		String sql = "SELECT song_id, title, length, release_date, record_date FROM song " ;
		if(!searchTerm.equalsIgnoreCase("") ){
			sql += "WHERE title LIKE '%" + searchTerm + "%' ;";
			
		}else{
			
		}
		
		
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Song ID", " Title", "Length", "Release Date", "Record Date"};
			return db.getDataTable(sql, columnNames);
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(frame, "Something WRONG");
			ErrorLogger.log(e.getMessage());
		}
		
		return null;
		
	}
	
	public DefaultTableModel searchAlbums(String searchTerm){
		String sql = "SELECT album_id, title, release_date, cover_image_path, recording_company_name, number_of_tracks, PMRC_rating, length FROM album ";
		if(!searchTerm.equalsIgnoreCase("")){
			sql+="WHERE title LIKE '%" + searchTerm + "%' ;" ;
			
		}
		
		
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Album ID", "Title", "Release Date", "Cover Image Path", "Recording Compnay", "Number Of Tracks", "PMRC Rating", "Length"};
			return db.getDataTable(sql, columnNames);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
		
	}
	
	public DefaultTableModel searchArtists(String searchTerm){
		String sql = "SELECT artist_id, first_name, last_name, band_name FROM artist ";
		if(!searchTerm.equalsIgnoreCase("")){
			sql+="WHERE first_name LIKE '%" + searchTerm + "%' OR last_name LIKE '%" + searchTerm + "%' OR band_name LIKE '%" + searchTerm + "%' ;" ;
			
		}
		
		
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Artist ID", "First Name", "Last Name", "Band"};
			return db.getDataTable(sql, columnNames);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
		
	}
}
