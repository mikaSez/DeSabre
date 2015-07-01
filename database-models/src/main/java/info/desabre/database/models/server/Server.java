package info.desabre.database.models.server;


import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MikaSez on 02/06/2015.
 */
public class Server {

    @Id
    private String id;

    private String name;
    private int coreCount;
    private int machineCount;
    private String address;
    private String connexionAdress;
    private boolean deleted;
    private List<Licence> licences;
    private int memory;


    public Server() {
    }

    public Server(String id, String name, int machineCount, int coreCount) {
        this.id = id;
        this.name = name;
        this.machineCount = machineCount;
        this.coreCount = coreCount;
    }

    public Server(String name, String address, int coreCount, List<Licence> licences) {
        this.name = name;
        this.address = address;
        this.coreCount = coreCount;
        this.licences = licences;
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

    public int getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(int coreCount) {
        this.coreCount = coreCount;
    }

    public int getMachineCount() {
        return machineCount;
    }

    public void setMachineCount(int machineCount) {
        this.machineCount = machineCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConnexionAdress() {
        return connexionAdress;
    }

    public void setConnexionAdress(String connexionAdress) {
        this.connexionAdress = connexionAdress;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<Licence> getLicences() {
        if (licences == null) {
            this.licences = new ArrayList<>();
        }
        return licences;
    }

    public void setLicences(List<Licence> licences) {
        this.licences = licences;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
