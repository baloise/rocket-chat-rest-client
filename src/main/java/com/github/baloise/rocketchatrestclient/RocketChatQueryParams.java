package com.github.baloise.rocketchatrestclient;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains the keys and values for the query string.
 *
 * @author Bradley Hilton (graywolf336)
 * @since 0.1.0
 * @version 0.0.1
 */
public class RocketChatQueryParams extends HashMap<String, String> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2303728479578621534L;

	//private HashMap<String, String> queryParams;

    /** Initializes an empty query parameters object. */
    public RocketChatQueryParams() {
        super();
    }

    /**
     * Initializes query parameter object with one parameter already added.
     *
     * @param param the query name
     * @param value the value of the parameter
     */
    public RocketChatQueryParams(String param, String value) {
        super.put(param, value);
    }

    /**
     * Adds a query parameter to the list.
     *
     * @param param the query name
     * @param value the value of the parameter
     * @return the instance of the {@link RocketChatQueryParams}
     */
    public RocketChatQueryParams add(String param, String value) {
        super.put(param, value);
        return this;
    }

    /**
     * Adds all of the provided query parameters to the list.
     *
     * @param params the query parameters
     * @return the instance of the {@link RocketChatQueryParams}
     */
    public RocketChatQueryParams addAll(Map<? extends String, ? extends String> params) {
        super.putAll(params);
        return this;
    }
}
