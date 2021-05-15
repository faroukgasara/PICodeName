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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author farou
 */
@Entity
@Table(name = "participation_e")
@NamedQueries({
    @NamedQuery(name = "ParticipationE.findAll", query = "SELECT p FROM ParticipationE p"),
    @NamedQuery(name = "ParticipationE.findById", query = "SELECT p FROM ParticipationE p WHERE p.id = :id"),
    @NamedQuery(name = "ParticipationE.findByIdEvenement", query = "SELECT p FROM ParticipationE p WHERE p.idEvenement = :idEvenement"),
    @NamedQuery(name = "ParticipationE.findByIdParticipant", query = "SELECT p FROM ParticipationE p WHERE p.idParticipant = :idParticipant")})
public class ParticipationE implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_evenement")
    private int idEvenement;
    @Basic(optional = false)
    @Column(name = "id_participant")
    private int idParticipant;

    public ParticipationE() {
    }

    public ParticipationE(Integer id) {
        this.id = id;
    }

    public ParticipationE(Integer id, int idEvenement, int idParticipant) {
        this.id = id;
        this.idEvenement = idEvenement;
        this.idParticipant = idParticipant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public int getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(int idParticipant) {
        this.idParticipant = idParticipant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipationE)) {
            return false;
        }
        ParticipationE other = (ParticipationE) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PICodeName.entities.ParticipationE[ id=" + id + " ]";
    }
    
}
