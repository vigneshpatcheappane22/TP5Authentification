package controlleur.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connexionDAOMySQL {
	private static String url = "jdbc:mysql://localhost:3306/tp5?autoReconnect=true&useSSL=false";
	private static String utilisateur = "root";
	private static String motDePasse = "";
	private static Connection connexion;
	
	public static Connection getInstance(){
		if(connexion==null){
			try{
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				connexion=DriverManager.getConnection(url,utilisateur,motDePasse);
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return connexion;
	}
}
