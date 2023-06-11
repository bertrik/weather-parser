package nl.bertriksikken.meteogouda;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface INoaaReportApi {

    @GET("/noaa_rapporten/{path}")
    public Call<String> getNoaaReport(@Path("path") String name);

}
