package spotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Album {
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private double length;
	Map<String, Song>albumSongs = new HashMap<String, Song>();;
	
	public Album(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, double length){
		this.title = title;
		this.releaseDate = releaseDate ;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		this.albumID = UUID.randomUUID().toString();
		
		/*
		String sql = "INSERT INTO `album`(`album_id`,`title`,`release_date`,`cover_image_path`,`recording_company_name`,`number_of_tracks`,`PMRC_rating`,`length`) ";
		       sql +=  "VALUES ('" + this.albumID + "','" + this.title + "','" + this.releaseDate + "','" + this.coverImagePath + "','" +this.recordingCompany + "','" + this.numberOfTracks + "','" + this.pmrcRating +"','" + this.length + "');";
		       
		System.out.println(sql);
		
		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		*/
		

		String sql = "INSERT INTO `album`(`album_id`,`title`,`release_date`,`cover_image_path`,`recording_company_name`,`number_of_tracks`,`PMRC_rating`,`length`) " ;
			   sql +=  "VALUES (?,?,?,?,?,?,?,?) ;" ;
			   		
	    //System.out.println(sql);
		
	    try {
		    DbUtilities db = new DbUtilities();
		    Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.albumID);
			ps.setString(2, this.title);
			ps.setString(3, this.releaseDate);
			ps.setString(4, this.coverImagePath);
			ps.setString(5, this.recordingCompany);
			ps.setInt(6, this.numberOfTracks);
			ps.setString(7, this.pmrcRating);
			ps.setDouble(8, this.length);
			ps.executeUpdate();
			
			System.out.print(ps);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
		

	
	public Album(String albumID){
		String sql = "SELECT * FROM album WHERE album_id = '" + albumID + "';" ;
		System.out.println(sql);
		
		DbUtilities db = new DbUtilities();
		
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.albumID = rs.getString("album_id");
				//System.out.println("Album ID from database: " + this.albumID);
				this.title = rs.getString("title");
				this.releaseDate = rs.getString("release_date");
				this.recordingCompany = rs.getString("recording_company_name");
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.pmrcRating = rs.getString("PMRC_rating");
				this.length = rs.getDouble("length");
				
				System.out.println("Album title from database: " + this.title);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteAlbum(String albumID){
		String sql = "DELETE FROM `album` WHERE album_id = '" + albumID +"';";
		System.out.println(sql);
		
		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
			
	}
	
	public void addSong(Song song){
		this.albumSongs.put(song.getAlbumID(), song);
	
	}
	
	public void deleteSong(String songID){
		this.albumSongs.remove(albumID);
	
	}
	
	public void deleteSong(Song song){
		this.deleteSong(song.getAlbumID());
		
	}

	public String getAlbumID() {
		return this.albumID;
		
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
		
		String sql = "UPDATE `album` SET `title` = '" + this.title + "' where  album_id = '" + this.albumID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public String getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
		
		String sql = "UPDATE `album` SET `release_date` = '" + this.releaseDate + "' where  album_id = '" + this.albumID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public String getCoverImagePath() {
		return this.coverImagePath;
	}

	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
		
		String sql = "UPDATE `album` SET `cover_image_path` = '" + this.coverImagePath + "' where  album_id = '" + this.albumID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public String getRecordingCompany() {
		return this.recordingCompany;
	}

	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
		
		String sql = "UPDATE `album` SET `recording_company_name` = '" + this.recordingCompany + "' where  album_id = '" + this.albumID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public int getNumberOfTracks() {
		return this.numberOfTracks;
	}

	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
		
		String sql = "UPDATE `album` SET `number_of_tracks` = '" + this.numberOfTracks + "' where  album_id = '" + this.albumID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public String getPmrcRating() {
		return this.pmrcRating;
	}

	public void setPmrcRating(String pmrcRating) {
		this.pmrcRating = pmrcRating;
		
		String sql = "UPDATE `album` SET `PMRC_rating` = '" + this.pmrcRating + "' where  album_id = '" + this.albumID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public double getLength() {
		return this.length;
	}

	public void setLength(double length) {
		this.length = length;
		
		String sql = "UPDATE `album` SET `length` = '" + this.length + "' where  album_id = '" + this.albumID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}

	public void setAlbumSongs(Map<String, Song> albumSongs) {
		this.albumSongs = albumSongs;
	}

	


}
