package app.easylink.shuterstockimages.di.qualifier;


import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * You can use qualifiers to provide various implementations of a particular bean
 * type. A qualifier is an annotation that you apply to a bean. A qualifier type is a
 * Java annotation defined as @Target({METHOD, FIELD, PARAMETER, TYPE}) and @Retention(RUNTIME).
 *
 * For example, you could declare an @Informal qualifier type and apply it to another class
 * that extends the Greeting class. To declare this qualifier type, you would use the following code:
 */
@Qualifier
@Retention(RUNTIME) public @interface ActivityContext {
    /**
     * RUNTIME
     * Added in API level 1
     * public static final RetentionPolicy RUNTIME
     * Annotations are to be recorded in the class file by the compiler and retained by the VM at run time, so they may be read reflectively.
     */

}
