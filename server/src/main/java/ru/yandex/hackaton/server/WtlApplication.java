package ru.yandex.hackaton.server;

import com.codahale.dropwizard.Application;
import com.codahale.dropwizard.cli.Command;
import com.codahale.dropwizard.setup.Bootstrap;
import com.codahale.dropwizard.setup.Environment;
import com.google.common.collect.ImmutableList;
import com.google.inject.Injector;
import com.hubspot.dropwizard.guice.GuiceBundle;

import ru.yandex.hackaton.server.cli.JoinData;
import ru.yandex.hackaton.server.cli.LoadDistrictsCoordinates;
import ru.yandex.hackaton.server.db.DbBundle;
import ru.yandex.hackaton.server.db.DbModule;

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

    private ImmutableList<Command> commands;
    private Injector injector;

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
        bootstrap.addCommand(new JoinData(this));
        bootstrap.addCommand(new LoadDistrictsCoordinates(this));

        commands = bootstrap.getCommands();
        injector = guiceBundle.getInjector();
    }

    @Override
    public void run(WtlConfiguration configuration, Environment environment) throws Exception {
        for (Command command : commands) {
            injector.injectMembers(command);
        }
    }
}
