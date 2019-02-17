package com.demo.guice.server.impl;

import com.google.inject.Provider;
import javax.inject.Inject;

/**
 * SessionManager
 * @author xiao1
 * @date 2019/2/17
 */
public class SessionManager {

    private final Provider<Long> sessionIdProvider;

    @Inject
    public SessionManager(@SessionId Provider<Long> sessionIdProvider) {
        this.sessionIdProvider = sessionIdProvider;
    }

    public Long getSessionId() {
        return sessionIdProvider.get();
    }
}
