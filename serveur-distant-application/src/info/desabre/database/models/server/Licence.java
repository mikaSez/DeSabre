package info.desabre.database.models.server;


import java.util.Date;

/**
 * Created by MikaSez on 02/06/2015.
 */
public class Licence {


    private String id;

    private String name;
    private Date expiration;
    private int count;
    
    public Licence(String name, int count){
    	this.name = name;
        this.expiration = new Date();
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
