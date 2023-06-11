package nl.bertriksikken.meteogouda;

import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Example URL: https://www.meteo-gouda.nl/noaa_rapporten/noaa202305.txt
 */
public final class NoaaReportClient {

    private static final Logger LOG = LoggerFactory.getLogger(NoaaReportClient.class);

    private final INoaaReportApi restApi;

    NoaaReportClient(INoaaReportApi restApi) {
        this.restApi = restApi;
    }

    public static NoaaReportClient create(NoaaReportConfig config) {
        LOG.info("Creating new REST client for URL '{}' with timeout {}", config.getHost(), config.getTimeout());
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(config.getTimeout())
                .readTimeout(config.getTimeout()).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(config.getHost())
                .addConverterFactory(ScalarsConverterFactory.create()).client(client).build();
        INoaaReportApi restApi = retrofit.create(INoaaReportApi.class);
        return new NoaaReportClient(restApi);
    }

    public String getReport(int year, int month) throws IOException {
        String name = String.format(Locale.ROOT, "noaa%04d%02d.txt", year, month);
        Response<String> response = restApi.getNoaaReport(name).execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody().string());
        }
        return response.body();
    }

}
