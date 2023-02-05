//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.11 at 12:28:33 AM CET 
//


package com.example.ZigBackend.gen.Z1Classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="podnosilac" type="{http://www.tim777.rs/dokumentZ1}TLice"/&gt;
 *         &lt;element name="punomocnik" type="{http://www.tim777.rs/dokumentZ1}TLice"/&gt;
 *         &lt;element name="zajednicki_posrednik" type="{http://www.tim777.rs/dokumentZ1}TLice" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.tim777.rs/dokumentZ1}opis_ziga"/&gt;
 *         &lt;element ref="{http://www.tim777.rs/dokumentZ1}brojevi_klasa_robe_usluga"/&gt;
 *         &lt;element ref="{http://www.tim777.rs/dokumentZ1}zatrazeno_pravo_prvenstva"/&gt;
 *         &lt;element ref="{http://www.tim777.rs/dokumentZ1}placanje"/&gt;
 *         &lt;element ref="{http://www.tim777.rs/dokumentZ1}prilozi"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="broj_zahteva" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "podnosilac",
    "punomocnik",
    "zajednickiPosrednik",
    "opisZiga",
    "brojeviKlasaRobeUsluga",
    "zatrazenoPravoPrvenstva",
    "placanje",
    "prilozi",
        "status"
})
@XmlRootElement(name = "zahtev_za_priznanje_ziga")
public class ZahtevZaPriznanjeZiga {

    @XmlElement(required = true)
    protected TLice podnosilac;
    @XmlElement(required = true)
    protected TLice punomocnik;
    @XmlElement(name = "zajednickiPosrednik")
    protected TLice zajednickiPosrednik;
    @XmlElement(name = "opisZiga", required = true)
    protected OpisZiga opisZiga;
    @XmlElement(name = "brojeviKlasaRobeUsluga", required = true)
    protected BrojeviKlasaRobeUsluga brojeviKlasaRobeUsluga;
    @XmlElement(name = "zatrazenoPravoPrvenstva", required = true)
    protected ZatrazenoPravoPrvenstva zatrazenoPravoPrvenstva;
    @XmlElement(required = true)
    protected Placanje placanje;
    @XmlElement(required = true)
    protected Prilozi prilozi;

    @XmlElement( name = "status")
    protected EStatusResenja status;
    @XmlAttribute(name = "brojZahteva")
    protected String brojZahteva;
    @XmlAttribute(name = "datumZahteva")
    protected String datumZahteva;


    public EStatusResenja getStatus() {
        return status;
    }

    public void setStatus(EStatusResenja status) {
        this.status = status;
    }

    public void setDatumZahteva(String datumZahteva) {
        this.datumZahteva = datumZahteva;
    }

    /**
     * Gets the value of the podnosilac property.
     * 
     * @return
     *     possible object is
     *     {@link TLice }
     *     
     */
    public TLice getPodnosilac() {
        return podnosilac;
    }

    /**
     * Sets the value of the podnosilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLice }
     *     
     */
    public void setPodnosilac(TLice value) {
        this.podnosilac = value;
    }

    /**
     * Gets the value of the punomocnik property.
     * 
     * @return
     *     possible object is
     *     {@link TLice }
     *     
     */
    public TLice getPunomocnik() {
        return punomocnik;
    }

    /**
     * Sets the value of the punomocnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLice }
     *     
     */
    public void setPunomocnik(TLice value) {
        this.punomocnik = value;
    }

    /**
     * Gets the value of the zajednickiPosrednik property.
     * 
     * @return
     *     possible object is
     *     {@link TLice }
     *     
     */
    public TLice getZajednickiPosrednik() {
        return zajednickiPosrednik;
    }

    /**
     * Sets the value of the zajednickiPosrednik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLice }
     *     
     */
    public void setZajednickiPosrednik(TLice value) {
        this.zajednickiPosrednik = value;
    }

    /**
     * Gets the value of the opisZiga property.
     * 
     * @return
     *     possible object is
     *     {@link OpisZiga }
     *     
     */
    public OpisZiga getOpisZiga() {
        return opisZiga;
    }

    /**
     * Sets the value of the opisZiga property.
     * 
     * @param value
     *     allowed object is
     *     {@link OpisZiga }
     *     
     */
    public void setOpisZiga(OpisZiga value) {
        this.opisZiga = value;
    }

    /**
     * Gets the value of the brojeviKlasaRobeUsluga property.
     * 
     * @return
     *     possible object is
     *     {@link BrojeviKlasaRobeUsluga }
     *     
     */
    public BrojeviKlasaRobeUsluga getBrojeviKlasaRobeUsluga() {
        return brojeviKlasaRobeUsluga;
    }

    /**
     * Sets the value of the brojeviKlasaRobeUsluga property.
     *
     * @param value
     *     allowed object is
     *     {@link BrojeviKlasaRobeUsluga }
     *
     */
    public void setBrojeviKlasaRobeUsluga(BrojeviKlasaRobeUsluga value) {
        this.brojeviKlasaRobeUsluga = value;
    }

    /**
     * Gets the value of the zatrazenoPravoPrvenstva property.
     * 
     * @return
     *     possible object is
     *     {@link ZatrazenoPravoPrvenstva }
     *     
     */
    public ZatrazenoPravoPrvenstva getZatrazenoPravoPrvenstva() {
        return zatrazenoPravoPrvenstva;
    }

    /**
     * Sets the value of the zatrazenoPravoPrvenstva property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZatrazenoPravoPrvenstva }
     *     
     */
    public void setZatrazenoPravoPrvenstva(ZatrazenoPravoPrvenstva value) {
        this.zatrazenoPravoPrvenstva = value;
    }

    /**
     * Gets the value of the placanje property.
     * 
     * @return
     *     possible object is
     *     {@link Placanje }
     *     
     */
    public Placanje getPlacanje() {
        return placanje;
    }

    /**
     * Sets the value of the placanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Placanje }
     *     
     */
    public void setPlacanje(Placanje value) {
        this.placanje = value;
    }

    /**
     * Gets the value of the prilozi property.
     * 
     * @return
     *     possible object is
     *     {@link Prilozi }
     *     
     */
    public Prilozi getPrilozi() {
        return prilozi;
    }

    /**
     * Sets the value of the prilozi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prilozi }
     *     
     */
    public void setPrilozi(Prilozi value) {
        this.prilozi = value;
    }

    /**
     * Gets the value of the brojZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojZahteva() {
        return brojZahteva;
    }

    /**
     * Gets the value of the datumZahteva property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDatumZahteva() {
        return datumZahteva;
    }

    /**
     * Sets the value of the brojZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojZahteva(String value) {
        this.brojZahteva = value;
    }

    /**
     * Sets the value of the datumZahteva property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDatumZahtevaZahteva(String value) {
        this.datumZahteva = value;
    }

    @Override
    public String toString() {
        return "ZahtevZaPriznanjeZiga{" +
                "podnosilac=" + podnosilac +
                ", punomocnik=" + punomocnik +
                ", zajednickiPosrednik=" + zajednickiPosrednik +
                ", opisZiga=" + opisZiga +
                ", brojeviKlasaRobeUsluga=" + brojeviKlasaRobeUsluga +
                ", zatrazenoPravoPrvenstva=" + zatrazenoPravoPrvenstva +
                ", placanje=" + placanje +
                ", prilozi=" + prilozi +
                ", datumZahteva=" + datumZahteva +
                ", brojZahteva='" + brojZahteva + '\'' +
                '}';
    }
}
