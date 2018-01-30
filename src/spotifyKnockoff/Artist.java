package spotifyKnockoff;

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
		
		String sql = "INSERT INTO `artist`(`artist_id`,`first_name`,`last_name`,`band_name`,`bio`) ";
               sql += "VALUES ('" + this.artistID + "','" + this.firstName + "','" + this.lastName + "','" + this.bandName + "','" + "');";
		
        System.out.println(sql);
        
        DbUtilities db = new DbUtilities();
        db.executeQuery(sql);
		
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
		
		String sql = "UPDATE `artist` SET `first_name` = '" + this.firstName + "' where  artist_id = '" + this.artistID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		
		String sql = "UPDATE `artist` SET `last_name` = '" + this.lastName + "' where  artist_id = '" + this.artistID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public String getBandName() {
		return this.bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
		
		String sql = "UPDATE `artist` SET `band_name` = '" + this.bandName + "' where  artist_id = '" + this.artistID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
		
		String sql = "UPDATE `artist` SET `bio` = '" + this.bio + "' where  artist_id = '" + this.artistID+ "';" ;
		System.out.println(sql);

		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}


}
