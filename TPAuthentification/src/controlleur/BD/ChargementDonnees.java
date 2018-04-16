package controlleur.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import modele.Compte;

public class ChargementDonnees {
	private ArrayList<Compte> compteCharg�s=new ArrayList<Compte>();
	
	
	
	public ChargementDonnees(){
		try {
			//On r�cup�re une connexion � la BDD
			Connection conn= connexionDAOMySQL.getInstance();
			//On va r�cup�rer les donn�es de la BDD pour en faire de nouveaux objets
			PreparedStatement state =  conn.prepareStatement("SELECT * FROM compte");
			//On �x�cute les requ�tes sql
			ResultSet res = state.executeQuery();		
			//On va r�cup�rer tous les comptes de la base de donn�es et les rentrer dans notre ArrayList
			
			while(res.next()) {
				String login = res.getString("login");
				String passwd = res.getString("mdp");
				Compte temp = new Compte(login,passwd);
				this.compteCharg�s.add(temp);}
			
			}catch(Exception e){
				Alert probleme = new Alert(AlertType.ERROR);
				probleme.setTitle("Erreur");
				probleme.setHeaderText("Impossible de charger les donn�es, v�rifier votre connexion.");
				probleme.showAndWait();
			}
	}

	public ArrayList<Compte> getCompteCharg�s() {
		return compteCharg�s;
	}

	public void setCompteCharg�s(ArrayList<Compte> compteCharg�s) {
		this.compteCharg�s = compteCharg�s;
	}
}
