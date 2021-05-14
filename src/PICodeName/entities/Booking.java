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
@Table(name = "booking")
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b"),
    @NamedQuery(name = "Booking.findById", query = "SELECT b FROM Booking b WHERE b.id = :id"),
    @NamedQuery(name = "Booking.findByIdEvent", query = "SELECT b FROM Booking b WHERE b.idEvent = :idEvent"),
    @NamedQuery(name = "Booking.findByA1", query = "SELECT b FROM Booking b WHERE b.a1 = :a1"),
    @NamedQuery(name = "Booking.findByA2", query = "SELECT b FROM Booking b WHERE b.a2 = :a2"),
    @NamedQuery(name = "Booking.findByA3", query = "SELECT b FROM Booking b WHERE b.a3 = :a3"),
    @NamedQuery(name = "Booking.findByA4", query = "SELECT b FROM Booking b WHERE b.a4 = :a4"),
    @NamedQuery(name = "Booking.findByA5", query = "SELECT b FROM Booking b WHERE b.a5 = :a5"),
    @NamedQuery(name = "Booking.findByA6", query = "SELECT b FROM Booking b WHERE b.a6 = :a6"),
    @NamedQuery(name = "Booking.findByB1", query = "SELECT b FROM Booking b WHERE b.b1 = :b1"),
    @NamedQuery(name = "Booking.findByB2", query = "SELECT b FROM Booking b WHERE b.b2 = :b2"),
    @NamedQuery(name = "Booking.findByB3", query = "SELECT b FROM Booking b WHERE b.b3 = :b3"),
    @NamedQuery(name = "Booking.findByB4", query = "SELECT b FROM Booking b WHERE b.b4 = :b4"),
    @NamedQuery(name = "Booking.findByB5", query = "SELECT b FROM Booking b WHERE b.b5 = :b5"),
    @NamedQuery(name = "Booking.findByB6", query = "SELECT b FROM Booking b WHERE b.b6 = :b6"),
    @NamedQuery(name = "Booking.findByC1", query = "SELECT b FROM Booking b WHERE b.c1 = :c1"),
    @NamedQuery(name = "Booking.findByC2", query = "SELECT b FROM Booking b WHERE b.c2 = :c2"),
    @NamedQuery(name = "Booking.findByC3", query = "SELECT b FROM Booking b WHERE b.c3 = :c3"),
    @NamedQuery(name = "Booking.findByC4", query = "SELECT b FROM Booking b WHERE b.c4 = :c4"),
    @NamedQuery(name = "Booking.findByC5", query = "SELECT b FROM Booking b WHERE b.c5 = :c5"),
    @NamedQuery(name = "Booking.findByC6", query = "SELECT b FROM Booking b WHERE b.c6 = :c6"),
    @NamedQuery(name = "Booking.findByPrix", query = "SELECT b FROM Booking b WHERE b.prix = :prix")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_event")
    private int idEvent;
    @Column(name = "A1")
    private Integer a1;
    @Column(name = "A2")
    private Integer a2;
    @Column(name = "A3")
    private Integer a3;
    @Column(name = "A4")
    private Integer a4;
    @Column(name = "A5")
    private Integer a5;
    @Column(name = "A6")
    private Integer a6;
    @Column(name = "B1")
    private Integer b1;
    @Column(name = "B2")
    private Integer b2;
    @Column(name = "B3")
    private Integer b3;
    @Column(name = "B4")
    private Integer b4;
    @Column(name = "B5")
    private Integer b5;
    @Column(name = "B6")
    private Integer b6;
    @Column(name = "C1")
    private Integer c1;
    @Column(name = "C2")
    private Integer c2;
    @Column(name = "C3")
    private Integer c3;
    @Column(name = "C4")
    private Integer c4;
    @Column(name = "C5")
    private Integer c5;
    @Column(name = "C6")
    private Integer c6;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix")
    private Float prix;

    public Booking() {
    }

    public Booking(Integer id) {
        this.id = id;
    }

    public Booking(Integer id, int idEvent) {
        this.id = id;
        this.idEvent = idEvent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public Integer getA1() {
        return a1;
    }

    public void setA1(Integer a1) {
        this.a1 = a1;
    }

    public Integer getA2() {
        return a2;
    }

    public void setA2(Integer a2) {
        this.a2 = a2;
    }

    public Integer getA3() {
        return a3;
    }

    public void setA3(Integer a3) {
        this.a3 = a3;
    }

    public Integer getA4() {
        return a4;
    }

    public void setA4(Integer a4) {
        this.a4 = a4;
    }

    public Integer getA5() {
        return a5;
    }

    public void setA5(Integer a5) {
        this.a5 = a5;
    }

    public Integer getA6() {
        return a6;
    }

    public void setA6(Integer a6) {
        this.a6 = a6;
    }

    public Integer getB1() {
        return b1;
    }

    public void setB1(Integer b1) {
        this.b1 = b1;
    }

    public Integer getB2() {
        return b2;
    }

    public void setB2(Integer b2) {
        this.b2 = b2;
    }

    public Integer getB3() {
        return b3;
    }

    public void setB3(Integer b3) {
        this.b3 = b3;
    }

    public Integer getB4() {
        return b4;
    }

    public void setB4(Integer b4) {
        this.b4 = b4;
    }

    public Integer getB5() {
        return b5;
    }

    public void setB5(Integer b5) {
        this.b5 = b5;
    }

    public Integer getB6() {
        return b6;
    }

    public void setB6(Integer b6) {
        this.b6 = b6;
    }

    public Integer getC1() {
        return c1;
    }

    public void setC1(Integer c1) {
        this.c1 = c1;
    }

    public Integer getC2() {
        return c2;
    }

    public void setC2(Integer c2) {
        this.c2 = c2;
    }

    public Integer getC3() {
        return c3;
    }

    public void setC3(Integer c3) {
        this.c3 = c3;
    }

    public Integer getC4() {
        return c4;
    }

    public void setC4(Integer c4) {
        this.c4 = c4;
    }

    public Integer getC5() {
        return c5;
    }

    public void setC5(Integer c5) {
        this.c5 = c5;
    }

    public Integer getC6() {
        return c6;
    }

    public void setC6(Integer c6) {
        this.c6 = c6;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
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
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PICodeName.entities.Booking[ id=" + id + " ]";
    }
    
}
