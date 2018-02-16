package spotifyKnockoff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

public class SpotifyTester {

	public static void main(String[] args) {
		
		//Song s = new Song("Fly Over States", 3.38, "2012-02-06", "2010-06-05", "bd4024b2-0483-11e8-a67a-005056881e07");
		//Song s1 = new Song("1979", 4.50, "2015-11-12", "2014-05-12", "9a80d0b2-0527-11e8-a67a-005056881e07");
		//Song s2 = new Song("Hotel California", 6.30, "1976-12-8", "1972-10-10", "a18dcdaa-0528-11e8-a67a-005056881e07");
		//s2.setTitle("BBBBBB");
		//Song s3 = new Song("Twenty Eight", 4.18, "2012-11-13", "2011-04-12", "c9638702-0528-11e8-a67a-005056881e07");
		//Song s4 = new Song("Uprising", 5.02, "2009-08-04", "2008-09-04", "c964a7fe-0528-11e8-a67a-005056881e07");
		
		//Song s5 = new Song("616494a7-b803-43ed-a59c-7fcca17e5e80");
		
		//Song s6 = new Song(null);
		//s6.deleteSong("616494a7-b803-43ed-a59c-7fcca17e5e80");
		
		
		//Artist a1 = new Artist("Jason","Aldean","None");

		//Artist a2 = new Artist("0b9b3e2f-ed38-4b11-add0-3e26314589cf");
		
		//Artist a3 = new Artist (null);
		//a3.deleteArtist("ba1bd116-a311-49a5-a1db-4bf73359b115");
		
		//Album s10 = new Album("My Kinda Party", "2010-11-02", " ", 15, "R", 3.38);
			
		//Album s11 = new Album ("973c7d55-06cb-4a7e-8d21-43c479df1384");
		
		//Album s12 = new Album("null");	
		//s12.deleteAlbum("973c7d55-06cb-4a7e-8d21-43c479df1384");


		//Album s14 = new Album("973c7d55-06cb-4a7e-8d21-43c479df1384");
		//s14.setTitle("My Kinda Party");
		//s14.setLength(56.44);
		
		//ArrayList<Song> songList = new ArrayList<Song>();
		
		//Map<String, Song> songList = new Hashtable<String, Song>();
		Vector<Vector<String>> songTable = new Vector<>();
		
		try {
			DbUtilities db = new DbUtilities();
			String sql = "SELECT * FROM song ;";
			ResultSet rs = db.getResultSet(sql);
			
			while(rs.next()){
				Song s = new Song(rs.getString("song_id"),
						          rs.getString("title"),
						          rs.getDouble("length"),
						          rs.getString("release_date"),
						          rs.getString("record_date"));
						          
				songTable.add(s.getSongRecord());
				
				//songList.add(s);
				//songList.put(s.getSongID(), s);
				//System.out.println(s.getTitle());
			}
			
			db.closeDbConnection();
			db=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < songTable.size() ; i++){
			for(int j = 0 ; j < songTable.get(i).size() ; j++){
				System.out.print(songTable.get(i).get(j) + "\t\t\t");
			}
			System.out.println();
		}
		
		
		/*
		//Find a song with ID = "fb475dc9-f820-4335-8d4e-c08de5b8b06b"
		
		for(Song s : songList){
			if(s.getSongID().equalsIgnoreCase("fb475dc9-f820-4335-8d4e-c08de5b8b06b")){
				System.out.println("Found : " + s.getTitle());
				break;
			}
		}
		*/
		//Find a song with ID = "fb475dc9-f820-4335-8d4e-c08de5b8b06b"
		
		//Song foundSong = songList.get("fb475dc9-f820-4335-8d4e-c08de5b8b06b");
		//System.out.println(foundSong.getTitle());
		
		//Song s13 = new Song("e05682d7-80c7-43ea-8117-2f83de10be9b");
		//s13.setReleaseDate("2000-01-01");
		//Artist a1 = new Artist("74bff9c1-a152-47b3-86fc-18c9d1e3536b");
		//s13.addArtist(a1);
		
		
		//Artist a5 = new Artist("a0b019a9-ade3-4eda-94a7-1a2f6f70c7d9");
		//a5.setFirstName("Andy");
		//a5.setLastName("Warhol");
		//a5.setBandName("His Libraary");
		//a5.setBio("He is a cool guy!");
		
		
		
}

}
	
