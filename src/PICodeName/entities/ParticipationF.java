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
public class ParticipationF implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
     @Basic(optional = false)
    @Column(name = "id_formation")
    private int idformation;
    @Basic(optional = false)
    @Column(name = "id_participant")
    private int idParticipant;

    public ParticipationF() {
    }

    public ParticipationF(int id, int idformation, int idParticipant) {
        this.id = id;
        this.idformation = idformation;
        this.idParticipant = idParticipant;
    }

    public ParticipationF(int id) {
        this.id = id;
    }

    public ParticipationF(int idformation, int idParticipant) {
        this.idformation = idformation;
        this.idParticipant = idParticipant;
    }
    
    
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setIdformation(int idformation) {
        this.idformation = idformation;
    }

    public void setIdParticipant(int idParticipant) {
        this.idParticipant = idParticipant;
    }

    
    
    
    
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public int getIdformation() {
        return idformation;
    }

    public int getIdParticipant() {
        return idParticipant;
    }

   

   

    @Override
    public String toString() {
        return "PICodeName.entities.ParticipationF[ id=" + id + " ]";
    }
    
}
