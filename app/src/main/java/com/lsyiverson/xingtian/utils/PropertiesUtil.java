package com.lsyiverson.xingtian.utils;

import android.content.Context;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.IOException;
import java.util.List;

import java8.util.stream.StreamSupport;

import static java8.util.stream.Collectors.toList;

public class PropertiesUtil {
    private static final String ENV_CONFIG_PROPERTIES = "env_config.properties";
    private static final String BASE_CONFIG_PROPERTIES = "base_config.properties";

    private static Configuration configuration;

    public static void init(Context context) {
        try {
            String[] list = context.getAssets().list("");

            List<String> properties = StreamSupport.of(list)
                .filter(s -> s.endsWith(".properties"))
                .collect(toList());

            CompositeConfiguration compositeConfiguration = new CompositeConfiguration();

            // priority 1: if env_config.properties is existed
            // load env_config.properties defined in environment module.
            if (properties.contains(ENV_CONFIG_PROPERTIES)) {
                PropertiesConfiguration envConfig = new PropertiesConfiguration();
                envConfig.load(context.getAssets().open(ENV_CONFIG_PROPERTIES));
                compositeConfiguration.addConfiguration(envConfig);
            }

            // priority 2: load default base_config.properties
            PropertiesConfiguration baseConfig = new PropertiesConfiguration();
            baseConfig.load(context.getAssets().open(BASE_CONFIG_PROPERTIES));
            compositeConfiguration.addConfiguration(baseConfig);

            configuration = compositeConfiguration;
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String key) {
        return configuration.getString(key);
    }

    public static int getInt(String key) {
        return configuration.getInt(key);
    }

    public static boolean getBoolean(String key) {
        return configuration.getBoolean(key);
    }
}
