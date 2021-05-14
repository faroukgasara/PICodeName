/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author wassim
 */
public class Rendezvous implements Serializable{
     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "meet")
    private String meet;
    @Basic(optional = false)
    @Column(name = "mail_id")
    private int mail_id;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    public Rendezvous(Integer id, Date date, String meet, int mail_id, String description) {
        this.id = id;
        this.date = date;
        this.meet = meet;
        this.mail_id = mail_id;
        this.description = description;
    }

    public Rendezvous() {
    }

    public Rendezvous(Integer id) {
        this.id = id;
    }

    public Rendezvous(String meet, String description) {
        this.meet = meet;
        this.description = description;
    }

    public Rendezvous(String meet, int mail_id, String description) {
        this.meet = meet;
        this.mail_id = mail_id;
        this.description = description;
    }

    public Rendezvous(Date date, String meet, String description) {
        this.date = date;
        this.meet = meet;
        this.description = description;
    }

    public Rendezvous(Date date, String meet,String description, int mail_id) {
        this.date = date;
        this.meet = meet;
        this.mail_id = mail_id;
        this.description = description;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getMeet() {
        return meet;
    }

    public int getMail_id() {
        return mail_id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMeet(String meet) {
        this.meet = meet;
    }

    public void setMail_id(int mail_id) {
        this.mail_id = mail_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rendezvous other = (Rendezvous) obj;
        if (this.mail_id != other.mail_id) {
            return false;
        }
        if (!Objects.equals(this.meet, other.meet)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rendezvous{" + "id=" + id + ", date=" + date + ", meet=" + meet + ", mail_id=" + mail_id + ", description=" + description + '}';
    }
   
    
}
