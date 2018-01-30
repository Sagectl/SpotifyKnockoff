package spotifyKnockoff;

import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Song {

	private String songID;
	private String title;
	private double length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	private String albumID;
	Map<String, Artist> songArtists = new HashMap<String, Artist>();


	public Song(String title, double length, String releaseDate, String recordDate, String albumID ){
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate; 
		this.songID = UUID.randomUUID().toString();	
		this.albumID = albumID;

		//System.out.println(this.songID);

		String sql = "INSERT INTO `song`(`song_id`,`title`,`length`,`file_path`,`release_date`,`record_date`,`fk_album_id`) ";
		sql+= "VALUES ('" + this.songID + "','" + this.title + "','" + this.length + "',''," + "'" + this.releaseDate + "','" +this.recordDate + "','" + this.albumID + "');" ;

		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);


	}

	public Song(String songID){
		String sql = "SELECT * FROM song WHERE song_id = '" + songID + "';";
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.songID = rs.getString("song_id");
				//System.out.println("Song ID from database: " + this.songID);
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.recordDate = rs.getDate("record_date").toString();
				this.length = rs.getDouble("length");
				this.albumID = rs.getString("fk_album_id");
				System.out.println("Song title from database: " + this.title);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}

	public String deleteSong(String songID){
		String sql = "DELETE FROM `song` WHERE song_id = '" + songID + "';";
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);

		return null;
	}

	public void addArtist(Artist artist){	
		this.songArtists.put(artist.getArtistID(), artist);  

	}


	public void deleteArtist(String artistID){
		this.songArtists.remove(artistID);

	}

	public void deleteArtist(Artist artist){
		this.deleteArtist(artist.getArtistID());

	}

	public String getSongID() {
		return this.songID;
	}


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;

		String sql = "UPDATE `song` SET `title` = '" + this.title + "' where  song_id = '" + this.songID + "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);

	}

	public double getLength() {
		return this.length;
	}

	public void setLength(double length) {
		this.length = length;


		String sql = "UPDATE `song` SET `length` = '" + this.length + "' where  song_id = '" + this.songID + "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;

		String sql = "UPDATE `song` SET `file_path` = '" + this.filePath + "' where  song_id = '" + this.songID + "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public String getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;

		String sql = "UPDATE `song` SET `release_date` = '" + this.releaseDate + "' where  song_id = '" + this.songID + "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public String getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;

		String sql = "UPDATE `song` SET `record_date` = '" + this.recordDate + "' where  song_id = '" + this.songID + "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public String getAlbumID() {
		return this.albumID;
	}


	public Map<String, Artist> getSongArtists() {
		return songArtists;
	}

	public void setSongArtists(Map<String, Artist> songArtists) {
		this.songArtists = songArtists;
	}


}
