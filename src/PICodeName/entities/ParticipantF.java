/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author fedi
 */
@Entity
public class ParticipantF implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
     @Basic(optional = false)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;

    public ParticipantF(int id) {
        this.id = id;
    }

    public ParticipantF(int id, String mail, String nom) {
        this.id = id;
        this.mail = mail;
        this.nom = nom;
    }

    public ParticipantF(String mail, String nom) {
        this.mail = mail;
        this.nom = nom;
    }
    

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMail() {
        return mail;
    }

    public String getNom() {
        return nom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PICodeName.entities.ParticipantF[ id=" + id + " ]";
    }
    
}
