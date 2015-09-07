package com.epam.education.XMLParsing.Utils;

import com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaFactory;
import org.apache.logging.log4j.Logger;
import org.xml.sax.*;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Utils for Parsers class
 */
public class ParserUtil {
    // валидация xml файла по схеме указанной явно в виде файла
    public static boolean validateXMLFile(String xmlFilePath, String xmlSchemaFilePath, Logger myLogger) {
        try {
            myLogger.info("Start validation file: " + xmlFilePath +
                    " by scheme file: " + xmlSchemaFilePath + " ...");

            SchemaFactory factory = new XMLSchemaFactory();
            Source schemaFile = new StreamSource(new File(xmlSchemaFilePath));
            Source xmlSource = new StreamSource(new File(xmlFilePath));
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlSource);

            myLogger.info("File is valid...");
        } catch (SAXException e) {
            myLogger.error(e.getMessage());
            myLogger.info("File: " + xmlFilePath + " is not valid...");
            return false;
        } catch (IOException e) {
            myLogger.error(e.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            myLogger.error(e.getMessage());
            return false;
        } catch (Error e) {
            e.printStackTrace();
            myLogger.error(e.getMessage());
            return false;
        }

        return true;
    }

    // метод преобразования файла XML в другой файл XML согласно указанного шаблона XSL
    public static void transformXMLByXSL(String xslFilePath, String xmlFilePath, String xmlTransformFilePath, Logger myLogger) {
        try {
            myLogger.info("Start transform file...");
            TransformerFactory tf =
                    TransformerFactory.newInstance();
            //установка используемого XSL-преобразования
            Transformer transformer =
                    tf.newTransformer(new StreamSource(xslFilePath));
            //установка исходного XML-документа и конечного XML-файла и процесс трансформации
            transformer.transform(
                    new StreamSource(xmlFilePath),
                    new StreamResult(xmlTransformFilePath));
            myLogger.info("End transform file...");
        } catch(TransformerException e) {
            e.printStackTrace();
            myLogger.error(e.getMessage());
        }
    }

}
