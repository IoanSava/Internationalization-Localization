package helpers;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestUtil {
    private static final String baseUrl = "http://www.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";
    private static final ResteasyClient client = new ResteasyClientBuilder().build();

    public static String getCapitalByCountryCode(String countryCode) {
        ResteasyWebTarget target = client.target(baseUrl + "/CapitalCity/JSON/debug");
        Response response = target.queryParam("sCountryISOCode", countryCode).request(MediaType.APPLICATION_JSON).get();
        String value = response.readEntity(String.class);
        response.close();
        return value;
    }

    public static String getContinentByCountryCode(String countryCode) {
        ResteasyWebTarget target = client.target(baseUrl + "/FullCountryInfo/JSON/debug");
        Response response = target.queryParam("sCountryISOCode", countryCode).request(MediaType.APPLICATION_JSON).get();
        String value = response.readEntity(String.class);
        response.close();
        JSONObject jsonObject = new JSONObject(value);
        return jsonObject.getString("sContinentCode");
    }
}
