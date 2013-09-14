package ru.yandex.hackaton.server.db;

import com.codahale.dropwizard.db.DataSourceFactory;
import com.codahale.dropwizard.hibernate.HibernateBundle;
import com.codahale.dropwizard.hibernate.SessionFactoryFactory;
import com.google.common.collect.ImmutableList;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import ru.yandex.hackaton.server.WtlConfiguration;
import ru.yandex.hackaton.server.db.model.BaseModel;

/**
 * @author Sergey Polovko
 */
public class DbBundle extends HibernateBundle<WtlConfiguration> implements SessionFactoryHolder {

    public DbBundle() {
        super(modelsFrom("ru.yandex.hackaton.server.db.model"), new SessionFactoryFactory());
    }

    @Override
    public DataSourceFactory getDataSourceFactory(WtlConfiguration configuration) {
        return configuration.getDatabaseConfig();
    }

    @SuppressWarnings("unchecked")
    private static ImmutableList<Class<?>> modelsFrom(String... basePackages) {
        Reflections reflections = buildReflections(basePackages);
        ImmutableList.Builder<Class<?>> modelsList = ImmutableList.builder();
        for (Class<?> model : reflections.getSubTypesOf(BaseModel.class)) {
            modelsList.add(model);
        }
        return modelsList.build();
    }

    private static Reflections buildReflections(String... basePackages) {
        ConfigurationBuilder configBuilder = new ConfigurationBuilder();
        FilterBuilder filterBuilder = new FilterBuilder();

        for (String basePkg : basePackages) {
            configBuilder.addUrls(ClasspathHelper.forPackage(basePkg));
            filterBuilder.include(FilterBuilder.prefix(basePkg));
        }

        configBuilder.filterInputsBy(filterBuilder).setScanners(new SubTypesScanner(), new TypeAnnotationsScanner());
        return new Reflections(configBuilder);
    }
}
