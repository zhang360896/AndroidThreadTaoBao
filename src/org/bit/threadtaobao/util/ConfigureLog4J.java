package org.bit.threadtaobao.util;

import java.io.File;

import org.apache.log4j.Level;

import android.os.Environment;
import de.mindpipe.android.logging.log4j.LogConfigurator;
/**
 * Call {@link #configure()}} from your application's activity.
 */
public class ConfigureLog4J {
    public static void configure() {
    	final LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(Environment.getExternalStorageDirectory()
            + File.separator + "ThreadTaobao" + File.separator + "logs"
            + File.separator + "devlog.txt");
		logConfigurator.setRootLevel(Level.TRACE);
		logConfigurator.setLevel("org.apache", Level.ERROR);
		logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
		logConfigurator.setMaxFileSize(1024 * 1024 * 5);
		logConfigurator.setImmediateFlush(true);
		logConfigurator.configure();
	}
}