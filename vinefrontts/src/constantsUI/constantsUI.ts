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
    UNOS_TIP_SREDSTVA_SUCCESS_TITLE : "Unos uspješan",
    UNOS_TIP_SREDSTVA_SUCCESS : "Podatak je uspješno unesen u bazu",
    UNOS_TIP_SREDSTVA_ERROR_TITLE : "Greška kod unosa",
    UNOS_TIP_SREDSTVA_ERROR : "Dogodila se greška kod unosa, provjerite podatke!",

    //SREDSTVA
    SREDSTVA_FORMA_UNOS: "Unos sredstava",
    SREDSTVA_SIFRARNIK: "Pregled sredstava",
    SREDSTVA_NASLOV : "Unos i pregled sredstava",
    SREDSTVA_NAME : "Naziv:",
    SREDSTVA_NAME_REQUIRED : "Potreno je unijeti naziv sredstva!",
    SREDSTVA_OPIS : "Opis: ",
    SREDSTVA_SASTAV: "Sastav: ",
    SREDSTVA_GRUPA : "Grupa: ",
    SREDSTVA_FORMULACIJA: "Formulacija: ",
    SREDSTVA_NACIN_DJELOVANJA: "Način djelovanja: ",
    SREDSTVA_UPORABA : "Uporaba: ",
    SREDSTVA_KONCENTRACIJA : "Koncentracija: ",
    SREDSTVA_DOZIRANJE : "Doza(100L):",
    SREDSTVA_KARENCA : "Karenca: ",
    SREDSTVA_UNOS_BUTTON: "Unesi sredstvo",
    SREDSTVA_TIP_SREDSTVA: "Tip sredstva: ",
    SREDSTVA_TIP_SREDSTVA_PLACEHOLDER: "Odaberite tip sredstva",
    UNOS_SREDSTVA_SUCCESS_TITLE : "Unos uspješan",
    UNOS_SREDSTVA_SUCCESS : "Podatak je uspješno unesen u bazu",
    UNOS_SREDSTVA_ERROR_TITLE : "Greška kod unosa",
    UNOS_SREDSTVA_ERROR : "Dogodila se greška kod unosa, provjerite podatke!",

    //SREDSTVA_SIFRARNIK
    SREDSTVA_SIFRARNIK_NAZIV:"Naziv",
    SREDSTVA_SIFRARNIK_OPIS:"Opis",
    SREDSTVA_SIFRARNIK_SASTAV:"Sastav",
    SREDSTVA_SIFRARNIK_GRUPA:"Grupa",
    SREDSTVA_SIFRARNIK_FORMULACIJA:"Formulacija",
    SREDSTVA_SIFRARNIK_PRIMJENA:"Primjena",
    SREDSTVA_SIFRARNIK_KORISTENJE:"Koristenje",
    SREDSTVA_SIFRARNIK_KONCENTRACIJA:"Koncentracija",
    SREDSTVA_SIFRARNIK_DOZIRANJE:"Doziranje na 100l",
    SREDSTVA_SIFRARNIK_UNESEN:"Datum unosa",
    SREDSTVA_SIFRARNIK_TIP:"Tip sredstva",

    //BOLEST
    BOLEST_FORMA_UNOS: "Unos bolesti",
    BOLEST_SIFRARNIK: "Pregled bolesti",
    BOLEST_NASLOV:"Unos bolesti",
    BOLEST_NAZIV:"Naziv bolesti",
    BOLEST_NAZIV_MESSAGE_REQUIRED:"Unesite naziv bolesti!",
    BOLEST_OPIS_MESSAGE_REQUIRED:"Unesite opis bolesti!",
    BOLEST_OPIS:"Opis bolesti",
    UNOS_BOLEST_SUCCESS_TITLE:"Unos bolesti uspješan",
    UNOS_BOLEST_SUCCESS:"Bolest je uspješno unesena u bazu!",
    UNOS_BOLEST_ERROR:"Dogodila se greška kod upisa bolesti!",
    UNOS_BOLEST_ERROR_TITLE:"Greška kod unosa bolesti!",
    BOLEST_SIFRARNIK_NAZIV:"Naziv",
    BOLEST_SIFRARNIK_OPIS:"Opis",
    BOLEST_SIFRARNIK_DATUM:"Datum unosa",
    BOLEST_BRISANJE_USPJELO_NASLOV:"Uspješno obrisan zapis",
    BOLEST_BRISANJE_USPJELO:"Zapis je uspješno izbrisan",
    BOLEST_BRISANJE_FAIL_NASLOV:"Brisanje nije uspjelo!",
    BOLEST_BRISANJE_FAIL:"Brisanje nije moguće!",
    BOLEST_BRISANJE_PITANJE:"Želite li obrisati zapis?",

    //FENOZAFA RAZVOJA
    FENOFAZA_NASLOV: "Fenofaza razvoja",
    FENOFAZA_FORMA_UNOS: "Unos fenofaza",
    FENOFAZA_SIFRARNIK:"Pregled fenofaza",
    FENOFAZA_SUCCESS_TITLE:"Unos uspješan",
    FENOFAZA_SUCCESS:"Fenofaza razvoja uspješno unesena!",
    FENOFAZA_ERROR_TITLE:"Greška kod unosa",
    FENOFAZA_ERROR:"Dogodila se greška kod unosa fenofaze!",
    FENOFAZA_FORM_NAZIV: "Naziv fenofaze: ",
    FENOFAZA_FORM_NAZIV_REQUIRED: "Potrebno je unijeti naziv!",
    FENOFAZA_FORM_VRIJEME: "Vrijeme prskanja: ",
    FENOFAZA_FORM_VRIJEME_REQUIRED : "Potrebno je unijeti vrijeme prskanja!",
}) 

export default constantsUI;