package info.desabre.application.views.inputs.form;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by MikaSez
 */
public class FormProcessor<T> {
    List<Method> methods;

    private static PasswordEncoder encoder = new BCryptPasswordEncoder();
    T object;
    Map<String, String> map;
    Map<String, Method> keyAndSetter;
    public static final Logger log = Logger.getLogger(FormProcessor.class.getName());


    public FormProcessor(Map<String, String> map, T object) {
        this.object = object;
        this.map = map;
        keyAndSetter = new HashMap<>();

    }

    public T process() {
        mapMethods();
        mapKeysToMethods();
        processUnmappedBooleans();
        processMapper();
        log.info("final object : " + object);
        return object;

    }

    /**
     * HTML doesn't send any data from uncheked checkboxes.
     * So we'll assume all booleans are checkboxes and in need to be set.
     * <p>
     * //TODO TRES lourd parcours... optimiser ?
     */
    private void processUnmappedBooleans() {
        methods.stream().filter(m -> !map.containsKey(m.getName().substring(3).toLowerCase()))
                .filter(m -> m.getParameterTypes()[0].equals(Boolean.class))
                .forEach(
                        o -> {
                            try {
                                o.invoke(object, false);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        });
    }

    private void processMapper() {
        //tous les booleans présents sont avec valeur
        keyAndSetter.entrySet().stream().filter(o -> o.getValue().getParameterTypes()[0].equals(Boolean.class))
                .forEach(o -> {
                    try {
                        o.getValue().invoke(object, true);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });


        keyAndSetter.entrySet().stream().filter(o -> o.getValue().getParameterTypes()[0].equals(String.class))
                .forEach(o -> {
                    try {
                        if (o.getKey().equals("password")) {
                            o.getValue().invoke(object, encoder.encode(map.get(o.getKey())));
                        } else {
                            o.getValue().invoke(object, map.get(o.getKey()));
                        }

                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
    }


    private void mapKeysToMethods() {

        map.keySet().forEach(key ->
                        methods.stream().filter(o -> o.getName().substring(3).equalsIgnoreCase(key))
                                .forEach(o -> keyAndSetter.put(key, o))
        );

        log.info("Found corresponding methods :" + keyAndSetter);

    }

    private void mapMethods() {
        methods = Arrays.asList(object.getClass().getMethods())
                .stream()
                .filter(method -> method.getName().substring(0, 3).equals("set"))
                .collect(Collectors.toList());
    }
}
