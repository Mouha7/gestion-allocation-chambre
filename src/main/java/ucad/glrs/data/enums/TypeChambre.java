package ucad.glrs.data.enums;

public enum TypeChambre {
    INDIVIDUEL, DUAL;

    public static TypeChambre get(String value) {
        for (TypeChambre type : TypeChambre.values()) {
            if (type.name().compareToIgnoreCase(value) == 0) {
                return type;
            }
        }
        return null;
    }
}
