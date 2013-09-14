package ru.yandex.hackaton.server.cli;

import com.codahale.dropwizard.cli.ConfiguredCommand;
import com.codahale.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;

import ru.yandex.hackaton.server.WtlConfiguration;

/**
 * @author Sergey Polovko
 */
public class JoinData extends ConfiguredCommand<WtlConfiguration> {

    public JoinData() {
        super("join-data", "Joins loaded data with districts");
    }

    @Override
    protected void run(Bootstrap bootstrap, Namespace namespace, WtlConfiguration configuration) throws Exception {
        System.out.println("It works!");
    }
}
