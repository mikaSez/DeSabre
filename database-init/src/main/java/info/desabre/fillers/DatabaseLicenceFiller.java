package info.desabre.fillers;

import info.desabre.database.models.server.Licence;
import info.desabre.repositories.licence.LicenceRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseLicenceFiller {
    @Autowired
    LicenceRepository licencesRepository;

	public DatabaseLicenceFiller() {
		// TODO Auto-generated constructor stub
	}

    public void licenceMockData() {
        System.out.println("Ajout de licences :)");

        licencesRepository.deleteAll();

        List<Licence> licences = new ArrayList();
        licences.add(new Licence("Matlab", 1));
        licences.add(new Licence("Matematica", 0));
        licences.add(new Licence("F#", 0));
        System.out.println(licences.size() + " licences créés ");
        licences.forEach(s -> System.out.println("leurs noms sont : " + s.getName()));
        licencesRepository.save(licences);

    }

}
