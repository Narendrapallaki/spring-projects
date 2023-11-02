package com.loggerfile.LogController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loggerfile.LoggerFileApplication;


@RestController
public class LogController {
	// 450
	 public Logger logger = (Logger) LoggerFactory.getLogger(LoggerFileApplication.class);
        
	   @RequestMapping("/logFile")
	    public void logFile() {
	    	
	        logger.debug("It is a debug ");
	        logger.info(" info message");
	        logger.warn("it is a warning message");
	        logger.error("XX error XX");
			
	    }

}
