package fr.wayis.framework.javaee.property;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Qualifier for Configuration properties.<br/>
 * The key is searched into the bundle file. Default bundle is 'config.*'.<br/>
 * If the property is declared mandatory and is not found in the bundle, a {@link java.util.MissingResourceException} is thrown during the deployment.<br/>
 * If the key is missing or not found in the bundle file, the default value is returned.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface ConfigProperty {

    /**
     * The bundle where is declared the property.<br/>
     * Default value: 'config'.
     */
    @Nonbinding
    public String bundle() default "config";

    /**
     * The property key.<br/>
     * Default value: ''.
     */
    @Nonbinding
    public String key() default "";

    /**
     * Is the property mandatory ?<br/>
     * Default value: false.
     */
    @Nonbinding
    public boolean mandatory() default false;

    /**
     * The default value to use if no property key found.<br/>
     * Default value: ''.
     */
    @Nonbinding
    public String defaultValue() default "";
}
