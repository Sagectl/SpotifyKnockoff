package spotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	Map<String, Artist> songArtists;


	public Song(String title, double length, String releaseDate, String recordDate, String albumID ){
		this.title = title ;
		this.length = length ;
		this.releaseDate = releaseDate ;
		this.recordDate = recordDate ;
		this.songID = UUID.randomUUID().toString();
		this.albumID= albumID ;

		songArtists = new Hashtable<String, Artist>();

		/*
		String sql = "INSERT INTO `song`(`song_id`,`title`,`length`,`file_path`,`release_date`,`record_date`,`fk_album_id`) ";
		       sql += "VALUES ('" +this.songID +"','"+ this.title + "','" +this.length +"','"+ " " + "','" + this.releaseDate + "','" + this.recordDate + "','" + this.albumID + "');" ;

		//System.out.println(sql);
		 * 
		 * DbUtilities db = new DbUtilities();
		 * db.executeQuery(sql);
		 * db.closeDbConnection();
		 * db=null;
		 */

		String sql = "INSERT INTO `song`(`song_id`,`title`,`length`,`file_path`,`release_date`,`record_date`,`fk_album_id`) ";
		sql+= "VALUES (?,?,?,?,?,?,?);";

		//System.out.println(sql);


		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2, this.title);
			ps.setDouble(3, this.length);
			ps.setString(4, this.filePath);
			ps.setString(5, this.releaseDate);
			ps.setString(6, this.recordDate);
			ps.setString(7, this.albumID);
			ps.executeUpdate();

			db.closeDbConnection();
			db=null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Song(String songID, String title, double length, String releaseDate, String recordDate){
		this.title = title ;
		this.length = length ;
		this.releaseDate = releaseDate ;
		this.recordDate = recordDate ;
		this.songID = songID;
		

		songArtists = new Hashtable<String, Artist>();
	}

	public Song(String songID){
		songArtists = new Hashtable<String, Artist>();

		String sql = "SELECT * FROM song WHERE song_id = '" + songID + "';";
		//System.out.println(sql);

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
				//System.out.println("Song title from database: " + this.title);

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
		songArtists.put(artist.getArtistID(), artist);  

		String sql = "INSERT INTO `song_artist`(`fk_song_id`,`fk_artist_id`) VALUES (?,?);";


		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2, artist.getArtistID());
			ps.executeUpdate();
			conn.close();
			db=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void deleteArtist(String artistID){
		songArtists.remove(artistID);

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

		/*
		String sql = "UPDATE `song` SET `title` = '" + this.title + "' where  song_id = '" + this.songID + "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		 */

		String sql = "UPDATE song SET title = ? WHERE song_id = ? ;";


		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.title);
			ps.setString(2, this.songID);
			ps.executeUpdate();
			conn.close();
			db= null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public double getLength() {
		return this.length;
	}

	public void setLength(double length) {
		this.length = length;


		/*
		String sql = "UPDATE `song` SET `length` = '" + this.length + "' where  song_id = '" + this.songID + "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		 */

		String sql = "UPDATE song SET length = ? WHERE song_id = ? ;";


		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, this.length);
			ps.setString(2, this.songID);
			ps.executeUpdate();
			conn.close();
			db = null ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;

		/*
		String sql = "UPDATE `song` SET `file_path` = '" + this.filePath + "' where  song_id = '" + this.songID + "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		 */
		String sql = "UPDATE `song` SET `file_path` = ? where  song_id = ? ;" ;

		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.filePath);
			ps.setString(2, this.songID);
			ps.executeUpdate();
			conn.close();
			db=null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;

		/*
		String sql = "UPDATE `song` SET `release_date` = '" + this.releaseDate + "' where  song_id = '" + this.songID + "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		 */

		String sql = "UPDATE song SET release_date = ? WHERE song_id = ? ;";


		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.releaseDate);
			ps.setString(2, this.songID);
			ps.executeUpdate();
			conn.close();
			db = null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;

		/*
		String sql = "UPDATE `song` SET `record_date` = '" + this.recordDate + "' where  song_id = '" + this.songID + "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		 */

		String sql = "UPDATE song SET release_date = ? WHERE song_id = ? ;";


		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.releaseDate);
			ps.setString(2, this.songID);
			ps.executeUpdate();
			conn.close();
			db = null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	Vector<String> getSongRecord(){
		Vector<String> songRecord = new Vector<>(6);
		
		songRecord.add(this.songID);
		songRecord.add(this.title);
		songRecord.add(String.valueOf(this.length));
		songRecord.add(this.filePath);
		songRecord.add(this.releaseDate);
		songRecord.add(this.recordDate);
		
		return songRecord;

	}


}
