package nl.bertriksikken.meteogouda;

import java.io.IOException;

public final class RunNoaaClient {

    public static void main(String args[]) throws IOException {
        RunNoaaClient client = new RunNoaaClient();
        client.run();
    }

    private void run() throws IOException {
        NoaaReportConfig config = new NoaaReportConfig();
        NoaaReportClient client = NoaaReportClient.create(config);
        String data = client.getReport(2023, 5);
        System.out.println(data);
    }

}
