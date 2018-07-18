package com.itrided.android.jokeslibrary;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONObject;

public final class JokeTeller {
    private static final String CHUCK_NORRIS_API_URL = "https://api.chucknorris.io/jokes/random";
    private static final String DEFAULT_JOKE = "Chuck Norris threw a grenade and killed 50 people, then it exploded.";
    private static final String GET_JOKE = "value";

    public static String tellJoke() {
        try {
            final Client client = Client.create();
            final WebResource webResource = client.resource(CHUCK_NORRIS_API_URL);
            final ClientResponse response = webResource.get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            final String result = response.getEntity(String.class);
            final JSONObject json = new JSONObject(result);

            return json.getString(GET_JOKE);
        } catch (Exception e) {
            return DEFAULT_JOKE;
        }
    }
}
