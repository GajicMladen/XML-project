package com.example.ZigBackend.utils;

import com.example.ZigBackend.gen.Z1Classes.ZahtevZaPriznanjeZiga;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;

public class Utils {

    public static JAXBContext getJAXBContext() throws JAXBException {
        return JAXBContext.newInstance("com.example.ZigBackend.gen.Z1Classes");
    }
    public static ZahtevZaPriznanjeZiga getTestZahtevZaPrizanjeZiga() throws FileNotFoundException, JAXBException {

        JAXBContext context = getJAXBContext();
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = ResourceUtils.getFile("classpath:Z1.xml");
        return (ZahtevZaPriznanjeZiga) unmarshaller.unmarshal(file);

    }
    public static String formatNameOfRequestForZig(String name, String extension) {
        StringBuilder returnSeparatedWithUnderscore= new StringBuilder();
        for (String word: name.split("/")) {
            if (returnSeparatedWithUnderscore.toString().equals("")) {
                returnSeparatedWithUnderscore = new StringBuilder(word);
            } else {
                returnSeparatedWithUnderscore.append("-").append(word);
            }
        }
        returnSeparatedWithUnderscore.append(extension);
        return returnSeparatedWithUnderscore.toString();
    }


}
