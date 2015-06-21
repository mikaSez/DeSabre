package info.desabre.application.views.generator;

import info.desabre.application.views.generator.annotations.Field;
import info.desabre.application.views.generator.annotations.Form;
import info.desabre.application.views.inputs.impl.TextField;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by MikaSez on 21/06/2015.
 */
public class FormProcessorTest {

    private FormProcessor tested = FormProcessor.getInstance();
    private TestedView view;
    @Before
    public void setUp() {
        view = new TestedView();
    }

    @Test
    public void testProcess() {

        info.desabre.application.views.inputs.form.Form<TestedView> f = tested.processView(view);

        Assert.assertEquals(1, f.getInputs().size());
        Assert.assertEquals(TextField.class, f.getInputs().get(0).getClass());
        Assert.assertEquals("lol", f.getInputs().get(0).getName());
        Assert.assertEquals("petitId", f.getInputs().get(0).getValue());


    }

}


@Form(id = "lol", path = "/view/test")
class TestedView {
    @Field(name = "lol", column = 1)
    private String id = "petitId";
    Boolean trucMuch = true;


    public void getChose() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setTrucMuch(Boolean trucMuch) {
        this.trucMuch = trucMuch;
    }
}