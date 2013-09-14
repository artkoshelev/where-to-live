package ru.yandex.hackaton.server;

import com.codahale.dropwizard.Application;
import com.codahale.dropwizard.setup.Bootstrap;
import com.codahale.dropwizard.setup.Environment;

import ru.yandex.hackaton.server.cli.JoinData;

/**
 * @author Sergey Polovko
 */
public class WtlApplication extends Application<WtlConfiguration> {

    public static void main(String[] args) throws Exception {
        new WtlApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<WtlConfiguration> bootstrap) {
        bootstrap.addCommand(new JoinData());
    }

    @Override
    public void run(WtlConfiguration configuration, Environment environment) throws Exception {
    }
}
