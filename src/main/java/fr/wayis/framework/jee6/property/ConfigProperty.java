package fr.wayis.framework.jee6.property;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Qualifier for Configuration properties.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
public @interface ConfigProperty {

    @Nonbinding
    public String bundle() default "deploy";

    @Nonbinding
    public String key() default "";

    @Nonbinding
    public boolean mandatory() default false;

    @Nonbinding
    public String defaultValue() default "";
}
