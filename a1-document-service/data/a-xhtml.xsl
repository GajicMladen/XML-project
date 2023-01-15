<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
	<xsl:template match="/">
        <html>
            <head>
                <title>Dokument A</title>
                <style type="text/css"> * {margin:0; padding:0; text-indent:0; }
                    body {
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        width: 60%;
                        padding: 20px;
                        margin: 0 auto;
                        border: 1px solid black;
                        margin-top: 10px;
                    }
                    .header {
                        border: 1px solid black;                        
                        width: 95%;
                        margin-top: 15px;
                    }
                    h2 { color: black; font-family:Arial, sans-serif; font-style: normal; font-weight: bold; text-decoration: none; font-size: 14px; }
                    p { color: black; font-family:Arial, sans-serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 16px; margin:0pt; }
                    h1 { color: black; font-family:Arial, sans-serif; font-style: normal; font-weight: bold; text-decoration: none; font-size: 14px; }
                    .s1 { color: black; font-family:Arial, sans-serif; font-style: italic; font-weight: normal; text-decoration: none; font-size: 12px; }
                    li {display: block; }
                    #l1 {padding-left: 0pt; border: 1px solid black; border-left: none; border-right: none; border-bottom: none; font-size: 16px;}
                    #l1> li>*:first-child:before {color: black; font-family:Arial, sans-serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 10pt; }
                    
                    li {display: block; }
                    #l2 {padding-left: 0pt;}
                    #l2> li>*:first-child:before {color: black; font-family:Arial, sans-serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 10pt; }                    
                    #l3 {padding-left: 0pt; }
                    #l3> li>*:first-child:before {content: "- "; color: black; font-family:Arial, sans-serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 10pt; }                
                </style>
            </head>
            <body>
                <div class="header" style="clear: both;">
                    <p style="text-indent: 0pt;text-align: left;"><br/></p>                
                    <span style="float: left;"><h2 style="padding-top: 4pt;padding-left: 6pt;text-indent: 0pt;text-align: left;font-size: 16px; margin-left: 10px">ZAVOD ZA INTELEKTUALNU SVOJINU</h2></span>
                    <span style="float: right;"><h2 style="padding-top: 4pt;padding-left: 6pt;text-indent: 0pt;text-align: left;font-size: 16px; margin-right: 10px">OBRAZAC A-1</h2></span>                     
                    <p style="text-indent: 0pt;text-align: left;"><br/></p>
                    <p style="padding-top: 7pt;padding-left: 6pt;text-indent: 0pt; margin-left: 10px">Beograd, Kneginje Ljubice 5</p>
                    <p style="text-indent: 0pt;text-align: left;"><br/></p>
                    <h1 style="padding-top: 7pt;padding-left: 31pt;text-indent: 0pt;text-align: center; margin-top: 20px;font-size: 16px;">ZAHTEV ZA UNOŠENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA</h1>
                    <p style="text-indent: 0pt;text-align: left;"><br/></p>
                    <p style="text-indent: 0pt;text-align: left;"><br/></p>
                    <ol id="l1" style="display: block">
	                    <li>
	                        <div>
	                            <br />
	                            <br />
	                            <p style="padding-left: 6pt;text-indent: 0pt;text-align: left;"> 1) Podnosilac - ime, prezime, adresa i državljanstvo autora ili drugog nosioca autorskog prava ako je podnosilac fizicko lice, odnosno poslovno ime i sedište nosioca autorskog prava ako je podnosilac pravno lice*:</p>
	                            <br />
	                            <xsl:if test="string(//podnosilac/lice/ime)">
	                            	<h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//podnosilac/lice/ime" /><xsl:text> </xsl:text><xsl:value-of select="//podnosilac/lice/prezime" /></h2>
	                            	<h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//podnosilac/lice/adresa/ulica" /><xsl:text> </xsl:text><xsl:value-of select="//podnosilac/lice/adresa/broj" /></h2>
	                            	<h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//podnosilac/lice/adresa/grad" /><xsl:text> </xsl:text><xsl:value-of select="//podnosilac/lice/adresa/postanski-kod" /></h2>
	                            	<h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//podnosilac/lice/drzavljanstvo" /></h2>									
								</xsl:if>
								<xsl:if test="string(//podnosilac/lice/poslovno-ime)">
									<h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//podnosilac/lice/poslovno-ime" /></h2>
									<h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//podnosilac/lice/sediste/adresa/ulica" /><xsl:text> </xsl:text><xsl:value-of select="//podnosilac/lice/sediste/adresa/broj" /></h2>
									<h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//podnosilac/lice/sediste/adresa/grad" /><xsl:text> </xsl:text><xsl:value-of select="//podnosilac/lice/sediste/adresa/postanski-kod" /></h2>									
								</xsl:if>	                            
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                            <table style="width: 90%; border-collapse: collapse; margin-left: 35px;">                                
	                                <tr>
	                                  <th style="width: 50%; border: 1px solid black;">
	                                    <p style="padding-top: 1pt;padding-left: 2pt;text-indent: 0pt;text-align: left;">Telefon:</p>
	                                    <h2 style="padding-left: 2pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//podnosilac/lice/telefon" /></h2>
	                                  </th>
	                                  <th style="width: 50%; border: 1px solid black;">
	                                    <p style="padding-top: 1pt;padding-left: 2pt;text-indent: 0pt;text-align: left;">Email:</p>
	                                    <p style="padding-left: 2pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//podnosilac/lice/email" /></p>                                                
	                                  </th>
	                                </tr>
	                            </table>
	                            <p style="padding-left: 5pt;text-indent: 0pt;text-align: left;"/>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                        </div>                        
	                    </li>	                    
                        <li>
	                        <div style="margin-top: 10px;">
	                            <p style="padding-left: 17pt;text-indent: -11pt;text-align: left;"> 2) Pseudonim ili znak autora, (ako ga ima):</p>
	                            <br />
	                            <h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//pseudonim-znak-autora" /></h2>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                        </div>                        
                    	</li>
	                    <li>
	                        <div style="margin-top: 15px;">
	                            <p style="padding-left: 17pt;text-indent: -11pt;text-align: left;"> 3) Ime, prezime i adresa punomocnika, ako se prijava podnosi preko punomocnika:</p>
	                            <br />
	                            <xsl:if test="string(//punomocnik/ime)">
	                            	<h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//punomocnik/ime" /><xsl:text> </xsl:text><xsl:value-of select="//punomocnik/prezime" /></h2>
	                            	<h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//punomocnik/adresa/ulica" /><xsl:text> </xsl:text><xsl:value-of select="//punomocnik/broj" /></h2>
	                            	<h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//punomocnik/adresa/grad" /><xsl:text> </xsl:text><xsl:value-of select="//punomocnik/postanski-broj" /></h2>																				
								</xsl:if>	                            
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                        </div>                        
	                    </li>
	                    <li>
	                        <div style="margin-top: 15px;">
	                            <p style="padding-left: 6pt;text-indent: 0pt;text-align: left;"> 4) Naslov autorskog dela, odnosno alternativni naslov, ako ga ima, po kome autorsko delo može da se identifikuje*:</p>
	                            <br />
	                            <h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//naslov-autorskog-dela/naslov" /></h2>
	                            <p class="s1" style="padding-left: 6pt;text-indent: 0pt;text-align: left; font-size: 12px;"><xsl:value-of select="//naslov-autorskog-dela/alternativni-naslov" /></p>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                        </div>                    
	                    </li>
	                    <li>
	                        <div style="margin-top: 15px; margin-right: 15px;">
	                            <p style="padding-left: 6pt;text-indent: 0pt;text-align: left;"> 5) Podaci o naslovu autorskog dela na kome se zasniva delo prerade, ako je u pitanju autorsko delo prerade, kao i podatak o autoru izvornog dela:</p>
	                            <br />
	                            <h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//naslov-delo-prerade/naslov" /></h2>
	                            <h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//naslov-delo-prerade/autor/ime" /><xsl:text> </xsl:text><xsl:value-of select="//naslov-delo-prerade/autor/prezime" /></h2>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                        </div>                        
	                    </li>
	                    <li>
	                        <div style="margin-top: 15px">
	                            <p style="padding-top: 9pt;padding-left: 17pt;text-indent: -11pt;text-align: left;"> 6) Podaci o vrsti autorskog dela (književno delo, muzicko delo, likovno delo pacunarski program i dr.)*:</p>
	                            <br />
	                            <h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//vrsta-autorskog-dela" /></h2>
	                            <p style="text-indent: 0pt;text-align: left;"/>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                        </div>                        
	                    </li>
	                    <li>
	                        <div style="margin-top: 15px;">
	                            <p style="padding-top: 4pt;padding-left: 17pt;text-indent: -11pt;text-align: left;"> 7) Podaci o formi zapisa autorskog dela (štampani tekst, opticki disk i slicno)*:</p>
	                            <br />
	                            <h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//forma-zapisa-autorskog-dela" /></h2>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                        </div>                        
	                    </li>
	                    <li>
	                        <div style="margin-top: 15px;  margin-right: 15px;">
	                            <p style="padding-left: 6pt;text-indent: 0pt;text-align: justify;"> 8) Podaci o autoru ako je podnosilac prijave iz tacke 1. ovog zahteva nije autor i to: prezime, ime, adresa i državljanstvo autora 
	                                (grupe autora ili koautora), a ako su u pitanju jedan ili više autora koji nisu živi, imena autora i godina smrti autora, a ako je 
	                                u pitanju autorsko delo anonimnog autora navod da je autorsko delo delo anonimnog autora:</p>
	                            <br />
	                            <h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"></h2>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                        </div>                        
	                    </li>
	                    <li>
	                        <div style="margin-top: 15px;">
	                            <p style="padding-top: 9pt;padding-left: 17pt;text-indent: -11pt;text-align: left;"> 9) Podatak da li je autorsko delo stvoreno u radnom odnosu:</p>
	                            <br />
	                            <h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//autorsko-delo-stvoreno-radnim-odnosom" /></h2>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                        </div>                        
	                    </li>
	                    <li>
	                        <div style="margin-top: 15px;">
	                            <p style="padding-left: 23pt;text-indent: -17pt;text-align: left;"> 10) Nacin korišcenja autorskog dela ili nameravani nacin korišcenja autoskog dela:</p>
	                            <br />
	                            <h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//nacin-koriscenja" /></h2>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                        </div>                        
	                    </li>
	                </ol>
	                <ol id="l2">
	                    <li>
	                        <div>
	                            <br />
	                            <p style="padding-top: 7pt;padding-left: 23pt;text-indent: -17pt;text-align: left;"> 12) Prilozi koji se podnose uz zahtev:</p>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                            <h1 style="text-indent: 0pt;text-align: center;">POPUNJAVA ZAVOD:</h1>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                            <h2 style="padding-left: 6pt;text-indent: 0pt;text-align: left;">Prilozi uz prijavu:</h2>
	                            <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                            <ul id="l3">
	                            <li data-list-text="-">
	                                <p style="padding-left: 12pt;text-indent: -6pt;text-align: left;">opis autorskog dela (ako je delo podneto na optickom disku)</p>
	                                <p style="text-indent: 0pt;text-align: left;"><br/></p>
	                            </li>
	                            <li data-list-text="-">
	                                <p style="padding-left: 12pt;text-indent: -6pt;text-align: left;">primer autroskog dela (slika, video zapis, audio zapis)</p>
	                            </li>
	                            </ul>
	                        </div>
	                    </li>
	                </ol>
	                <div style="padding: 15px;">
	                    <br />
	                    <br />
	                    <br />             
	                    <br />
	                    <br />
	                    <table style="width: 90%; border-collapse: collapse; margin-left: 35px;">                                
	                        <tr>
	                            <th>                                
	                            </th>
	                            <th style="width: 50%; border: 1px solid black;">
	                                <p style="padding-top: 1pt;padding-left: 1pt;text-indent: 0pt;text-align: left;"><span style=" color: black; font-family:Arial, sans-serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 12pt;">Broj prijave:</span></p>
	                                <h1 style="padding-left: 1pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//broj-prijave" /></h1>
	                            </th>
	                        </tr>
	                        <tr>
	                            <th>
	                            </th>
	                            <th style="width: 50%; border: 1px solid black;">
	                                <p style="padding-top: 1pt;padding-left: 1pt;text-indent: 0pt;text-align: left;"><span style=" color: black; font-family:Arial, sans-serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 12pt;">Datum podnošenja:</span></p>
	                                <h1 style="padding-left: 1pt;text-indent: 0pt;text-align: left;"><xsl:value-of select="//datum-podnosenja" /></h1>
	                            </th>
	                        </tr>
	                    </table>                                      
	                    <br />
	                    <br />                    
	                </div>                  
                </div>                              
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
