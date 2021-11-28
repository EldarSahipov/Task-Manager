package models;

import java.util.Objects;

public enum DelayedTime {

    FIVE_MINUTES(Objects.requireNonNull(JsonParser.getJsonObject()).get("min5")),
    THIRTY_MINUTES(Objects.requireNonNull(JsonParser.getJsonObject()).get("min5")),
    ONE_HOUR(Objects.requireNonNull(JsonParser.getJsonObject()).get("min5")),
    SIX_HOURS(Objects.requireNonNull(JsonParser.getJsonObject()).get("min5")),
    TWENTY_FOUR_HOURS(Objects.requireNonNull(JsonParser.getJsonObject()).get("min5"));

    private final String typeValue;

    DelayedTime(Object type) {
        typeValue = (String) type;
    }

    static public DelayedTime getType(String pType) {
        for (DelayedTime type: DelayedTime.values()) {
            if (type.getTypeValue().equals(pType)) {
                return type;
            }
        }
        throw new RuntimeException("unknown type");
    }

    public String getTypeValue() {
        return typeValue;
    }
}
