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
 * @author wassim
 */
@Entity
@Table(name = "surfer")

@NamedQueries({
    @NamedQuery(name = "Surfer.findAll", query = "SELECT s FROM Surfer s"),
    @NamedQuery(name = "Surfer.findById", query = "SELECT s FROM Surfer s WHERE s.id = :id"),
    @NamedQuery(name = "Surfer.findByName", query = "SELECT s FROM Surfer s WHERE s.name = :name"),
    @NamedQuery(name = "Surfer.findByFirstName", query = "SELECT s FROM Surfer s WHERE s.firstName = :firstName"),
    @NamedQuery(name = "Surfer.findByCin", query = "SELECT s FROM Surfer s WHERE s.cin = :cin"),
    @NamedQuery(name = "Surfer.findByPassword", query = "SELECT s FROM Surfer s WHERE s.password = :password"),
    @NamedQuery(name = "Surfer.findByEmailadress", query = "SELECT s FROM Surfer s WHERE s.emailadress = :emailadress")})
public class Surfer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "cin")
    private Integer cin;
    @Column(name = "password")
    private String password;
    @Column(name = "emailadress")
    private String emailadress;

    public Surfer() {
    }

    public Surfer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getCin() {
        return cin;
    }

    public void setCin(Integer cin) {
        this.cin = cin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailadress() {
        return emailadress;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
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
        if (!(object instanceof Surfer)) {
            return false;
        }
        Surfer other = (Surfer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PICodeName.entities.Surfer[ id=" + id + " ]";
    }
    
}
