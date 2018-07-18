package com.itrided.android.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import com.itrided.android.jokeslibrary.JokeTeller;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "theJokerApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class JokeEndpoint {

    @ApiMethod(name = "makeMeLaugh")
    public JokeBean tellJoke() {
        final JokeBean response = new JokeBean();
        response.setJoke(JokeTeller.tellJoke());

        return response;
    }
}