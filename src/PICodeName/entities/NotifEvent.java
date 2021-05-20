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
@Table(name = "notif_event")
@NamedQueries({
    @NamedQuery(name = "NotifEvent.findAll", query = "SELECT n FROM NotifEvent n"),
    @NamedQuery(name = "NotifEvent.findById", query = "SELECT n FROM NotifEvent n WHERE n.id = :id"),
    @NamedQuery(name = "NotifEvent.findByNotif", query = "SELECT n FROM NotifEvent n WHERE n.notif = :notif")})
public class NotifEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "notif")
    private String notif;

    public NotifEvent() {
    }

    public NotifEvent(Integer id) {
        this.id = id;
    }

    public NotifEvent(Integer id, String notif) {
        this.id = id;
        this.notif = notif;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
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
        if (!(object instanceof NotifEvent)) {
            return false;
        }
        NotifEvent other = (NotifEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PICodeName.entities.NotifEvent[ id=" + id + " ]";
    }
    
}
