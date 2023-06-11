package nl.bertriksikken.meteogouda;

import java.util.Locale;
import java.util.Map;

/**
 * One line in the meteo file.
 */
public final class MeteoRecord {

    static final MeteoField DAY = new MeteoField("day", Integer.class, 0, 2, true);
    static final MeteoField MEAN_TEMP = new MeteoField("meantemp", Double.class, 3, 5, false);
    static final MeteoField HEAT_DEG = new MeteoField("heatdeg", Double.class, 37, 5, true);

    public final int day;
    public final double meanTemp;
    public final double heatDeg;

    MeteoRecord(int day, double meanTemp, double heatDeg) {
        this.day = day;
        this.meanTemp = meanTemp;
        this.heatDeg = heatDeg;
    }

    static MeteoRecord parse(Map<String, Object> map) {
        int day = (int) map.get(DAY.name);
        Double meanTemp = (Double) map.getOrDefault(MEAN_TEMP.name, Double.NaN);
        Double heatDeg = (Double) map.get(HEAT_DEG.name);
        return new MeteoRecord(day, meanTemp, heatDeg);
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "{day=%2d,heatdeg=%5.1f,meanTemp=%5.1f,}", day, heatDeg, meanTemp);
    }

}
