package com.ingsoft.url.gudbox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

/**
 * Created by iiscancinos on 30/09/2016.
 */
@RunWith(RobolectricTestRunner.class)
public class API_Connection_Test {
    @Test
    public void evaluateConnection() throws Exception {
        _API_Connection api =  new _API_Connection( "api/GetSeeds");
        api.execute();
    }
}
