/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ala
 */
@Entity
@Table(name = "company")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findById", query = "SELECT c FROM Company c WHERE c.id = :id"),
    @NamedQuery(name = "Company.findByDescription", query = "SELECT c FROM Company c WHERE c.description = :description"),
    @NamedQuery(name = "Company.findByTaxRegistrationNumber", query = "SELECT c FROM Company c WHERE c.taxRegistrationNumber = :taxRegistrationNumber"),
    @NamedQuery(name = "Company.findByLocalisation", query = "SELECT c FROM Company c WHERE c.localisation = :localisation"),
    @NamedQuery(name = "Company.findByNumero", query = "SELECT c FROM Company c WHERE c.numero = :numero"),
    @NamedQuery(name = "Company.findByImages", query = "SELECT c FROM Company c WHERE c.images = :images"),
    @NamedQuery(name = "Company.findByPass", query = "SELECT c FROM Company c WHERE c.pass = :pass"),
    @NamedQuery(name = "Company.findByEmailadresse", query = "SELECT c FROM Company c WHERE c.emailadresse = :emailadresse"),
    @NamedQuery(name = "Company.findByNameCompany", query = "SELECT c FROM Company c WHERE c.nameCompany = :nameCompany")})
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Column(name = "tax_registration_number")
    private Integer taxRegistrationNumber;
    @Column(name = "localisation")
    private String localisation;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "images")
    private String images;
    @Column(name = "pass")
    private String pass;
    @Column(name = "emailadresse")
    private String emailadresse;
    @Column(name = "name_company")
    private String nameCompany;
    @OneToMany(mappedBy = "companyId")
    private Collection<Reclamation> reclamationCollection;

    public Company() {
    }

    public Company(Integer id) {
        this.id = id;
    }

    public Company(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    public void setTaxRegistrationNumber(Integer taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmailadresse() {
        return emailadresse;
    }

    public void setEmailadresse(String emailadresse) {
        this.emailadresse = emailadresse;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    //@XmlTransient
    public Collection<Reclamation> getReclamationCollection() {
        return reclamationCollection;
    }

    public void setReclamationCollection(Collection<Reclamation> reclamationCollection) {
        this.reclamationCollection = reclamationCollection;
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
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PICodeName.entities.Company[ id=" + id + " ]";
    }
    
}
