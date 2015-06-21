package info.desabre.application.views.generator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by MikaSez on 21/06/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Form {
    String id();

    int nbColumn() default 1;

    boolean readOnly() default true;

    String path() default "/";

}
