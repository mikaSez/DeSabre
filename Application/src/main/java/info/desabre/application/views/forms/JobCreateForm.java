package info.desabre.application.views.forms;

import info.desabre.application.views.inputs.Input;
import info.desabre.application.views.inputs.form.Form;
import info.desabre.application.views.inputs.impl.SpecialField;
import info.desabre.application.views.inputs.impl.TextField;
import info.desabre.database.models.job.Job;

/**
 * Created by Naiirod on 21/06/2015.
 */
public class JobCreateForm extends Form<Job> {

	public JobCreateForm(String id) {
        super(id);
        this.setNbColumn(2);
        this.setReadOnly(true);
	}

    public void getForm() {
        Input in = new TextField("taskName", "Nom de la tâche", "Nom de la tâche");
        this.addInput(in, 1);

        in = new SpecialField("licences", "Licences nécessaires");
        this.addInput(in, 1);

        in = new SpecialField("scripts", "Scripts");
        this.addInput(in, 2);

        in = new SpecialField("params", "Parametres");
        this.addInput(in, 2);
    }
}
