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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "offre")

@NamedQueries({
    @NamedQuery(name = "Offre.findAll", query = "SELECT o FROM Offre o")
    , @NamedQuery(name = "Offre.findById", query = "SELECT o FROM Offre o WHERE o.id = :id")
    , @NamedQuery(name = "Offre.findBySpecialite", query = "SELECT o FROM Offre o WHERE o.specialite = :specialite")
    , @NamedQuery(name = "Offre.findByLocalisation", query = "SELECT o FROM Offre o WHERE o.localisation = :localisation")
    , @NamedQuery(name = "Offre.findByNbDem", query = "SELECT o FROM Offre o WHERE o.nbDem = :nbDem")
    , @NamedQuery(name = "Offre.findByDescription", query = "SELECT o FROM Offre o WHERE o.description = :description")
    , @NamedQuery(name = "Offre.findByImagesoffre", query = "SELECT o FROM Offre o WHERE o.imagesoffre = :imagesoffre")})
public class Offre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "specialite")
    private String specialite;
    @Basic(optional = false)
    @Column(name = "localisation")
    private String localisation;
    @Basic(optional = false)
    @Column(name = "nb_dem")
    private int nbDem;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Column(name = "imagesoffre")
    private String imagesoffre;
    @JoinColumn(name = "typecategorie_id", referencedColumnName = "id")
    @ManyToOne
    private CategorieOffre typecategorieId;

    public Offre() {
    }

    public Offre(Integer id) {
        this.id = id;
    }

    public Offre(Integer id, String specialite, String localisation, int nbDem, String description) {
        this.id = id;
        this.specialite = specialite;
        this.localisation = localisation;
        this.nbDem = nbDem;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getNbDem() {
        return nbDem;
    }

    public void setNbDem(int nbDem) {
        this.nbDem = nbDem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagesoffre() {
        return imagesoffre;
    }

    public void setImagesoffre(String imagesoffre) {
        this.imagesoffre = imagesoffre;
    }

    public CategorieOffre getTypecategorieId() {
        return typecategorieId;
    }

    public void setTypecategorieId(CategorieOffre typecategorieId) {
        this.typecategorieId = typecategorieId;
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
        if (!(object instanceof Offre)) {
            return false;
        }
        Offre other = (Offre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PICodeName.entities.Offre[ id=" + id + " ]";
    }
    
}
