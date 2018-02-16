package spotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Artist {

	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;

	public Artist(String firstName, String lastName, String bandName){
		this.firstName = firstName ;
		this.lastName = lastName ; 
		this.bandName = bandName ;
		this.artistID = UUID.randomUUID().toString();

		/*
		String sql = "INSERT INTO `artist`(`artist_id`,`first_name`,`last_name`,`band_name`,`bio`) ";
               sql += "VALUES ('" + this.artistID + "','" + this.firstName + "','" + this.lastName + "','" + this.bandName + "','" + "');";

        System.out.println(sql);

        DbUtilities db = new DbUtilities();
        db.executeQuery(sql);

		 */
		String sql = "INSERT INTO `artist`(`artist_id`,`first_name`,`last_name`,`band_name`,`bio`) ";
		sql += "VALUES (?,?,?,?,?);";

		//System.out.println(sql);

		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.artistID);
			ps.setString(2, this.firstName);
			ps.setString(3, this.lastName);
			ps.setString(4, this.bandName);
			ps.setString(5, " ");
			ps.executeUpdate();
			//System.out.println(ps);

			db.closeDbConnection();
			db=null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}

	public Artist(String artistID){
		String sql = "SELECT * FROM artist WHERE artist_id = '" + artistID + "';";
		//System.out.println(sql);

		DbUtilities db = new DbUtilities();

		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.artistID = rs.getString("artist_id");
				System.out.println("Artist ID from database : " + this.artistID);
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
				this.bio = rs.getString("bio");

				System.out.println("Artist name from database : " + this.firstName + " " + this.lastName);	

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
	}

	public String deleteArtist(String artistID){
		String sql = "DELETE FROM `artist` WHERE artist_id = '" + artistID + "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);

		return null;
	}

	public String getArtistID() {
		return this.artistID;
	}


	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;

		/*
		String sql = "UPDATE `artist` SET `first_name` = '" + this.firstName + "' where  artist_id = '" + this.artistID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		*/
		
		String sql = "UPDATE artist SET first_name = ? WHERE artist_id = ? ;";
		
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.firstName);
			ps.setString(2, this.artistID);
			ps.executeUpdate();
			conn.close();
			db=null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;

		/*
		String sql = "UPDATE `artist` SET `last_name` = '" + this.lastName + "' where  artist_id = '" + this.artistID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		*/
        String sql = "UPDATE artist SET last_name = ? WHERE artist_id = ? ;";
		
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.lastName);
			ps.setString(2, this.artistID);
			ps.executeUpdate();
			conn.close();
			db=null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public String getBandName() {
		return this.bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;

		/*
		String sql = "UPDATE `artist` SET `band_name` = '" + this.bandName + "' where  artist_id = '" + this.artistID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		*/
		
        String sql = "UPDATE artist SET band_name = ? WHERE artist_id = ? ;";
		
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.bandName);
			ps.setString(2, this.artistID);
			ps.executeUpdate();
			conn.close();
			db=null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;

		/*
		String sql = "UPDATE `artist` SET `bio` = '" + this.bio + "' where  artist_id = '" + this.artistID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		*/
		
        String sql = "UPDATE artist SET bio = ? WHERE artist_id = ? ;";
		
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.bio);
			ps.setString(2, this.artistID);
			ps.executeUpdate();
			conn.close();
			db=null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
