package ru.yandex.hackaton.server;

import com.codahale.dropwizard.Application;
import com.codahale.dropwizard.setup.Bootstrap;
import com.codahale.dropwizard.setup.Environment;
import com.hubspot.dropwizard.guice.GuiceBundle;

import ru.yandex.hackaton.server.cli.JoinData;
import ru.yandex.hackaton.server.db.DbBundle;import ru.yandex.hackaton.server.db.DbModule;

/**
 * @author Sergey Polovko
 */
public class WtlApplication extends Application<WtlConfiguration> {

    public static void main(String[] args) throws Exception {
        new WtlApplication().run(args);
    }

    @Override
    public String getName() {
        return "wtl-server";
    }

    @Override
    public void initialize(Bootstrap<WtlConfiguration> bootstrap) {
        DbBundle dbBundle = new DbBundle();
        bootstrap.addBundle(dbBundle);

        GuiceBundle<WtlConfiguration> guiceBundle = GuiceBundle.<WtlConfiguration>newBuilder()
                .addModule(new DbModule(dbBundle))
                .setConfigClass(getConfigurationClass())
                .enableAutoConfig(getClass().getPackage().getName())
                .build();
        bootstrap.addBundle(guiceBundle);

        bootstrap.addCommand(new JoinData());
    }

    @Override
    public void run(WtlConfiguration configuration, Environment environment) throws Exception {
    }
}
