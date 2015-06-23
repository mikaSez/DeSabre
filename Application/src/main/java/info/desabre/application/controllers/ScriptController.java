package info.desabre.application.controllers;

import info.desabre.application.services.UserService;
import info.desabre.application.views.grid.ScriptGridView;
import info.desabre.database.models.job.Script;
import info.desabre.repositories.job.ScriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Naiirod on 07/06/2015.
 * Controleur responsable de la gestion des serveurs.
 */
@Controller
@RequestMapping("/script/**")
public class ScriptController {

    private static final Logger log = Logger.getLogger(ScriptController.class.getName());

    @Autowired
    private UserService user;

    @Autowired
    private ScriptRepository repository;

    @RequestMapping("list")
    public String list(Model model) {
        log.info("Asked for list");
        return "script/scriptList";
    }

    @RequestMapping("/create")
    public String create(Model model) {

        return "server/serverCreate";
    }

    @RequestMapping("data")
    public
    @ResponseBody
    List<ScriptGridView> data(Model model) {
        log.info("data requested");
        List<Script> scripts = repository.findAll();
        scripts.forEach(e -> log.info(e.toString()));
        return Collections.unmodifiableList(ScriptGridView.map(scripts));

    }


    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadPage() {
        return "script/scriptUpload";

    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadRequest(@RequestParam("name") String name, @RequestParam(value = "isMain", required = false) Boolean isMain, @RequestParam("file") MultipartFile file) throws IOException {
        log.info("file uploading");
        String pathToFile = "files/" + user.getUser().getId() + "/scripts/";
        Path path = Paths.get(pathToFile + name);
        if (isMain == null) {
            isMain = false;
        }

        if (!Files.isDirectory(Paths.get(pathToFile))) {
            Files.createDirectories(Paths.get(pathToFile));
        }
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

        channel.write(ByteBuffer.wrap(file.getBytes()));
        channel.close();


        Script script = new Script(name, path.toString());

        script.setMainScript(isMain);
        repository.save(script);
        return "script/scriptList";

    }

}
