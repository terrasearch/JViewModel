package com.github.terrasearch.jviewmodel.swing;

import org.jetbrains.annotations.Nullable;

/**
 * Interface for Parsing Error Handlers
 */
public interface IParseErrorListener {
    /**
     * Parsing threw an exception
     *
     * @param iae exception which was thrown, can be null
     */
    void onParseError(@Nullable IllegalArgumentException iae);

    /**
     * Parsing was successful
     */
    void onParseSuccess();
}
