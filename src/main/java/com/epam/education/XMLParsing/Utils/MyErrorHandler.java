package com.epam.education.XMLParsing.Utils;

import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

/**
 * SAXParse Error class
 */
public class MyErrorHandler implements ErrorHandler {
        private Logger logger;

        public MyErrorHandler(Logger myLogger) {
            this.logger = myLogger;
        }

        public void warning(SAXParseException e) {
            logger.warn(getLineAddress(e) + " - " + e.getMessage());
        }

        public void error(SAXParseException e) {
            logger.error(getLineAddress(e) + " - " + e.getMessage());
        }

        public void fatalError(SAXParseException e) {
            logger.fatal(getLineAddress(e) + " - " + e.getMessage());
        }

        private String getLineAddress(SAXParseException e) {
            return e.getLineNumber() + " : " + e.getColumnNumber();
        }
}
