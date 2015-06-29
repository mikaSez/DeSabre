package info.desabre.fillers;

import info.desabre.UserConstants;
import info.desabre.database.models.information.WidgetBox;
import info.desabre.database.models.information.WidgetType;
import info.desabre.repositories.information.WidgetBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseWidgetBoxFiller {
    @Autowired
    WidgetBoxRepository widgetBoxRepository;

    public DatabaseWidgetBoxFiller() {
    }

    public void widgetBoxMockData() {
        System.out.println("Création des données des widgetbox");
        List<WidgetBox> widgets = new ArrayList<>();
        widgetBoxRepository.deleteAll();

        initAdminWidget(widgets);


        initGlobalWidgetBox(widgets);

        System.out.println("Les données sont mis dans la widgetbox");
        widgetBoxRepository.save(widgets);
    }

    void initGlobalWidgetBox(List<WidgetBox> widgets) {
        widgets.add(new WidgetBox("primary", "bell", 0, "/news/list", "Notification(s).", UserConstants.GLOBAL_GROUPEID.getGroupeId(), WidgetType.NEWS));
        widgets.add(new WidgetBox("red", "database", 0, "#", "Job(s) en cours.", UserConstants.GLOBAL_GROUPEID.getGroupeId(), WidgetType.JOB));
        widgets.add(new WidgetBox("green", "superscript", 0, "/script/list", "Scripts créés.", UserConstants.GLOBAL_GROUPEID.getGroupeId(), WidgetType.SCRIPT));
    }

    void initAdminWidget(List<WidgetBox> widgets) {
        widgets.add(new WidgetBox("primary", "tasks", 0, "/news/list", "Notification(s).", UserConstants.ADMIN_GROUPEID.getGroupeId(), WidgetType.NEWS));
        widgets.add(new WidgetBox("primary", "users", 0, "/admin/users", "Utilisateurs.", UserConstants.ADMIN_GROUPEID.getGroupeId(), WidgetType.USER));
        widgets.add(new WidgetBox("red", "database", 0, "#", "Serveurs.", UserConstants.ADMIN_GROUPEID.getGroupeId(), WidgetType.SERVER));
    }
}