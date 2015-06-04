/**
 * Created by MikaSez on 30/05/2015.
 */


var sideNavData = [
    {title: "Accueil", icon: "fa-dashboard", path: "/index.html", childs: []},
    {
        title: "Jobs", icon: "fa-wrench", path: "#",
        childs: [
            {title: "Voir les jobs", icon: "fa-tasks", path: "job/list"},
            {title: "Creer un job", icon: "fa-plus", path: "job/create"},
            {title: "Lancer un job", icon: "fa-play", path: "job/launch"}
        ]
    },
    {
        title: "Developpement purpose", icon: "fa-linux", path: "#",
        childs: [
            {title: "Login page", icon: "fa-users", path: "/login"},
            {title: "Registration page", icon: "fa-users", path: "/register"},
            {title: "Forgotten password page", icon: "fa-users", path: "/restore"},
            {title: "Switch to admin view", icon: "fa-users", path: "/admin/home"},
            {title: "Switch to user view", icon: "fa-users", path: "/user/home"}
        ]
    }
];

var userOptionsData = [
    {title: "Profil utilisateur", icon: "fa-user", path: "/profil"},
    {title: "Parametrages", icon: "fa-gear", path: "/settings"},
    {title: "Déconnexion", icon: "fa-sign-out", path: "/logout"}
];

var taskData = [
    {title: "Compter les poules", progression: "70", status: "danger", state: 4},
    {title: "Calculer le nombre PI", progression: "30", status: "info", state: 2}
];

var messageData = [
    {
        title: "Calcul du chiffre PI",
        end: "12/04/2015",
        path: "#",
        message: "Ce travail lancé *15/12/2013* sur le serveur *CALC2* s'est terminé *hier*. "
    },
    {title: "Sécurité", end: "11/04/2015", path: "#", message: "Le mot de passe a été reinitialisé. "},
    {
        title: "Compter les poules",
        end: "10/04/2015",
        path: "#",
        message: "Ce travail lancé le *15/12/2013* sur le serveur *CALC1* est en erreur. "
    }
];

var widgetBoxData = [
    {id: 1, color: "primary", icon: "bell", number: 5, path: "#", text: "Nouvelle(s) notification(s)."},
    {id: 2, color: "red", icon: "database", number: 3, path: "#", text: "Job(s) en cours."},
    {id: 3, color: "green", icon: "tasks", number: 12, path: "#", text: "Messages."}
];



var widgetTimelineData =
{
    title: "Compter les poules", steps: [
    {id: 0, title: "Creation du job", text: "Le job a été crée le 12.04.15", step: 1, icon: "gift", iconType: "info"},
    {
        id: 1,
        title: "Lancement du job",
        text: "Le job a été lancé le 14.12.15",
        step: 2,
        icon: "play",
        iconType: "success"
    },
    {
        id: 2,
        title: "Chargement sur le serveur",
        text: "Le job a été chargé sur le serveur le 12.04.15 par Jerome ",
        step: 3,
        icon: "upload",
        iconType: "success"
    },
    {
        id: 3,
        title: "Lancement sur le serveur",
        text: "Le job a été lancé par le serveur",
        step: 4,
        icon: "history",
        iconType: "info"
    },
    {
        id: 4,
        title: "Fin du job",
        text: "Interrompu par l'utilisateur",
        step: 5,
        icon: "puzzle-piece",
        iconType: "danger"
    }
]
};


var widgetGridHeader = {
    title: "Visualiser les jobs",
    headers: [
        {text: "Nom"},
        {text: "Crée le"},
        {text: "Visualiser"}
    ]
};

var widgetGridData = [
    {id: 0, title: "Compter les poules", date: "10/02/2003", path: "#"},
    {id: 1, title: "Vitesse moyenne de Superman", date: "10/02/2005", path: "#"},
    {id: 2, title: "Production moyenne de toile par Spiderman", date: "10/02/2003", path: "#"},
    {id: 3, title: "Nombre PI", date: "10/02/2003", path: "#"},
    {id: 4, title: "Dechiffrage enigma ", date: "10/02/1941", path: "#"},
    {id: 5, title: "Chiffrage Titan", date: "10/02/1942", path: "#"},
    {id: 8, title: "Compter les poules", date: "10/02/2003", path: "#"},
    {id: 9, title: "Vitesse moyenne de Superman", date: "10/02/2005", path: "#"},
    {id: 21, title: "Production moyenne de toile par Spiderman", date: "10/02/2003", path: "#"},
    {id: 32, title: "Nombre PI", date: "10/02/2003", path: "#"},
    {id: 43, title: "Dechiffrage enigma ", date: "10/02/1941", path: "#"},
    {id: 54, title: "Chiffrage Titan", date: "10/02/1942", path: "#"},
    {id: 123, title: "Compter les poules", date: "10/02/2003", path: "#"},
    {id: 15, title: "Vitesse moyenne de Superman", date: "10/02/2005", path: "#"},
    {id: 23, title: "Production moyenne de toile par Spiderman", date: "10/02/2003", path: "#"},
    {id: 323, title: "Nombre PI", date: "10/02/2003", path: "#"},
    {id: 489, title: "Dechiffrage enigma ", date: "10/02/1941", path: "#"},
    {id: 5435, title: "Chiffrage Titan", date: "10/02/1942", path: "#"},
    {id: 612, title: "Nombre de naissance par seconde", date: "10/02/2011", path: "#"}
];