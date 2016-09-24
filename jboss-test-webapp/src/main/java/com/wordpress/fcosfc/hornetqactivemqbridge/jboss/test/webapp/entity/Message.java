package com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Paco Saucedo
 */
@Entity
@NamedQueries(value = {
    @NamedQuery(name = "Message.Received", query = "SELECT m "
            + "FROM Message m "
            + "WHERE m.in = true "
            + "ORDER BY m.dateInOut DESC"),
    @NamedQuery(name = "Message.Sent", query = "SELECT m "
            + "FROM Message m "
            + "WHERE m.in = false "
            + "ORDER BY m.dateInOut DESC")
})
public class Message implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInOut;

    private boolean in;

    public Message() {
    }

    public Message(String content, boolean in) {
        this.content = content;
        dateInOut = Calendar.getInstance().getTime();
        this.in = in;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateInOut() {
        return dateInOut;
    }

    public void setDateInOut(Date dateInOut) {
        this.dateInOut = dateInOut;
    }

    public boolean isIn() {
        return in;
    }

    public void setIn(boolean in) {
        this.in = in;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Message other = (Message) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", content=" + content + ", dateInOut=" + dateInOut + ", in=" + in + '}';
    }

}
