package com.demo.guice.server.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import javax.inject.Inject;

import static org.junit.Assert.assertNotEquals;

/**
 * SessionManager Tester.
 *
 * @author <Authors name>
 * @since <pre>02/17/2019</pre>
 * @version 1.0
 */
public class SessionManagerTest {

    @Inject
    private SessionManager sessionManager;

    @Before
    public void before() throws Exception {
        Guice.createInjector(new ServerModule()).injectMembers(this);
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: getSessionId()
     *
     */
    @Test
    public void testGetSessionId() throws Exception {
        Long sessionId1 = sessionManager.getSessionId();
        Thread.sleep(1000);
        Long sessionId2 = sessionManager.getSessionId();
        assertNotEquals(sessionId2.longValue(), sessionId1.longValue());
    }
}
