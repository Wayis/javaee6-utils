package com.github.wayis.framework.javaee.extensions.impl.config;

import com.github.wayis.framework.javaee.extensions.api.config.ConfigProperty;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Producer associated to the {@link com.github.wayis.framework.javaee.extensions.api.config.ConfigProperty} qualifier.<br/>
 * It manages:
 * <ul>
 * <li>String</li>
 * <li>Integer</li>
 * <li>Boolean</li>
 * </ul>
 * <p/>
 * It searches the property key in the declared bundle.
 */
@ApplicationScoped
public class ConfigPropertyProducer {

    /**
     * Producer for the {@link com.github.wayis.framework.javaee.extensions.api.config.ConfigProperty} qualifer for String value.
     *
     * @param injectionPoint The injection point.
     * @return The String value corresponding to the key from the declared bundle.
     */
    @Produces
    @Dependent
    @ConfigProperty
    public String getStringProperty(final InjectionPoint injectionPoint) {
        return getPropertyValue(injectionPoint);
    }

    /**
     * Producer for the {@link com.github.wayis.framework.javaee.extensions.api.config.ConfigProperty} qualifer for Integer value.
     *
     * @param injectionPoint The injection point.
     * @return The Integer value corresponding to the key from the declared bundle.
     */
    @Produces
    @Dependent
    @ConfigProperty
    public Integer getIntegerProperty(final InjectionPoint injectionPoint) {
        final String value = getPropertyValue(injectionPoint);
        if (value == null) {
            return null;
        }
        return Integer.parseInt(value);
    }

    /**
     * Producer for the {@link com.github.wayis.framework.javaee.extensions.api.config.ConfigProperty} qualifer for Boolean value.
     *
     * @param injectionPoint The injection point.
     * @return The Boolean value corresponding to the key from the declared bundle.
     */
    @Produces
    @Dependent
    @ConfigProperty
    public Boolean getBooleanProperty(final InjectionPoint injectionPoint) {
        final String value = getPropertyValue(injectionPoint);
        if (value == null) {
            return null;
        }
        return Boolean.parseBoolean(value);
    }

    /**
     * Gets a property value.<br/>
     * Retrieves the {@link com.github.wayis.framework.javaee.extensions.api.config.ConfigProperty} annotation from the injection point.<br/>
     * Searches the key in the declared bundle. The default bundle is a 'config.*' file.
     *
     * @param injectionPoint The injection point.
     * @return The default value if the key is missing or the key is not found in the bundle. Otherwise the found property value.
     * @throws java.util.MissingResourceException If the property is declared mandatory and is not found in the bundle.
     */
    private String getPropertyValue(final InjectionPoint injectionPoint) throws MissingResourceException {
        final ConfigProperty configProperty = injectionPoint.getAnnotated().getAnnotation(ConfigProperty.class);
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
