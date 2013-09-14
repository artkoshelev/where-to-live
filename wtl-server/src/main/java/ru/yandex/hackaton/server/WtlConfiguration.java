package ru.yandex.hackaton.server;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.codahale.dropwizard.Configuration;
import com.codahale.dropwizard.db.DataSourceFactory;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sergey Polovko
 */
public class WtlConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory databaseConfig = new DataSourceFactory();


    public DataSourceFactory getDatabaseConfig() {
        return databaseConfig;
    }
}
