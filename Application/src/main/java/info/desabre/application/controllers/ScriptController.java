package info.desabre.application.controllers;

import info.desabre.application.services.ScriptTypes;
import info.desabre.application.services.UserService;
import info.desabre.application.views.grid.ScriptGridView;
import info.desabre.database.models.job.Script;
import info.desabre.repositories.job.ScriptRepository;
import info.desabre.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/script/**")
public class ScriptController {

    private static final Logger log = Logger.getLogger(ScriptController.class.getName());


    @Autowired
    private UserService user;

    @Autowired
    private ScriptRepository repository;


    @Autowired
    private UserRepository userRepository;

    @RequestMapping("list")
    public String list(Model model) {
        log.info("Asked for list");
        return "script/scriptList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createView(Model model) throws IOException {
        List<String> modes = new ArrayList<>();
        for (ScriptTypes type : ScriptTypes.values()) {
            modes.add(type.getName());
        }
        model.addAttribute("modes", modes);
        return "script/scriptCreate";
    }


    @RequestMapping(value = {"/create", "/detail"}, method = RequestMethod.POST)
    public String createRequest(Model model, @RequestParam("name") String name,
                                @RequestParam(value = "isMain", required = false) Boolean isMain, @RequestParam("script") String script) throws IOException {


        Path path = createFile(name);
        log.info(script.toString());
        Script s;
        saveFile(path, script.getBytes());
        java.util.Optional<Script> opt = user.getUser().getScripts().stream().filter(o -> o.getName().equalsIgnoreCase(name)).findFirst();
        if (opt.isPresent()) {
            s = opt.get();

        } else {
            s = new Script(name, path.toString());
            user.getUser().getScripts().add(s);
            userRepository.save(user.getUser());
        }


        if (isMain == null) {
            isMain = false;
        }

        s.setMainScript(isMain);
        repository.save(s);

        return "script/scriptList";
    }


    @RequestMapping("data")
    public
    @ResponseBody
    List<ScriptGridView> data() {
        log.info("data requested");
        List<Script> scripts = user.getUser().getScripts();
        scripts.forEach(e -> log.info(e.toString()));
        return Collections.unmodifiableList(ScriptGridView.map(scripts));

    }


    @RequestMapping(value = "/detail")
    public String details(Model model, @RequestParam("id") String id) {
        List<String> modes = new ArrayList<>();
        for (ScriptTypes type : ScriptTypes.values()) {
            modes.add(type.getName());
        }
        model.addAttribute("modes", modes);
        Script script = repository.findById(id);
        model.addAttribute("name", script.getName());
        model.addAttribute("isMain", script.getMainScript());

        String content = "";
        try {
            content = readFile(Paths.get(getPathToFiles() + script.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("script", content);
        return "script/scriptCreate";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadPage() {
        return "script/scriptUpload";

    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadRequest(@RequestParam("name") String name, @RequestParam(value = "isMain", required = false) Boolean isMain, @RequestParam("file") MultipartFile file) throws IOException {
        log.info("file uploading");


        if (isMain == null) {
            isMain = false;
        }

        Path path = createFile(name);

        Script script;
        saveFile(path, file.getBytes());
        java.util.Optional<Script> opt = user.getUser().getScripts().stream().filter(o -> o.getName().equalsIgnoreCase(name)).findFirst();
        if (opt.isPresent()) {
            script = opt.get();
        } else {
            script = new Script(name, path.toString());
            user.getUser().getScripts().add(script);
            userRepository.save(user.getUser());
        }

        script.setMainScript(isMain);
        repository.save(script);

        return "script/scriptList";

    }


    private Path createFile(String fileName) throws IOException {
        String pathToFile = getPathToFiles();
        Path path = Paths.get(pathToFile + fileName);
        if (!Files.isDirectory(Paths.get(pathToFile))) {
            Files.createDirectories(Paths.get(pathToFile));
        }
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        return path;
    }

    private void saveFile(Path path, byte[] bytes) throws IOException {
        FileChannel channel = FileChannel
                .open(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

        channel.write(ByteBuffer.wrap(bytes));
        channel.close();
    }

    private String readFile(Path path) throws IOException {
        BufferedReader reader = Files.newBufferedReader(path);
        StringBuilder sb = new StringBuilder();
        reader.lines().forEach(o -> sb.append(o).append('\n'));
        return sb.toString();

    }

    public String getPathToFiles() {
        return "files/" + user.getUser().getId() + "/scripts/";
    }
}
