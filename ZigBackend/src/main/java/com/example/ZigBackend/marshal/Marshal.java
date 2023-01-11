package com.example.ZigBackend.marshal;

import com.example.ZigBackend.gen.Z1Classes.ZahtevZaPriznanjeZiga;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static com.example.ZigBackend.utils.Utils.getJAXBContext;

public class Marshal {

    public static OutputStream marshalZig(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga) {
        try {
            JAXBContext context = getJAXBContext();
            OutputStream os = new ByteArrayOutputStream();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zahtevZaPriznanjeZiga, os);
            return os;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
