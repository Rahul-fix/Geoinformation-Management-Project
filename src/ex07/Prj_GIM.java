package ex07;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.JFrame;
import org.postgresql.ds.PGSimpleDataSource;

import viewer.base.DatabaseLayer;
import viewer.base.MapPanel;
import viewer.symbols.BasicSymbolFactory;


public class Prj_GIM {
	static String hostname = "131.220.71.164";
	static String port = "5432";
	static String dbname = "dm";
	static String user = "dm";
	static String password = "wise2019";
	
	
	public static void main(String[] args) {
		JFrame myMapFrame = new JFrame("IGGGIS - DatabaseLayer");
		myMapFrame.setPreferredSize(new Dimension(1000, 600));
		myMapFrame.pack();		
		MapPanel panel = new MapPanel();		

		PGSimpleDataSource dataSource = new PGSimpleDataSource();
		dataSource.setUrl("jdbc:postgresql://"+hostname+":"+port+"/"+dbname);

		dataSource.setUser(user);
		dataSource.setPassword(password);
		
		/**----------------------------------------------------------------------------------------------*/
			/*
			 * a)
			 */
		DatabaseLayer dbl_campus = null;
		try {
			dbl_campus= new DatabaseLayer(
					dataSource.getConnection().createStatement(),
					new BasicSymbolFactory(Color.BLACK, Color.LIGHT_GRAY),
					"public.fields",
					"geom"
					);

		} catch (SQLException e) { 
			e.printStackTrace(); 
		};
		panel.getMap().addLayer(dbl_campus, 0);
		
		myMapFrame.add(panel);
		myMapFrame.setVisible(true);
	}
}
