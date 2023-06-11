package nl.bertriksikken.meteogouda;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeteoParserTest {

    private static final Logger LOG = LoggerFactory.getLogger(MeteoParserTest.class);

    @Test
    public void test() throws IOException, URISyntaxException {
        MeteoParser parser = new MeteoParser();
        parser.addField(MeteoRecord.DAY);
        parser.addField(MeteoRecord.HEAT_DEG);
        parser.addField(MeteoRecord.MEAN_TEMP);

        URL url = getClass().getClassLoader().getResource("meteo.txt");
        List<String> lines = Files.readAllLines(Path.of(url.toURI()));
        List<MeteoRecord> records = parser.parse(lines);
        double total = 0.0;
        for (MeteoRecord record : records) {
            LOG.info("{}", record);
            total += record.heatDeg;
        }
        LOG.info("total = {}", total);
    }

}
