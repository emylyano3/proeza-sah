package proeza.sah.desktop.core;

import com.guiBuilder.api.component.application.exception.ConfigurationNotFoundException;

public class Main {

    private static final String SPTRING_CONFIG_FILE = "/spring-config.xml";

    public static void main(String... args) throws ConfigurationNotFoundException {
        MainApp.launch(SPTRING_CONFIG_FILE);
    }
}