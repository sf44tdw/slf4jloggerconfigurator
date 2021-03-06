/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggerconfigurator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;

/**
 *
 * @author normal
 */
public class LoggerConfiguratorTest {

    public LoggerConfiguratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCallerLogger method, of class LoggerConfigurator.
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testGetCallerLogger() throws ClassNotFoundException {
    	LoggerConfigurator LC= LoggerConfigurator.getlnstance();
        Logger result =LC.getCallerLogger();
        assertTrue(result!=null);
        final String va;
        final String vb;
        va=this.getClass().getSimpleName();
        vb=LC.getLastClassName();
        result.info("getCallerLogger ThisClassName:{} LastSetClassName:{}",va,vb);
        assertTrue(va.equals(vb));

    }

}
