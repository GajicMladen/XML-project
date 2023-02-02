package main.java.com.xws.a1document.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import main.java.com.xws.a1document.xml.model.TAdresa;
import main.java.com.xws.a1document.xml.model.TAutor;
import main.java.com.xws.a1document.xml.model.TAutorPreminuo;
import main.java.com.xws.a1document.xml.model.TAutorZiv;
import main.java.com.xws.a1document.xml.model.TFizickoLice;
import main.java.com.xws.a1document.xml.model.TLice;
import main.java.com.xws.a1document.xml.model.TPravnoLice;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "zavod",
    "zahtev"
})
@XmlRootElement(name = "obrazac_a_1")
public class ObrazacA1DTO {

    @XmlElement(required = true)
    protected ObrazacA1DTO .Zavod zavod;
    @XmlElement(required = true)
    protected ObrazacA1DTO .Zahtev zahtev;

    public ObrazacA1DTO .Zavod getZavod() {
        return zavod;
    }

    public void setZavod(ObrazacA1DTO .Zavod value) {
        this.zavod = value;
    }

    public ObrazacA1DTO .Zahtev getZahtev() {
        return zahtev;
    }

    public void setZahtev(ObrazacA1DTO .Zahtev value) {
        this.zahtev = value;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        //"podnosilac",
    	"fizickoLice",
    	"pravnoLice",
        "pseudonimZnakAutora",
        "punomocnik",
        "naslovAutorskogDela",
        "naslovDeloPrerade",
        "vrstaAutorskogDela",
        "formaZapisaAutorskogDela",
        //"podaciOAutoruNepodnosilac",
        "podnosilacAutor",
        "autorAnoniman",
        "autorZiv",
        "autorPreminuo",
        "autorskoDeloStvorenoRadnimOdnosom",
        "nacinKoriscenja",
        "prilozi",
        "brojPrijave",
        "datumPodnosenja"
    })
    public static class Zahtev {

        //@XmlElement(required = true)
        //protected ObrazacA1DTO .Zahtev.Podnosilac podnosilac;
    	@XmlElement(name = "fizicko_lice", required = true)
        protected TFizickoLice fizickoLice;
    	@XmlElement(name = "pravno_lice", required = true)
        protected TPravnoLice pravnoLice;
        @XmlElement(name = "pseudonim_znak_autora", required = true)
        protected ObrazacA1DTO .Zahtev.PseudonimZnakAutora pseudonimZnakAutora;
        @XmlElement(required = true)
        protected ObrazacA1DTO .Zahtev.Punomocnik punomocnik;
        @XmlElement(name = "naslov_autorskog_dela", required = true)
        protected ObrazacA1DTO .Zahtev.NaslovAutorskogDela naslovAutorskogDela;
        @XmlElement(name = "naslov_delo_prerade", required = true)
        protected ObrazacA1DTO .Zahtev.NaslovDeloPrerade naslovDeloPrerade;
        @XmlElement(name = "vrsta_autorskog_dela", required = true)
        protected ObrazacA1DTO .Zahtev.VrstaAutorskogDela vrstaAutorskogDela;
        @XmlElement(name = "forma_zapisa_autorskog_dela", required = true)
        protected ObrazacA1DTO .Zahtev.FormaZapisaAutorskogDela formaZapisaAutorskogDela;
        //@XmlElement(name = "podaci_o_autoru_nepodnosilac", required = true)
        //protected ObrazacA1DTO .Zahtev.PodaciOAutoruNepodnosilac podaciOAutoruNepodnosilac;
        @XmlElement(name = "podnosilac_autor", required = true)
        protected TAutor podnosilacAutor;
        @XmlElement(name = "autor_anoniman", required = true)
        protected TAutor autorAnoniman;
        @XmlElement(name = "autor_ziv", required = true)
        protected TAutorZiv autorZiv;
        @XmlElement(name = "autor_preminuo", required = true)
        protected TAutorPreminuo autorPreminuo;
        @XmlElement(name = "autorsko_delo_stvoreno_radnim_odnosom", required = true)
        protected ObrazacA1DTO .Zahtev.AutorskoDeloStvorenoRadnimOdnosom autorskoDeloStvorenoRadnimOdnosom;
        @XmlElement(name = "nacin_koriscenja", required = true)
        protected ObrazacA1DTO .Zahtev.NacinKoriscenja nacinKoriscenja;
        @XmlElement(required = true)
        protected ObrazacA1DTO .Zahtev.Prilozi prilozi;
        @XmlElement(name = "broj_prijave", required = true)
        protected ObrazacA1DTO .Zahtev.BrojPrijave brojPrijave;
        @XmlElement(name = "datum_podnosenja", required = true)
        protected ObrazacA1DTO .Zahtev.DatumPodnosenja datumPodnosenja;
        @XmlAttribute(name = "naslov")
        @XmlSchemaType(name = "anySimpleType")
        protected String naslov;

        /*public ObrazacA1DTO .Zahtev.Podnosilac getPodnosilac() {
            return podnosilac;
        }

        public void setPodnosilac(ObrazacA1DTO .Zahtev.Podnosilac value) {
            this.podnosilac = value;
        }*/
        
        public TFizickoLice getFizickoLice() {
			return fizickoLice;
		}

		public void setFizickoLice(TFizickoLice fizickoLice) {
			this.fizickoLice = fizickoLice;
		}

		public TPravnoLice getPravnoLice() {
			return pravnoLice;
		}

		public void setPravnoLice(TPravnoLice pravnoLice) {
			this.pravnoLice = pravnoLice;
		}

        public ObrazacA1DTO .Zahtev.PseudonimZnakAutora getPseudonimZnakAutora() {
            return pseudonimZnakAutora;
        }
        

		public void setPseudonimZnakAutora(ObrazacA1DTO .Zahtev.PseudonimZnakAutora value) {
            this.pseudonimZnakAutora = value;
        }

        public ObrazacA1DTO .Zahtev.Punomocnik getPunomocnik() {
            return punomocnik;
        }

        public void setPunomocnik(ObrazacA1DTO .Zahtev.Punomocnik value) {
            this.punomocnik = value;
        }

        public ObrazacA1DTO .Zahtev.NaslovAutorskogDela getNaslovAutorskogDela() {
            return naslovAutorskogDela;
        }

        public void setNaslovAutorskogDela(ObrazacA1DTO .Zahtev.NaslovAutorskogDela value) {
            this.naslovAutorskogDela = value;
        }

        public ObrazacA1DTO .Zahtev.NaslovDeloPrerade getNaslovDeloPrerade() {
            return naslovDeloPrerade;
        }

        public void setNaslovDeloPrerade(ObrazacA1DTO .Zahtev.NaslovDeloPrerade value) {
            this.naslovDeloPrerade = value;
        }

        public ObrazacA1DTO .Zahtev.VrstaAutorskogDela getVrstaAutorskogDela() {
            return vrstaAutorskogDela;
        }

        public void setVrstaAutorskogDela(ObrazacA1DTO .Zahtev.VrstaAutorskogDela value) {
            this.vrstaAutorskogDela = value;
        }

        public ObrazacA1DTO .Zahtev.FormaZapisaAutorskogDela getFormaZapisaAutorskogDela() {
            return formaZapisaAutorskogDela;
        }

        public void setFormaZapisaAutorskogDela(ObrazacA1DTO .Zahtev.FormaZapisaAutorskogDela value) {
            this.formaZapisaAutorskogDela = value;
        }

        /*public ObrazacA1DTO .Zahtev.PodaciOAutoruNepodnosilac getPodaciOAutoruNepodnosilac() {
            return podaciOAutoruNepodnosilac;
        }

        public void setPodaciOAutoruNepodnosilac(ObrazacA1DTO .Zahtev.PodaciOAutoruNepodnosilac value) {
            this.podaciOAutoruNepodnosilac = value;
        }*/
        
        public TAutor getPodnosilacAutor() {
			return podnosilacAutor;
		}

		public void setPodnosilacAutor(TAutor podnosilacAutor) {
			this.podnosilacAutor = podnosilacAutor;
		}

		public TAutor getAutorAnoniman() {
			return autorAnoniman;
		}

		public void setAutorAnoniman(TAutor autorAnoniman) {
			this.autorAnoniman = autorAnoniman;
		}

		public TAutorZiv getAutorZiv() {
			return autorZiv;
		}

		public void setAutorZiv(TAutorZiv autorZiv) {
			this.autorZiv = autorZiv;
		}

		public TAutorPreminuo getAutorPreminuo() {
			return autorPreminuo;
		}

		public void setAutorPreminuo(TAutorPreminuo autorPreminuo) {
			this.autorPreminuo = autorPreminuo;
		}
        
        public ObrazacA1DTO .Zahtev.AutorskoDeloStvorenoRadnimOdnosom getAutorskoDeloStvorenoRadnimOdnosom() {
            return autorskoDeloStvorenoRadnimOdnosom;
        }

		public void setAutorskoDeloStvorenoRadnimOdnosom(ObrazacA1DTO .Zahtev.AutorskoDeloStvorenoRadnimOdnosom value) {
            this.autorskoDeloStvorenoRadnimOdnosom = value;
        }

        public ObrazacA1DTO .Zahtev.NacinKoriscenja getNacinKoriscenja() {
            return nacinKoriscenja;
        }

        public void setNacinKoriscenja(ObrazacA1DTO .Zahtev.NacinKoriscenja value) {
            this.nacinKoriscenja = value;
        }

        public ObrazacA1DTO .Zahtev.Prilozi getPrilozi() {
            return prilozi;
        }

        public void setPrilozi(ObrazacA1DTO .Zahtev.Prilozi value) {
            this.prilozi = value;
        }

        public ObrazacA1DTO .Zahtev.BrojPrijave getBrojPrijave() {
            return brojPrijave;
        }

        public void setBrojPrijave(ObrazacA1DTO .Zahtev.BrojPrijave value) {
            this.brojPrijave = value;
        }

        public ObrazacA1DTO .Zahtev.DatumPodnosenja getDatumPodnosenja() {
            return datumPodnosenja;
        }

        public void setDatumPodnosenja(ObrazacA1DTO .Zahtev.DatumPodnosenja value) {
            this.datumPodnosenja = value;
        }

        public String getNaslov() {
            return naslov;
        }

        public void setNaslov(String value) {
            this.naslov = value;
        }

        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class AutorskoDeloStvorenoRadnimOdnosom {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "required")
            @XmlSchemaType(name = "anySimpleType")
            protected String required;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the required property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequired() {
                return required;
            }

            /**
             * Sets the value of the required property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequired(String value) {
                this.required = value;
            }

        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class BrojPrijave {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "required")
            @XmlSchemaType(name = "anySimpleType")
            protected String required;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the required property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequired() {
                return required;
            }

            /**
             * Sets the value of the required property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequired(String value) {
                this.required = value;
            }

        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class DatumPodnosenja {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "required")
            @XmlSchemaType(name = "anySimpleType")
            protected String required;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the required property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequired() {
                return required;
            }

            /**
             * Sets the value of the required property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequired(String value) {
                this.required = value;
            }

        }


 
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class FormaZapisaAutorskogDela {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "required")
            @XmlSchemaType(name = "anySimpleType")
            protected String required;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the required property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequired() {
                return required;
            }

            /**
             * Sets the value of the required property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequired(String value) {
                this.required = value;
            }

        }



        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class NacinKoriscenja {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "required")
            @XmlSchemaType(name = "anySimpleType")
            protected String required;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the required property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequired() {
                return required;
            }

            /**
             * Sets the value of the required property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequired(String value) {
                this.required = value;
            }

        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "naslov",
            "alternativniNaslov"
        })
        public static class NaslovAutorskogDela {

            @XmlElement(required = true)
            protected ObrazacA1DTO .Zahtev.NaslovAutorskogDela.Naslov naslov;
            @XmlElement(name = "alternativni_naslov", required = true)
            protected ObrazacA1DTO .Zahtev.NaslovAutorskogDela.AlternativniNaslov alternativniNaslov;

            /**
             * Gets the value of the naslov property.
             * 
             * @return
             *     possible object is
             *     {@link ObrazacA1DTO .Zahtev.NaslovAutorskogDela.Naslov }
             *     
             */
            public ObrazacA1DTO .Zahtev.NaslovAutorskogDela.Naslov getNaslov() {
                return naslov;
            }

            /**
             * Sets the value of the naslov property.
             * 
             * @param value
             *     allowed object is
             *     {@link ObrazacA1DTO .Zahtev.NaslovAutorskogDela.Naslov }
             *     
             */
            public void setNaslov(ObrazacA1DTO .Zahtev.NaslovAutorskogDela.Naslov value) {
                this.naslov = value;
            }

            /**
             * Gets the value of the alternativniNaslov property.
             * 
             * @return
             *     possible object is
             *     {@link ObrazacA1DTO .Zahtev.NaslovAutorskogDela.AlternativniNaslov }
             *     
             */
            public ObrazacA1DTO .Zahtev.NaslovAutorskogDela.AlternativniNaslov getAlternativniNaslov() {
                return alternativniNaslov;
            }

            /**
             * Sets the value of the alternativniNaslov property.
             * 
             * @param value
             *     allowed object is
             *     {@link ObrazacA1DTO .Zahtev.NaslovAutorskogDela.AlternativniNaslov }
             *     
             */
            public void setAlternativniNaslov(ObrazacA1DTO .Zahtev.NaslovAutorskogDela.AlternativniNaslov value) {
                this.alternativniNaslov = value;
            }


            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class AlternativniNaslov {

                @XmlValue
                protected String value;
                @XmlAttribute(name = "reuqired")
                @XmlSchemaType(name = "anySimpleType")
                protected String reuqired;

                /**
                 * Gets the value of the value property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the reuqired property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getReuqired() {
                    return reuqired;
                }

                /**
                 * Sets the value of the reuqired property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setReuqired(String value) {
                    this.reuqired = value;
                }

            }


            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class Naslov {

                @XmlValue
                protected String value;
                @XmlAttribute(name = "required")
                @XmlSchemaType(name = "anySimpleType")
                protected String required;

                /**
                 * Gets the value of the value property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the required property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getRequired() {
                    return required;
                }

                /**
                 * Sets the value of the required property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setRequired(String value) {
                    this.required = value;
                }

            }

        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "naslov",
            "autor"
        })
        public static class NaslovDeloPrerade {

            @XmlElement(required = true)
            protected String naslov;
            @XmlElement(required = true)
            protected TAutor autor;
            @XmlAttribute(name = "required")
            @XmlSchemaType(name = "anySimpleType")
            protected String required;

            /**
             * Gets the value of the naslov property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNaslov() {
                return naslov;
            }

            /**
             * Sets the value of the naslov property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNaslov(String value) {
                this.naslov = value;
            }

            /**
             * Gets the value of the autor property.
             * 
             * @return
             *     possible object is
             *     {@link TAutor }
             *     
             */
            public TAutor getAutor() {
                return autor;
            }

            /**
             * Sets the value of the autor property.
             * 
             * @param value
             *     allowed object is
             *     {@link TAutor }
             *     
             */
            public void setAutor(TAutor value) {
                this.autor = value;
            }

            /**
             * Gets the value of the required property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequired() {
                return required;
            }

            /**
             * Sets the value of the required property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequired(String value) {
                this.required = value;
            }

        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "autor"
        })
        public static class PodaciOAutoruNepodnosilac {

            @XmlElement(required = true)
            protected TAutor autor;
            //protected List<TAutor> autor;
            @XmlAttribute(name = "required")
            @XmlSchemaType(name = "anySimpleType")
            protected String required;

            /**
             * Gets the value of the autor property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the autor property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getAutor().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link TAutor }
             * 
             * 
             */
            public TAutor getAutor() {                
                return this.autor;
            }
            
            public void setAutor(TAutor value) {
            	this.autor = value;
            }

            /**
             * Gets the value of the required property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequired() {
                return required;
            }

            /**
             * Sets the value of the required property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequired(String value) {
                this.required = value;
            }

        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "lice"
        })
        public static class Podnosilac {

            @XmlElement(required = true)
            protected TLice lice;
            @XmlAttribute(name = "required")
            @XmlSchemaType(name = "anySimpleType")
            protected String required;

            /**
             * Gets the value of the lice property.
             * 
             * @return
             *     possible object is
             *     {@link TLice }
             *     
             */
            public TLice getLice() {
                return lice;
            }

            /**
             * Sets the value of the lice property.
             * 
             * @param value
             *     allowed object is
             *     {@link TLice }
             *     
             */
            public void setLice(TLice value) {
                this.lice = value;
            }

            /**
             * Gets the value of the required property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequired() {
                return required;
            }

            /**
             * Sets the value of the required property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequired(String value) {
                this.required = value;
            }

        }


 
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "opisAutorskogDela",
            "primerAutorskogDela"
        })
        public static class Prilozi {

            @XmlElement(name = "opis_autorskog_dela", required = true)
            protected String opisAutorskogDela;
            @XmlElement(name = "primer_autorskog_dela", required = true)
            protected String primerAutorskogDela;
            @XmlAttribute(name = "required")
            @XmlSchemaType(name = "anySimpleType")
            protected String required;

            /**
             * Gets the value of the opisAutorskogDela property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOpisAutorskogDela() {
                return opisAutorskogDela;
            }

            /**
             * Sets the value of the opisAutorskogDela property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOpisAutorskogDela(String value) {
                this.opisAutorskogDela = value;
            }

            /**
             * Gets the value of the primerAutorskogDela property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPrimerAutorskogDela() {
                return primerAutorskogDela;
            }

            /**
             * Sets the value of the primerAutorskogDela property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPrimerAutorskogDela(String value) {
                this.primerAutorskogDela = value;
            }

            /**
             * Gets the value of the required property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequired() {
                return required;
            }

            /**
             * Sets the value of the required property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequired(String value) {
                this.required = value;
            }

        }

       
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class PseudonimZnakAutora {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "required")
            @XmlSchemaType(name = "anySimpleType")
            protected String required;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the required property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequired() {
                return required;
            }

            /**
             * Sets the value of the required property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequired(String value) {
                this.required = value;
            }

        }


      
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "ime",
            "prezime",
            "adresa"
        })
        public static class Punomocnik {

            @XmlElement(required = true)
            protected String ime;
            @XmlElement(required = true)
            protected String prezime;
            @XmlElement(required = true)
            protected TAdresa adresa;
            @XmlAttribute(name = "required")
            @XmlSchemaType(name = "anySimpleType")
            protected String required;

            /**
             * Gets the value of the ime property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIme() {
                return ime;
            }

            /**
             * Sets the value of the ime property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIme(String value) {
                this.ime = value;
            }

            /**
             * Gets the value of the prezime property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPrezime() {
                return prezime;
            }

            /**
             * Sets the value of the prezime property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPrezime(String value) {
                this.prezime = value;
            }

            /**
             * Gets the value of the adresa property.
             * 
             * @return
             *     possible object is
             *     {@link TAdresa }
             *     
             */
            public TAdresa getAdresa() {
                return adresa;
            }

            /**
             * Sets the value of the adresa property.
             * 
             * @param value
             *     allowed object is
             *     {@link TAdresa }
             *     
             */
            public void setAdresa(TAdresa value) {
                this.adresa = value;
            }

            /**
             * Gets the value of the required property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequired() {
                return required;
            }

            /**
             * Sets the value of the required property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequired(String value) {
                this.required = value;
            }

        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class VrstaAutorskogDela {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "required")
            @XmlSchemaType(name = "anySimpleType")
            protected String required;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the required property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequired() {
                return required;
            }

            /**
             * Sets the value of the required property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequired(String value) {
                this.required = value;
            }

        }

    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "naziv",
        "adresa"
    })
    public static class Zavod {

        @XmlElement(required = true)
        protected String naziv;
        @XmlElement(required = true)
        protected TAdresa adresa;

        /**
         * Gets the value of the naziv property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNaziv() {
            return naziv;
        }

        /**
         * Sets the value of the naziv property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNaziv(String value) {
            this.naziv = value;
        }

        /**
         * Gets the value of the adresa property.
         * 
         * @return
         *     possible object is
         *     {@link TAdresa }
         *     
         */
        public TAdresa getAdresa() {
            return adresa;
        }

        /**
         * Sets the value of the adresa property.
         * 
         * @param value
         *     allowed object is
         *     {@link TAdresa }
         *     
         */
        public void setAdresa(TAdresa value) {
            this.adresa = value;
        }

    }

}

