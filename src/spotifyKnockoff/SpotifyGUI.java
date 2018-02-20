package spotifyKnockoff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class SpotifyGUI {

	private JFrame frame;
	private JRadioButton rdbShowAlbums;
	private JRadioButton rdbShowArtists;
	private JRadioButton rdbShowSongs;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JTable tblData;
	private DefaultTableModel musicData;
	private DefaultTableModel songData;
	private DefaultTableModel albumData;
	private DefaultTableModel artistData;
	
	Spotify search = new Spotify();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpotifyGUI window = new SpotifyGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SpotifyGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Spotify");
		frame.setBounds(100, 100, 1000, 600);
		frame.getContentPane().setLayout(null);
		
		JLabel lblViewSelector = new JLabel("Select View");
		lblViewSelector.setBounds(45, 30, 97, 26);
		frame.getContentPane().add(lblViewSelector);
		
		rdbShowAlbums = new JRadioButton("Albums");
		rdbShowAlbums.setSelected(true);
		rdbShowAlbums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbShowAlbums.isSelected()){
					System.out.println("Album Button clicked !");
					albumData = search.searchAlbums(txtSearch.getText());
					tblData.setModel(albumData);
				}
				
			}
		});
		
		rdbShowAlbums.setBounds(70, 60, 141, 23);
		frame.getContentPane().add(rdbShowAlbums);
		
	    rdbShowArtists = new JRadioButton("Artists");
	    rdbShowArtists.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("Artist Button Clicked !");
	    		artistData = search.searchArtists(txtSearch.getText());
				tblData.setModel(artistData);
	    	}
	    });
	    
	    rdbShowArtists.setBounds(70, 90, 141, 23);
		frame.getContentPane().add(rdbShowArtists);
		
		rdbShowSongs = new JRadioButton("Songs");
		rdbShowSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Song Button Clicked !");
				songData = search.searchSongs(txtSearch.getText());
				tblData.setModel(songData);
			}
		});
		rdbShowSongs.setBounds(70, 120, 141, 23);
		frame.getContentPane().add(rdbShowSongs);
		
		JLabel lblViewSearch = new JLabel("Search");
		lblViewSearch.setBounds(45, 320, 61, 16);
		frame.getContentPane().add(lblViewSearch);
		
		txtSearch = new JTextField();
		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		txtSearch.setBounds(45, 350, 200, 25);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		btnSearch= new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Search button clicked !");
				
				if(rdbShowAlbums.isSelected()){
					albumData = search.searchAlbums(txtSearch.getText());
					tblData.setModel(albumData);
					
				}else if(rdbShowSongs.isSelected()){
					//musicData= getSongData(txtSearch.getText());
					//tblData.setModel(musicData);
					songData = search.searchSongs(txtSearch.getText());
					tblData.setModel(songData);
			
				}else if(rdbShowArtists.isSelected()){
					artistData = search.searchArtists(txtSearch.getText());
					tblData.setModel(artistData);
					
				}
			}
		});
		
		btnSearch.setBounds(133, 390, 117, 25);
		frame.getContentPane().add(btnSearch);
		
		albumData = search.searchAlbums("");
		songData = search.searchSongs("");
		artistData = search.searchAlbums("");
		
		//musicData = getSongData("");
		tblData = new JTable(albumData);  //
		tblData.setBounds(311, 36, 600, 400);
		tblData.setFillsViewportHeight(true);
		tblData.setShowGrid(true);
		tblData.setGridColor(Color.BLACK);
		frame.getContentPane().add(tblData);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbShowAlbums);
		group.add(rdbShowArtists);
		group.add(rdbShowSongs);
		
		
	}
	
	
	
	
	//private void searchSongs(){
	//	txtSearch.getText();		
	//}
	
	
	
	
	/*
	private DefaultTableModel getSongData(String searchTerm){
		String sql = "SELECT song_id, title, length, release_date, record_date FROM song ";
		if(!searchTerm.equals("")){
			sql += "WHERE title LIKE '%" + searchTerm + "%';" ;
            			
		}
		//DefaultTableModel dataTable;
		
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Song ID", " Title", "Length", "Release Date", "Record Date"};
			return db.getDataTable(sql, columnNames);
			
		//} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//JOptionPane.showMessageDialog(frame, "Unable to connect to database");
			//ErrorLogger.log(e.toString());
		} catch (SQLException e){
			JOptionPane.showMessageDialog(frame, "Something WRONG");
			ErrorLogger.log(e.getMessage());
			
		}
		
		return null ;
	}
	*/
}
