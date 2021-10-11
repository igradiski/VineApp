/**
 * String konstante u aplikaciji
 */

 const constantsUI = Object.freeze({

    //login form
    TEST: "Test",
    LABEL_USERNAME:"Korisnicko ime: ",
    PLACEHOLDER_USERNAME:"Korisnicko ime ",
    LABEL_PASSWORD:"Lozinka: ",
    PLACEHOLDER_PASSWORD:"Lozinka ",
    REMEMBER_ME : "Zapamti me",
    MESSAGE_USERNAME: "Molimo Vas unesite korisničko ime",
    MESSAGE_PASSWORD: "Molimo Vas unesite lozinku",
    BUTTON_LOGIN : "Prijava",
    BUTTON_REGISTER: "Registracija",

    //register from 
    LBL_REGISTRATION: "Registracija",
    LBL_USER_NAME: "Ime:",
    LBL_USER_SURNAME: "Prezime:",
    MESSAGE_NAME: "Molimo Vas unesite ime",
    MESSAGE_SURNAME: "Molimo Vas unesite prezime",
    LBL_MAIL: "Email:",
    MESSAGE_MAIL: "Molimo Vas unesite email",


    //left menu
    LEFT_MENU_HOME : "Početna",
    LEFT_MENU_LOGIN : "Prijava",
    LEFT_MENU_REGISTER : "Registracija",
    LEFT_MENU_CALENDAR : "Kalendar",
    LEFT_MENU_SREDSTVA : "Sredstva",
    LEFT_MENU_TIP_SREDSTVA : "Tip sredstva",
    API_BASE_URL: process.env.REACT_APP_BASE_URL,

    //TIP SREDSTVA
    TIP_SREDSTVA_NASLOV : "Tip sredstva",
    TIP_SREDSTVA_NAZIV : "Naziv tipa:",
    TIP_SREDSTVA_NAZIV_REQ: "Unesite naziv tipa sredstva",
    TIP_SREDSTVA_BUTTON_DODAJ: "Dodaj",
    TIP_SREDSTVA_NASLOV_SIFRARNIK: "Popis tipova sredstava",
    TIP_SREDSTVA_TBL_NAZIV: "Naziv tipa",
    TIP_SREDSTVA_TBL_DATUM: "Datum unosa",

    //SREDSTVA
    SREDSTVA_NASLOV : "Unos i pregled sredstava",
}) 

export default constantsUI;