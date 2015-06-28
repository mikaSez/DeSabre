package info.desabre.application.views.forms.views;

import info.desabre.database.models.server.Licence;
import info.desabre.database.models.server.Server;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ServerCreateView {

    @NotNull(message = "Le nom doit être renseigné")
    @Size(min = 2, message = "Le nom doit comporter au moins deux caractères")
    private String name;

    @NotNull(message = "Le nombre de coeur est obligatoire")
    @Min(1)
    @Max(1024)
    private Integer coreCount;

    @NotNull(message = "Une adresse est obligatoire")
    @Size(min = 2, message = "L\'adresse du serveur doit comporter au moins deux caractères")
    private String address;

    private List<Licence> licences;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(Integer coreCount) {
        this.coreCount = coreCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addLicence(Licence licence) {
        licences.add(licence);
    }


    public List<Licence> getLicences() {
        return licences;
    }

    public void setLicences(List<Licence> licences) {
        this.licences = licences;
    }

    public Server mapToServer() {
        return new Server(name, address, coreCount, licences);
    }

    @Override
    public String toString() {
        return "JobCreateView{" +
                "name='" + name + "'" +
                "address='" + address + "'" +
                "coreCount=" + coreCount +
                "licences={'" + licences.toString() + "'}" +
                "}";
    }
}
