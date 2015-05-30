/**
 * Created by MikaSez on 30/05/2015.
 */


var sideNavData = [
    {title: "Accueil", icon: "fa-dashboard", path: "/index.html", childs: []},
    {
        title: "Jobs", icon: "fa-wrench", path: "#",
        childs: [
            {title: "Voir les jobs", icon: "fa-tasks"},
            {title: "Creer un job", icon: "fa-plus"},
            {title: "Lancer un job", icon: "fa-play"}
        ]
    },
    {
        title: "Developpement purpose", icon: "fa-linux", path: "#",
        childs: [
            {title: "Login page", icon: "fa-users"},
            {title: "Registration page", icon: "fa-users"},
            {title: "Forgotten password page", icon: "fa-users"},
            {title: "Switch to admin view", icon: "fa-users"}
        ]
    }
];

var userOptionsData = [
    {title: "Profil utilisateur", icon: "fa-user", path: "#"},
    {title: "Parametrages", icon: "fa-gear", path: "#"},
    {title: "Déconnexion", icon: "fa-sign-out", path: "#"}
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