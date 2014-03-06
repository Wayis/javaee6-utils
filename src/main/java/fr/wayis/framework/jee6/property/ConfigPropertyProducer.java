package fr.wayis.framework.jee6.property;

import org.apache.commons.lang3.StringUtils;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Producer associated to the {@link ConfigProperty} qualifier.
 */
public class ConfigPropertyProducer {

    @Produces
    @ConfigProperty
    public String getStringProperty(final InjectionPoint p) {
        return getPropertyValue(p);
    }

    @Produces
    @ConfigProperty
    public Integer getIntegerProperty(final InjectionPoint p) {
        final String value = getPropertyValue(p);
        if (value == null) {
            return null;
        }
        return Integer.parseInt(value);
    }

    @Produces
    @ConfigProperty
    public Boolean getBooleanProperty(final InjectionPoint p) {
        final String value = getPropertyValue(p);
        if (value == null) {
            return null;
        }
        return Boolean.parseBoolean(value);
    }

    private String getPropertyValue(final InjectionPoint p) {
        final ConfigProperty configProperty = p.getAnnotated().getAnnotation(ConfigProperty.class);
        final String propertyKey = configProperty.key();
        final String propertyBundle = configProperty.bundle();

        if (StringUtils.isEmpty(propertyKey)) {
            return configProperty.defaultValue();
        }

        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(propertyBundle);
            return resourceBundle.getString(propertyKey);

        } catch (MissingResourceException e) {
            if (configProperty.mandatory()) {
                throw e;
            } else {
                return configProperty.defaultValue();
            }
        }
    }
}
