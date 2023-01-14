<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:b="http://www.ftn.uns.ac.rs/xpath/examples" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Zahtev za izdavanje patenta</title>
                <style type="text/css">body {
						padding: 30px;
						font-family: arial;
					}
                    table, th, td {
                        padding: 8px;
                        border: 2px solid black;
  						border-collapse: collapse;
  						font-size: 16px;
                    }
                    .content-data {
                    	font-size:26px;
                    	text-align:center
                    }
                    .title {
                    	font-size: 48px; 
                    	text-align: center; 
                    	margin-top: 20px;
                    }</style>
            </head>
            <body>
                <div>
                    <table table-layout="fixed" width="30%">
                        <tbody>
                            <tr style="text-align: center">
                                <td colspan="2">Popunjava zavod</td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    Broj prijave
                                    <div class="content-data">
                                        <xsl:value-of select="//podaci_zavod/broj_prijave" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Datum prijema
                                    <div class="content-data">
                                        <xsl:value-of select="//podaci_zavod/datum_prijema" />
                                    </div>
                                </td>
                                <td>
                                    Priznati datum podnošenja
                                    <div class="content-data">
                                        <xsl:value-of select="//podaci_zavod/datum_podnosenja" />
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div style="font-size: 16px">Republika Srbija</div>
                    <div style="font-size: 16px">Zavod za intelektualnu svojinu</div>
                    <div style="font-size: 16px">Kneginje Ljubice broj 5</div>
                    <div style="font-size: 16px">11000 Beograd</div>
                </div>
                <div class="title">ZAHTEV ZA PRIZNANJE PATENTA</div>
                <div style="margin-top: 30px;">
                    <table table-layout="fixed" width="100%" style="margin-left: auto; margin-right: auto;">
                        <tbody>
                            <tr>
                                <td colspan="3">
                                    <div>Polje broj I - NAZIV PRONALASKA</div>
                                    <div style="margin-top: 20px">
                                        <span style="font-size: 14px">Na srpskom:</span>
                                        <span class="content-data">
                                            <xsl:value-of select="//naziv[@lang='sr']" />
                                        </span>
                                    </div>
                                    <div>
                                        <span style="font-size: 14px">Na engleskom:</span>
                                        <span class="content-data">
                                            <xsl:value-of select="//naziv[@lang='en']" />
                                        </span>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" style="border-bottom: 1px solid black">
                                    <div>
                                        Polje broj II - PODNOSILAC PRIJAVE &#160;&#160;&#160;
                                        <xsl:variable name="pronalazac" select="//podnosilac_pronalazac = true()" />
                                        <xsl:if test="$pronalazac">
                                            <span style="font-size: 16px; color: darkgreen">Podnosilac JESTE pronalazač</span>
                                        </xsl:if>
                                        <xsl:if test="not($pronalazac)">
                                            <span style="font-size: 16px; color: darkred">Podnosilac NIJE pronalazač</span>
                                        </xsl:if>
                                    </div>
                                </td>
                            </tr>
                            <tr style="vertical-align: text-top">
                                <td style="border: 1px solid black">
                                    <xsl:if test="//podnosilac_prijave/fizicko_lice">
                                        Ime i prezime:
                                        <div class="content-data">
                                            <xsl:value-of select="//podnosilac_prijave/fizicko_lice/ime" />  &#160;
                                            <xsl:value-of select="//podnosilac_prijave/fizicko_lice/prezime" />
                                        </div>
                                    </xsl:if>
                                    <xsl:if test="//podnosilac_prijave/poslovno_ime">
                                        Poslovno ime:
                                        <div class="content-data">
                                            <xsl:value-of select="//podnosilac_prijave/poslovno_ime" />
                                        </div>
                                    </xsl:if>
                                </td>
                                <td style="border: 1px solid black">
                                    <div>
                                        Država:
                                        <span class="content-data">
                                            <xsl:value-of select="//podnosilac_prijave//drzava" />
                                        </span>
                                    </div>
                                    <div>
                                        Mesto:
                                        <span class="content-data">
                                            <xsl:value-of select="//podnosilac_prijave//mesto" />
                                        </span>
                                    </div>
                                    <div>
                                        Ulica i broj:
                                        <span class="content-data">
                                            <xsl:value-of select="//podnosilac_prijave//ulica" /> &#160;
                                            <xsl:value-of select="//podnosilac_prijave//broj" />
                                        </span>
                                    </div>
                                    <div>
                                        Poštanski broj:
                                        <span class="content-data">
                                            <xsl:value-of select="//podnosilac_prijave//postanski_broj" />
                                        </span>
                                    </div>
                                </td>
                                <td style="border: 1px solid black">
                                    <div>
                                        Broj telefona:
                                        <span class="content-data">
                                            <xsl:value-of select="//podnosilac_prijave//broj_telefona" />
                                        </span>
                                    </div>
                                    <div>
                                        Broj faksa:
                                        <span class="content-data">
                                            <xsl:value-of select="//podnosilac_prijave//broj_faksa" />
                                        </span>
                                    </div>
                                    <div>
                                        E-mail:
                                        <span class="content-data">
                                            <xsl:value-of select="//podnosilac_prijave//e-mail" />
                                        </span>
                                    </div>
                                </td>
                            </tr>
                            <xsl:if test="//podnosilac_prijave/fizicko_lice">
                                <tr style="vertical-align: text-top;">
                                    <td style="border: 1px solid black" colspan="3">
                                        <div>
                                            Državljanstvo:
                                            <span class="content-data">
                                                <xsl:value-of select="//podnosilac_prijave//drzavljanstvo" />
                                            </span>
                                        </div>
                                    </td>
                                </tr>
                            </xsl:if>
                        </tbody>
                    </table>
                </div>
     			<xsl:variable name="pronalazac" select="//podnosilac_pronalazac = true()"/>
                <xsl:variable name="pronalazac_naveden" select="//podaci_o_pronalazacu/pronalazac_naveden = true()" />
                <xsl:if test="not($pronalazac)">
                    <div style="margin-top: 30px;">
                        <table table-layout="fixed" width="100%" style="margin-left: auto; margin-right: auto;">
                            <tbody>
                                <tr>
                                    <td colspan="3" style="border-bottom: 1px solid black">
                                        <div>
                                            Polje broj III - PRONALAZAČ &#160; &#160; &#160;
                                            <xsl:if test="$pronalazac_naveden">
                                       		    <span style="font-size: 16px; color: darkgreen">Pronalazač ŽELI da bude naveden</span>
                                            </xsl:if>
                                            <xsl:if test="not($pronalazac_naveden)">
                                                <span style="font-size: 16px; color: darkred">Pronalazač NE ŽELI da bude naveden</span>
                                            </xsl:if>
                                        </div>
                                    </td>
                                </tr>
                                <xsl:if test="$pronalazac_naveden">
	                                <tr style="vertical-align: text-top">
	                                    <td style="border: 1px solid black">
	                                            Ime i prezime:
	                                            <div class="content-data">
	                                                <xsl:value-of select="//pronalazac/fizicko_lice/ime" />&#160;
	                                                <xsl:value-of select="//pronalazac/fizicko_lice/prezime" />
	                                            </div>
	                                    </td>
	                                    <td style="border: 1px solid black">
	                                        <div>
	                                            Država:
	                                            <span class="content-data">
	                                                <xsl:value-of select="//pronalazac//drzava" />
	                                            </span>
	                                        </div>
	                                        <div>
	                                            Mesto:
	                                            <span class="content-data">
	                                                <xsl:value-of select="//pronalazac//mesto" />
	                                            </span>
	                                        </div>
	                                        <div>
	                                            Ulica i broj:
	                                            <span class="content-data">
	                                                <xsl:value-of select="//pronalazac//ulica" />&#160;
	                                                <xsl:value-of select="//pronalazac//broj" />
	                                            </span>
	                                        </div>
	                                        <div>
	                                            Poštanski broj:
	                                            <span class="content-data">
	                                                <xsl:value-of select="//pronalazac//postanski_broj" />
	                                            </span>
	                                        </div>
	                                    </td>
	                                    <td style="border: 1px solid black">
	                                        <div>
	                                            Broj telefona:
	                                            <span class="content-data">
	                                                <xsl:value-of select="//pronalazac//broj_telefona" />
	                                            </span>
	                                        </div>
	                                        <div>
	                                            Broj faksa:
	                                            <span class="content-data">
	                                                <xsl:value-of select="//pronalazac//broj_faksa" />
	                                            </span>
	                                        </div>
	                                        <div>
	                                            E-mail:
	                                            <span class="content-data">
	                                                <xsl:value-of select="//pronalazac//e-mail" />
	                                            </span>
	                                        </div>
	                                    </td>
	                                </tr>
	                            </xsl:if>
                            </tbody>
                        </table>
                    </div>
                </xsl:if>
                <div style="margin-top: 30px;">
                    <table table-layout="fixed" width="100%" style="margin-left: auto; margin-right: auto;">
                        <tbody>
                        	<xsl:variable name="punomocnik_zastupanje" select="//punomocnik_zastupanje = true()"/>
							<xsl:variable name="punomocnik_prijem_pismena" select="//punomocnik_prijem_pismena = true()"/>
							<xsl:variable name="zajednicki_predstavnik" select="//zajednicki_predstavnik = true()"/>
                            <tr>
                                <td colspan="3" style="border-bottom: 1px solid black">
                                   <div text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
										Polje broj IV
										<xsl:if test="$punomocnik_zastupanje">
											<div class="content-data" style="color: darkgreen; text-align: left">Punomoćnik za zastupanje</div>
										</xsl:if>
										<xsl:if test="$punomocnik_prijem_pismena">
											<div class="content-data" style="color: darkgreen; text-align: left">Punomoćnik za prijem pismena</div>
										</xsl:if>
										<xsl:if test="$zajednicki_predstavnik">
											<div class="content-data" style="color: darkgreen; text-align: left">Zajednički predstavnik</div>
										</xsl:if>
										<xsl:if test="(not($punomocnik_zastupanje)) and (not($punomocnik_prijem_pismena)) and (not($zajednicki_predstavnik))">
											<div class="content-data" style="color: darkred; text-align: left">Punomoćnik nije unesen</div>
										</xsl:if>
									</div>
                                </td>
                            </tr>
                            <xsl:if test="($punomocnik_zastupanje) or ($punomocnik_prijem_pismena) or ($zajednicki_predstavnik)">
	                            <tr style="vertical-align: text-top">
	                                <td style="border: 1px solid black">
	                                    <xsl:if test="//punomocnik/fizicko_lice">
	                                        Ime i prezime:
	                                        <div class="content-data">
	                                            <xsl:value-of select="//punomocnik/fizicko_lice/ime" />  &#160;
	                                            <xsl:value-of select="//punomocnik/fizicko_lice/prezime" />
	                                        </div>
	                                    </xsl:if>
	                                    <xsl:if test="//punomocnik/poslovno_ime">
	                                        Poslovno ime:
	                                        <div class="content-data">
	                                            <xsl:value-of select="//punomocnik/poslovno_ime" />
	                                        </div>
	                                    </xsl:if>
	                                </td>
	                                <td style="border: 1px solid black">
	                                    <div>
	                                        Mesto:
	                                        <span class="content-data">
	                                            <xsl:value-of select="//punomocnik//mesto" />
	                                        </span>
	                                    </div>
	                                    <div>
	                                        Ulica i broj:
	                                        <span class="content-data">
	                                            <xsl:value-of select="//punomocnik//ulica" /> &#160;
	                                            <xsl:value-of select="//punomocnik//broj" />
	                                        </span>
	                                    </div>
	                                    <div>
	                                        Poštanski broj:
	                                        <span class="content-data">
	                                            <xsl:value-of select="//punomocnik//postanski_broj" />
	                                        </span>
	                                    </div>
	                                </td>
	                                <td style="border: 1px solid black">
	                                    <div>
	                                        Broj telefona:
	                                        <span class="content-data">
	                                            <xsl:value-of select="//punomocnik//broj_telefona" />
	                                        </span>
	                                    </div>
	                                    <div>
	                                        E-mail:
	                                        <span class="content-data">
	                                            <xsl:value-of select="//punomocnik//e-mail" />
	                                        </span>
	                                    </div>
	                                </td>
	                            </tr>
                            </xsl:if>
                        </tbody>
                    </table>
                </div>
                <xsl:if test="//adresa_za_dostavljanje">
	                <div style="margin-top: 30px;">
	                    <table table-layout="fixed" width="100%" style="margin-left: auto; margin-right: auto;">
	                        <tbody>
	                            <tr>
	                                <td style="border-bottom: 1px solid black">
	                                   <div text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
											Polje broj V - ADRESA ZA DOSTAVLJANJE
										</div>
	                                </td>
	                            </tr>
	                            <tr style="vertical-align: text-top">
	                                <td style="border: 1px solid black">
	                                    <div>
	                                        Mesto:
	                                        <span class="content-data">
	                                            <xsl:value-of select="//adresa_za_dostavljanje/mesto" />
	                                        </span>
	                                    </div>
	                                    <div>
	                                        Ulica i broj:
	                                        <span class="content-data">
	                                            <xsl:value-of select="//adresa_za_dostavljanje/ulica" /> &#160;
	                                            <xsl:value-of select="//adresa_za_dostavljanje/broj" />
	                                        </span>
	                                    </div>
	                                    <div>
	                                        Poštanski broj:
	                                        <span class="content-data">
	                                            <xsl:value-of select="//adresa_za_dostavljanje/postanski_broj" />
	                                        </span>
	                                    </div>
	                                </td>
	                            </tr>
	                        </tbody>
	                    </table>
	                </div>
                </xsl:if>
               	<div style="margin-top: 30px;">
                    <table table-layout="fixed" width="100%" style="margin-left: auto; margin-right: auto;">
                        <tbody>
                        	<xsl:variable name="elektronski" select="//elektronski = true()"/>
							<xsl:variable name="papirno" select="//papirno = true()"/>
                            <tr>
                                <td style="border-bottom: 1px solid black">
                                   <div text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
										Polje broj VI - NAČIN DOSTAVLJANJA
									</div>
                                </td>
                            </tr>
                            <tr style="vertical-align: text-top">
                                <td style="border: 1px solid black">
                                    <xsl:if test="$elektronski">
										<div class="content-data" style="color: darkgreen; text-align: left">
											Podnosilac prijave je saglasan da zavod vrši dostavljanje pismena isključivo elektronskim putem u formi elektronskog dokumenta (u ovom slučaju neophodna je registracija na portalu "eUprave")
										</div>
										
									</xsl:if>
									<xsl:if test="$papirno and $elektronski"><br/></xsl:if>
									<xsl:if test="$papirno">
										<div class="content-data" style="color: darkgreen; text-align: left">
											Podnosilac prijave je saglasan da zavod vrši dostavljanje pismena u papirnoj formi
										</div>
									</xsl:if>
									<xsl:if test="(not($elektronski)) and (not($papirno))">
										<div class="content-data" style="color: darkred; text-align: left">
											Podnosilac prijave nije pružio saglasnost za dostavu
										</div>
									</xsl:if>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <xsl:variable name="dopunska" select="//dopunska_prijava = true()"/>
				<xsl:variable name="izdvojena" select="//izdvojena_prijava = true()"/>
				<xsl:if test="($dopunska) or ($izdvojena)">
					<div style="margin-top: 30px;">
	                    <table table-layout="fixed" width="100%" style="margin-left: auto; margin-right: auto;">
	                        <tbody>
	                        	<xsl:variable name="elektronski" select="//elektronski = true()"/>
								<xsl:variable name="papirno" select="//papirno = true()"/>
	                            <tr>
	                                <td style="border-bottom: 1px solid black">
	                                   <div text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
											Polje broj VII - Prijava je &#160;
											<xsl:if test="$dopunska and not($izdvojena)">
												<span class="content-data" style="color: darkgreen">
													dopunska
												</span>
											</xsl:if>
												&#160;
											<xsl:if test="$dopunska and $izdvojena">
												<span class="content-data" style="color: darkgreen">dopunska i izdvojena</span>
											</xsl:if>
											<xsl:if test="$izdvojena and not($dopunska)">
												<span class="content-data" style="color: darkgreen">
													izdvojena
												</span>
											</xsl:if>
										</div>
	                                </td>
	                            </tr>
	                            <tr style="vertical-align: text-top">
	                                <td style="border: 1px solid black">
	                                	<div style="text-align: left; margin-bottom:20px">
	                                		Broj prvobitne prijave/osnovne prijave, odnosno osnovnog patenta:&#160;
											<span class="content-data">
												<xsl:value-of select="//broj_osnovne_prijave" />
											</span>
	                                	</div>
	                                	<div style="text-align: left;">
	                                		Datum podnošenja prvobitne prijave/osnovne prijave:&#160;
											<span class="content-data">
												<xsl:value-of select="//datum_osnovne_prijave" />
											</span>
	                                	</div>
	                                </td>
	                            </tr>
	                        </tbody>
	                    </table>
	                </div>
				</xsl:if>
				<xsl:if test="(count(//detalji_ranije_prijave) &gt; 0)">
					<div style="margin-top: 30px;">
	                    <table table-layout="fixed" width="100%" style="margin-left: auto; margin-right: auto;">
	                        <tbody>
	                            <tr>
	                                <td colspan="3" style="border-bottom: 1px solid black">
	                                    <div>Polje broj VIII - ZAHTEV ZA PRIZNANJE PRVENSTVA IZ RANIJIH PRIJAVA</div>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td style="border: 1px solid black" width="33%">
	                                    <div style="text-align: center">
	                                        Datum podnošenja ranije prijave
	                                    </div>
	                                </td>
	                                <td style="border: 1px solid black" width="33%">
	                                    <div style="text-align: center">
	                                        Broj ranije prijave
	                                    </div>
	                                </td>
                        	        <td style="border: 1px solid black" width="34%">
	                                    <div style="text-align: center">
	                                        Dvoslovna oznaka države, regionalne ili međunarodne organizacije
	                                    </div>
	                                </td>
	                            </tr>
	                            <xsl:for-each select="//detalji_ranije_prijave">
		                            <tr>
		                                <td style="border: 1px solid black" width="33%">
		                                    <div style="text-align: center">
		                                        <xsl:value-of select="datum_podnosenja"/>
		                                    </div>
		                                </td>
		                                <td style="border: 1px solid black" width="33%">
		                                    <div style="text-align: center">
		                                        <xsl:value-of select="broj_prijave"/>
		                                    </div>
		                                </td>
	                        	        <td style="border: 1px solid black" width="34%">
		                                    <div style="text-align: center">
		                                        <xsl:value-of select="dvoslovna_oznaka"/>
		                                    </div>
		                                </td>
		                            </tr>
		                        </xsl:for-each>    
	                        </tbody>
	                    </table>
	                </div>
				</xsl:if>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>