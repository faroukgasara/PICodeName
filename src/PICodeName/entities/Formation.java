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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fedi
 */
@Entity
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "date_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAt;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "localisation")
    private String localisation;
    @Basic(optional = false)
    @Column(name = "id_soc")
    private int id_soc;
    @Column(name = "imagef")
    private String imagef;

    public Formation() {
    }

    public Formation(int id) {
        this.id = id;
    }

    public Formation(int id, String description, Date dateAt, String title, String localitation, int id_soc, String imagef) {
        this.id = id;
        this.description = description;
        this.dateAt = dateAt;
        this.title = title;
        this.localisation = localitation;
        this.id_soc = id_soc;
        this.imagef = imagef;
    }

    public Formation(String description, Date dateAt, String title, String localitation, int id_soc) {
        
        this.description = description;
        this.dateAt = dateAt;
        this.title = title;
        this.localisation = localitation;
        this.id_soc = id_soc;
    }
     public Formation(String description, Date dateAt, String title, String localitation) {
        
        this.description = description;
        this.dateAt = dateAt;
        this.title = title;
        this.localisation = localitation;
        
    }
    

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateAt() {
        return dateAt;
    }

    public String getTitle() {
        return title;
    }

    public String getLocalisation() {
        return localisation;
    }

    public int getId_soc() {
        return id_soc;
    }

    public String getImagef() {
        return imagef;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateAt(Date dateAt) {
        this.dateAt = dateAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocalisation(String localitation) {
        this.localisation = localitation;
    }

    public void setId_soc(int id_soc) {
        this.id_soc = id_soc;
    }

    public void setImagef(String imagef) {
        this.imagef = imagef;
    }
   
    @Override
    public String toString() {
        return "PICodeName.entities.Formation[ id=" + id + " ]";
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PICodeNamePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
