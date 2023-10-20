package org.eclipse.jdt.annotation;

import static java.lang.annotation.ElementType.TYPE_USE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 'placeholder' for Eclipse-style Nullable annotation -- used because Lombok
 * sometimes insists on generating these regardless of settings: https://github.com/projectlombok/lombok/issues/3122
 * <p>
 * {@link javax.annotation.Nullable} is expected to be used instead.
 *
 * @author Sergey Olefir
 * 
 * @deprecated only exists for compatibility with new Lombok which insists on generating these;
 * 		use {@link javax.annotation.Nullable} instead
 */
@Deprecated
@Target({ TYPE_USE, ElementType.PARAMETER })
public @interface Nullable {
	// marker annotation with no members
}
