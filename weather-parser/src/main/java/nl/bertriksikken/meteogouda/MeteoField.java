package nl.bertriksikken.meteogouda;

public final class MeteoField {
    public final String name;
    public final String type;
    public final int position;
    public final int length;
    public final boolean mandatory;

    MeteoField(String name, Class<?> type, int position, int length, boolean mandatory) {
        this.name = name;
        this.type = type.getSimpleName();
        this.position = position;
        this.length = length;
        this.mandatory = mandatory;
    }

    @Override
    public String toString() {
        return name;
    }
}