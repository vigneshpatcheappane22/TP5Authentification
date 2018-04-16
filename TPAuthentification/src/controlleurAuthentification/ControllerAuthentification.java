package controlleurAuthentification;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controlleur.BD.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modele.Compte;

public class ControllerAuthentification implements Initializable{

	private ArrayList<Compte> compte=new ArrayList<Compte>();
	//Sert de pointeur vers le compte sélectionné dans notre arraylist
	private int position;
	
	@FXML
	private TextField logintextfield;
	
	@FXML
	private TextField mdptextfield;
	
	@FXML
	private Label statutlabel;

	
	public ControllerAuthentification() {
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		ChargementDonnees charg= new ChargementDonnees();
		this.compte=charg.getCompteChargés();
	}
	


	public void Login(ActionEvent event){
		//on vérifie d'abord si le compte est bloqué
		if(compte_bloque()) {
			String message = " Compte bloqué";
			statutlabel.setText(message);
			return ;
		}
		if(id_correct()) {
			if(mdp_correct()) {
					statutlabel.setText("Login + mdp correct ! BRAVO !");
	
					return ;
			}
			else {
				System.out.println("Mauvais mdp");
				String message = " Mot de passe incorrect\n Il vous reste : "+compte.get(position).getNbr_tentative()+" tentatives";
				statutlabel.setText(message);
				return ;
			}
		}
		else {
			System.out.println("Login inconnue !");
			String message = " Login inconnu";
			statutlabel.setText(message);
			return ;
		}
	}

	public ControllerAuthentification(ArrayList<Compte> compte) {
		super();
		this.compte = compte;
		this.position=0;
	}

	public boolean id_correct() {
		int i=0;
		for(int j=0;j<this.compte.size();j++){
			System.out.print(this.compte.get(j).getLogin());
			if(this.compte.get(j).getLogin().equals(logintextfield.getText())){
				this.position=i;
				return true;
			}
			i++;
		}
		
		return false;
	}

	public boolean mdp_correct() {
		if(this.compte.get(position).getMdp().equals(mdptextfield.getText()))
			return true;
		
		return false;
	}
	
	public boolean compte_bloque() {
		if(this.compte.get(position).getNbr_tentative()<1)
			return true;
		decrementerNbrTentative();
		return false;
	}

	//Une erreur = une tentative en moins
	public void decrementerNbrTentative() {
		this.compte.get(position).setNbr_tentative(this.compte.get(position).getNbr_tentative()-1);
	}



}
