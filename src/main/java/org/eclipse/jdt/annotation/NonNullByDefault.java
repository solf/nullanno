/*******************************************************************************
 * Copyright (c) 2011, 2014 Stephan Herrmann and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Herrmann - initial API and implementation
 *     IBM Corporation - bug fixes
 *******************************************************************************/
package org.eclipse.jdt.annotation;

import java.lang.annotation.ElementType;

import static org.eclipse.jdt.annotation.DefaultLocation.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Nonnull;
 
/**
 * Applying this annotation to a declaration has the effect that type references,
 * which are contained in the declaration, and for which a null annotation is otherwise lacking,
 * should be considered as {@link Nonnull @NonNull}.
 * <dl>
 * <dt>Locations</dt>
 * <dd>This annotation is permitted for these declarations:
 * {@link ElementType#PACKAGE PACKAGE}, {@link ElementType#TYPE TYPE}, 
 * {@link ElementType#METHOD METHOD}, {@link ElementType#CONSTRUCTOR CONSTRUCTOR},
 * {@link ElementType#FIELD FIELD}, {@link ElementType#LOCAL_VARIABLE LOCAL_VARIABLE}.</dd>
 * <dt>Fine tuning</dt>
 * <dd>The exact effect is further controlled by the attribute {@link #value}, specifying what 
 * kinds of locations within the given declaration will be affected. See {@link DefaultLocation}
 * for the meaning of the available values.</dd>
 * <dt>Nested defaults</dt>
 * <dd>If this annotation is applied to a declaration that is already affected by the same
 * annotation at an enclosing scope, the inner annotation <em>replaces</em> the effect of the
 * outer annotation for the scope of the inner declaration.</dd>
 * <dt>Canceling a default</dt>
 * <dd>In particular, specifying an empty value (<code>{}</code>) for the {@link #value}
 * attribute has the effect of canceling any null defaults that might be defined for any
 * enclosing scope.</dd>
 * </dl>
 * <p>
 * Note that for applying an annotation to a package, a file by the name
 * <code>package-info.java</code> is used.
 * </p>
 * <p>
 * <b>Note:</b> Since org.eclipse.jdt.annotation 2.0.0, this annotation also applies to field and local variable declarations.
 * For the old API, see
 * <a href="http://help.eclipse.org/kepler/topic/org.eclipse.jdt.doc.isv/reference/api/org/eclipse/jdt/annotation/NonNullByDefault.html">
 * <code>@NonNullByDefault</code> in 1.1.0</a>.
 * </p>
 * <p>
 * 2021-07-09 Sergey Olefir NOTES: for IntelliJ IDEA support of default non-null
 * annotations see: https://youtrack.jetbrains.com/issue/IDEA-144920#focus=Comments-27-3949320.0-0
 * </p>
 * <p>
 * Eclipse appears to give priority to TypeQualifierDefault annotation if present
 * and that annotation doesn't appear to allow specifying all of the possible
 * options (e.g. TYPE_ARGUMENT and ARRAY_CONTENTS are impossible + others).
 * </p>
 * <p>
 * So it appears the only way to have proper default-non-null support across
 * Eclipse & IDEA is to use org.eclipse.jdt.annotation.NonNullByDefault
 * full-qualified annotation name. 
 * </p>
 * @since 1.0
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.PACKAGE, ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.LOCAL_VARIABLE })
public @interface NonNullByDefault {
	/**
	 * Specifies the set of locations within the annotated declaration that should be affected by the nonnull default.
	 * @return the locations, or an empty array to cancel any null defaults from enclosing scopes
	 * @since 2.0
	 */
	DefaultLocation[] value() default { PARAMETER, RETURN_TYPE, FIELD, TYPE_BOUND, TYPE_ARGUMENT, ARRAY_CONTENTS }; // Sergey Olefir: added ARRAY_CONTENTS 
}
