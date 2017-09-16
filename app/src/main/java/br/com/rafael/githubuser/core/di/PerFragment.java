package br.com.rafael.githubuser.core.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the fragment to be memorized in the
 * corret component
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
