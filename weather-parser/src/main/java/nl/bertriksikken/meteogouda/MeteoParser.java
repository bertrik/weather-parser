package nl.bertriksikken.meteogouda;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class MeteoParser {

    private final List<MeteoField> fields = new ArrayList<>();

    public void addField(MeteoField field) {
        int start1 = field.position;
        int end1 = field.position + field.length;
        for (MeteoField other : fields) {
            int start2 = other.position;
            int end2 = other.position + other.length;
            if ((end1 >= start2) && (end2 >= start1)) {
                throw new IllegalArgumentException("Field " + field.name + " overlaps with field " + other.name);
            }
        }
        fields.add(field);
    }

    public List<MeteoRecord> parse(List<String> lines) {
        List<MeteoRecord> list = new ArrayList<>();
        for (String line : lines) {
            Map<String, Object> map = parseLine(line);
            if (!map.isEmpty()) {
                MeteoRecord record = MeteoRecord.parse(map);
                list.add(record);
            }
        }
        return list;
    }

    private Map<String, Object> parseLine(String line) {
        Map<String, Object> map = new LinkedHashMap<>();
        for (MeteoField field : fields) {
            if (field.position < line.length()) {
                String sub = line.substring(field.position, field.position + field.length);
                Object value = parse(field, sub);
                if (value == null) {
                    if (field.mandatory) {
                        return Map.of();
                    }
                } else {
                    map.put(field.name, value);
                }
            }
        }
        return map;
    }

    private Object parse(MeteoField field, String data) {
        try {
            switch (field.type) {
            case "Integer":
                return Integer.parseInt(data.strip());
            case "Double":
                return Double.parseDouble(data.strip());
            default:
                throw new IllegalStateException("Unhandled type: " + field.type);
            }
        } catch (NumberFormatException e) {
            // fall through
        }
        return null;
    }

}
