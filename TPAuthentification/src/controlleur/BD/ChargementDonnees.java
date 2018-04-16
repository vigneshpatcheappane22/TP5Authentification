package controlleur.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import modele.Compte;

public class ChargementDonnees {
	private ArrayList<Compte> compteChargés=new ArrayList<Compte>();
	
	
	
	public ChargementDonnees(){
		try {
			//On récupère une connexion à la BDD
			Connection conn= connexionDAOMySQL.getInstance();
			//On va récupérer les données de la BDD pour en faire de nouveaux objets
			PreparedStatement state =  conn.prepareStatement("SELECT * FROM compte");
			//On éxécute les requêtes sql
			ResultSet res = state.executeQuery();		
			//On va récupérer tous les comptes de la base de données et les rentrer dans notre ArrayList
			
			while(res.next()) {
				String login = res.getString("login");
				String passwd = res.getString("mdp");
				Compte temp = new Compte(login,passwd);
				this.compteChargés.add(temp);}
			
			}catch(Exception e){
				Alert probleme = new Alert(AlertType.ERROR);
				probleme.setTitle("Erreur");
				probleme.setHeaderText("Impossible de charger les données, vérifier votre connexion.");
				probleme.showAndWait();
			}
	}

	public ArrayList<Compte> getCompteChargés() {
		return compteChargés;
	}

	public void setCompteChargés(ArrayList<Compte> compteChargés) {
		this.compteChargés = compteChargés;
	}
}
