package info.desabre;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MikaSez on 01/07/2015.
 */
@RestController
public class Controller {


    @RequestMapping("/jobStatus/{id}")
    public
    @ResponseBody
    String status(@PathVariable("id") String id) {
        return MonitorClass.status.get(id).name;
    }
}
