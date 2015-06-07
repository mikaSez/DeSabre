package info.desabre.database.models.server;


import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

/**
 * Created by MikaSez on 02/06/2015.
 */
public class Licence {

    @Id
    private int id;

    private String name;
    private Timestamp expiration;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getExpiration() {
        return expiration;
    }

    public void setExpiration(Timestamp expiration) {
        this.expiration = expiration;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
