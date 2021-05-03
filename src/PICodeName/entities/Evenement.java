/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author farou
 */
@Entity
@Table(name = "evenement")
@NamedQueries({
    @NamedQuery(name = "Evenement.findAll", query = "SELECT e FROM Evenement e"),
    @NamedQuery(name = "Evenement.findById", query = "SELECT e FROM Evenement e WHERE e.id = :id"),
    @NamedQuery(name = "Evenement.findByDateAt", query = "SELECT e FROM Evenement e WHERE e.dateAt = :dateAt"),
    @NamedQuery(name = "Evenement.findByTitle", query = "SELECT e FROM Evenement e WHERE e.title = :title"),
    @NamedQuery(name = "Evenement.findByType", query = "SELECT e FROM Evenement e WHERE e.type = :type"),
    @NamedQuery(name = "Evenement.findByDescription", query = "SELECT e FROM Evenement e WHERE e.description = :description"),
    @NamedQuery(name = "Evenement.findByLocalitation", query = "SELECT e FROM Evenement e WHERE e.localitation = :localitation"),
    @NamedQuery(name = "Evenement.findByIdSociete", query = "SELECT e FROM Evenement e WHERE e.idSociete = :idSociete"),
    @NamedQuery(name = "Evenement.findByPicture", query = "SELECT e FROM Evenement e WHERE e.picture = :picture"),
    @NamedQuery(name = "Evenement.findByViewed", query = "SELECT e FROM Evenement e WHERE e.viewed = :viewed")})
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAt;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "localitation")
    private String localitation;
    @Basic(optional = false)
    @Column(name = "id_societe")
    private int idSociete;
    @Column(name = "picture")
    private String picture;
    @Column(name = "viewed")
    private Integer viewed;

    public Evenement() {
    }

    public Evenement(Integer id) {
        this.id = id;
    }

    public Evenement(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public Evenement(String title, String type, String description, String localitation, int idSociete,int viewed) {

        this.title = title;
        this.type = type;
        this.description = description;
        this.localitation = localitation;
        this.idSociete = idSociete;
        this.viewed = viewed;
    }

    public Evenement(Integer id, Date dateAt, String title, String type, String description, String localitation, int idSociete) {
        this.id = id;
        this.dateAt = dateAt;
        this.title = title;
        this.type = type;
        this.description = description;
        this.localitation = localitation;
        this.idSociete = idSociete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateAt() {
        return dateAt;
    }

    public void setDateAt(Date dateAt) {
        this.dateAt = dateAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalitation() {
        return localitation;
    }

    public void setLocalitation(String localitation) {
        this.localitation = localitation;
    }

    public int getIdSociete() {
        return idSociete;
    }

    public void setIdSociete(int idSociete) {
        this.idSociete = idSociete;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
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
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PICodeName.entities.Evenement[ id=" + id + " ]";
    }

}
