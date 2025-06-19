package ma.alten.backend.constantes;

public class Validation {

    public static final String BLANK = "CHAMP REQUIS";
    public static final String POSITIVE = "CHAMP N'ACCEPTE PAS LES VALEURS NEGATIVES";
    public static final String MIN = "LE MINIMUM EST 0";
    public static final String MAX = "LE MAXIMUM EST 10";
    public static final String DATETIME_PATTERN = "ENTREZ UNE DATE VALIDE";

    public static final String REQUEST_DATA_FORMAT_ERROR = "La requête contient des données invalides ou mal formatées.";

    public static final String PHONE_PATTERN = "^(?:\\+[\\d\\s\\-()]{7,20}|0[67]\\d{8})$";
    public static final String INVALID_PHONE_PATTERN ="ENTREZ UN TELEPHONE AU FORMAT VALIDE";

    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String INVALID_EMAIL_PATTERN ="ENTREZ UN EMAIL AU FORMAT VALIDE";

}
