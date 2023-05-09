package com.storeship.entities;

import java.util.Date;

public class Reclamation {
private int IdReclamation;
private int IdAdmin;
private int IdUser;
private int IdProduit;
private int IdCommande;
private String Contenu;
private Date Date;
private String Etat;
private TypeReclamation Type;
private String Image;
public Reclamation(int idReclamation, int idUser, int idCommande, String etat, String image, Date date, String contenu, int idProduit1, int idAdmin, TypeReclamation type) {
	super();
	IdReclamation = idReclamation;
	IdAdmin = idAdmin;
	IdUser = idUser;
	IdProduit = idProduit1;
	Contenu = contenu;
	Date = date;
	Etat = etat;
	IdCommande=idCommande;
	Type=type;
	Image=image;
	
}
public Reclamation(int idUser, int idCommande,String etat,Date date,String image,String contenu,int idProduit,int idAdmin,TypeReclamation type) {
	super();
	IdAdmin = idAdmin;
	IdUser = idUser;
	IdProduit = idProduit;
	Contenu = contenu;
	Date = date;
	Etat = etat;
	IdCommande=idCommande;
	Type=type;
      Image=image;}

    public Reclamation() {
    }
public int getIdCommande() {
	return IdCommande;
}
public void setIdCommande(int idCommande) {
	IdCommande = idCommande;
}
public TypeReclamation getType() {
	return Type;
}
public void setType(TypeReclamation type) {
	Type = type;
}
public int getIdReclamation() {
	return IdReclamation;
}
@Override
public String toString() {
	return "Reclamation [IdReclamation=" + IdReclamation + ", IdAdmin=" + IdAdmin + ", IdUser=" + IdUser
			+ ", IdProduit=" + IdProduit + ", Contenu=" + Contenu + ", Date=" + Date + ", Etat=" + Etat + "]";
}
public void setIdReclamation(int idReclamation) {
	IdReclamation = idReclamation;
}
public int getIdAdmin() {
	return IdAdmin;
}
public void setIdAdmin(int idAdmin) {
	IdAdmin = idAdmin;
}
public int getIdUser() {
	return IdUser;
}
public void setIdUser(int idUser) {
	IdUser = idUser;
}
public int getIdProduit() {
	return IdProduit;
}
public void setIdProduit(int idProduit) {
	IdProduit = idProduit;
}
public String getContenu() {
	return Contenu;
}
public void setContenu(String contenu) {
	Contenu = contenu;
}
public Date getDate() {
	return Date;
}
public void setDate(Date date) {
	Date = date;
}
public String getEtat() {
	return Etat;
}
public void setEtat(String etat) {
	Etat = etat;
}

    public String getImage() {
        return Image;
        }
	
}
