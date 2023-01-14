<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    			
    			xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
	<xsl:template match="/">
		
		<fo:root>
			<fo:layout-master-set>
                <fo:simple-page-master master-name="a-obrazac-page">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="a-obrazac-page">
				<fo:flow flow-name="xsl-region-body">
					<fo:block border="1pt solid black" padding="10px">
	  					<fo:block font-family="sans-serif" font-size="10px" font-weight="bold">
	  						ZAVOD ZA INTELEKTUALNU SVOJINU
	  						<xsl:for-each select="1 to 18">
  								<fo:leader leader-pattern="space" />
							</xsl:for-each>	  						
	  						OBRAZAC A-1
	  					</fo:block>	  					
	  					<fo:block font-family="sans-serif" font-size="10px">
	  						Beograd, Kneginje Ljubice 5
	  					</fo:block>
	  					<fo:block font-family="sans-serif" font-size="12px" font-weight="bold" text-align="center" margin="20px">
	  						ZAHTEV ZA UNOŠENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA 
	  					</fo:block>
					</fo:block>
					<fo:block border="1pt solid black" padding="10px" border-top="none">
						<fo:block font-family="sans-serif" font-size="10px" margin-top="15px" margin-bottom="20px">
							1) Podnosilac - ime, prezime, adresa i državljanstvo autora ili drugog nosioca 
							autorskog prava ako je podnosilac fizicko lice, odnosno poslovno ime i sedište
							nosioca autorskog prava ako je podnosilac pravno lice*:							
							<xsl:if test="string(//podnosilac/lice/ime)">
								<fo:block font-weight="bold"> <xsl:value-of select="//podnosilac/lice/ime" /><xsl:text> </xsl:text><xsl:value-of select="//podnosilac/lice/prezime" /></fo:block>
								<fo:block font-weight="bold"> <xsl:value-of select="//podnosilac/lice/adresa/ulica" /><xsl:text> </xsl:text><xsl:value-of select="//podnosilac/lice/adresa/broj" /></fo:block>
								<fo:block font-weight="bold"> <xsl:value-of select="//podnosilac/lice/adresa/grad" /><xsl:text> </xsl:text><xsl:value-of select="//podnosilac/lice/adresa/postanski-kod" /></fo:block>
								<fo:block font-weight="bold"> <xsl:value-of select="//podnosilac/lice/drzavljanstvo" /> </fo:block>
							</xsl:if>
							<xsl:if test="string(//podnosilac/lice/poslovno-ime)">
								<fo:block font-weight="bold"> <xsl:value-of select="//podnosilac/lice/poslovno-ime" /> </fo:block>
								<fo:block font-weight="bold"> <xsl:value-of select="//podnosilac/lice/sediste/adresa/ulica" /><xsl:text> </xsl:text><xsl:value-of select="//podnosilac/lice/sediste/adresa/broj" /></fo:block>
								<fo:block font-weight="bold"> <xsl:value-of select="//podnosilac/lice/sediste/adresa/grad" /><xsl:text> </xsl:text><xsl:value-of select="//podnosilac/lice/sediste/adresa/postanski-kod" /></fo:block>
							</xsl:if>
						</fo:block>												
												
						<fo:table>
							<fo:table-column column-width="50%"/>
                            <fo:table-column column-width="50%"/>
							<fo:table-body>
						    	<fo:table-row>
						        	<fo:table-cell border="1pt solid black">
						        		<fo:block margin="2px" font-family="sans-serif" font-size="10px">Telefon: <fo:block font-weight="bold"> <xsl:value-of select="//podnosilac/lice/telefon" /></fo:block></fo:block>
						      		</fo:table-cell>
						      		<fo:table-cell border="1pt solid black">
						        		<fo:block margin="2px" font-family="sans-serif" font-size="10px">Email: <fo:block font-weight="bold"> <xsl:value-of select="//podnosilac/lice/email" /></fo:block></fo:block>
						      		</fo:table-cell>
						    	</fo:table-row>
						  	</fo:table-body>
						</fo:table>
						
						<fo:block font-family="sans-serif" font-size="10px" margin-top="35px" margin-bottom="20px">
							2) Pseudonim ili znak autora, (ako ga ima):
							<fo:block font-weight="bold"> <xsl:value-of select="//pseudonim-znak-autora" /></fo:block>
						</fo:block>												
						
						<fo:block font-family="sans-serif" font-size="10px" margin-top="35px" margin-bottom="20px">
							3) Ime, prezime i adresa punomocnika, ako se prijava podnosi preko punomocnika:
							<fo:block>
								<xsl:if test="string(//punomocnik/ime)">
									<fo:block font-weight="bold"> <xsl:value-of select="//punomocnik/ime" /><xsl:text> </xsl:text><xsl:value-of select="//punomocnik/prezime" /></fo:block>
									<fo:block font-weight="bold"> <xsl:value-of select="//punomocnik/adresa/ulica" /><xsl:text> </xsl:text><xsl:value-of select="//punomocnik/broj" /></fo:block>
									<fo:block font-weight="bold"> <xsl:value-of select="//punomocnik/adresa/grad" /><xsl:text> </xsl:text><xsl:value-of select="//punomocnik/postanski-broj" /></fo:block>			
								</xsl:if>
							</fo:block>	
						</fo:block>																			
						
						<fo:block font-family="sans-serif" font-size="10px" margin-top="35px" margin-bottom="20px">
							4) Naslov autorskog dela, odnosno alternativni naslov, ako ga ima, po kome
							autorsko delo može da se identifikuje*:
							<fo:block font-weight="bold"> <xsl:value-of select="//naslov-autorskog-dela/naslov" /></fo:block>
							<fo:block font-style="italic"> <xsl:value-of select="//naslov-autorskog-dela/alternativni-naslov" /></fo:block>
						</fo:block>												
						
						<fo:block font-family="sans-serif" font-size="10px" margin-top="35px" margin-bottom="20px">
							5) Podaci o naslovu autorskog dela na kome se zasniva delo prerade, ako je
							u pitanju autorsko delo prerade, kao i podatak o autoru izvornog dela:
							<fo:block font-weight="bold"> <xsl:value-of select="//naslov-delo-prerade/naslov" /></fo:block>
							<fo:block font-weight="bold"> <xsl:value-of select="//naslov-delo-prerade/autor/ime" /><xsl:text> </xsl:text><xsl:value-of select="//naslov-delo-prerade/autor/prezime" /> </fo:block>
						</fo:block>																		
					
						<fo:block font-family="sans-serif" font-size="10px" margin-top="35px" margin-bottom="80px">
							6) Podaci o vrsti autorskog dela (književno delo, muzicko delo, likovno delo
							pacunarski program i dr.)*:
							<fo:block font-weight="bold"> <xsl:value-of select="//vrsta-autorskog-dela" /></fo:block>
						</fo:block>																		
					</fo:block>
					<fo:block border="1pt solid black" padding="10px">
						<fo:block font-family="sans-serif" font-size="10px" margin-top="15px" margin-bottom="20px">
							7) Podaci o formi zapisa autorskog dela (štampani tekst, opticki disk i slicno)*:
							<fo:block font-weight="bold"> <xsl:value-of select="//forma-zapisa-autorskog-dela" /></fo:block>
						</fo:block>												
											
						<fo:block font-family="sans-serif" font-size="10px"  margin-top="35px" margin-bottom="20px">
							8) Podaci o autoru ako je podnosilac prijave iz tacke 1. ovog zahteva nije autor i to:
						 	prezime, ime, adresa i državljanstvo autora (grupe autora ili koautora), a ako su u pitanju
						 	jedan ili više autora koji nisu živi, imena autora i godina smrti autora, a ako je u pitanju
						 	autorsko delo anonimnog autora navod da je autorsko delo delo anonimnog autora:						 	
						</fo:block>																		
						
						<fo:block font-family="sans-serif" font-size="10px" margin-top="35px" margin-bottom="20px">
							9) Podatak da li je autorsko delo stvoreno u radnom odnosu:
							<fo:block font-weight="bold"> <xsl:value-of select="//autorsko-delo-stvoreno-radnim-odnosom" /></fo:block>
						</fo:block>												
						
						<fo:block font-family="sans-serif" font-size="10px" margin-top="35px" margin-bottom="20px">
							10) Nacin korišcenja autorskog dela ili nameravani nacin korišcenja autoskog dela:
							<fo:block font-weight="bold"> <xsl:value-of select="//nacin-koriscenja" /></fo:block>
						</fo:block>												
						
						<fo:block font-family="sans-serif" font-size="10px" margin-top="15px">
							12) Prilozi koji se podnose uz zahtev:
						</fo:block>												
						<fo:block text-align="center" font-weight="bold" font-family="sans-serif" font-size="12px" margin-top="15px">
							POPUNJAVA ZAVOD:
						</fo:block>
						<fo:block font-weight="bold" font-family="sans-serif" font-size="10px" margin-top="15px">
							Prilozi uz prijavu:
						</fo:block>
						<fo:block font-family="sans-serif" font-size="10px" margin-top="15px">
							- opis autorskog dela (ako je delo podneto na optickom disku)
						</fo:block>
						<fo:block font-family="sans-serif" font-size="10px" margin-top="15px">
							- primer autroskog dela (slika, video zapis, audio zapis)
						</fo:block>
						
						<fo:table margin-top="75px">
							<fo:table-body>
						    	<fo:table-row>						        	
						      		<fo:table-cell>
								        <fo:block></fo:block>
								    </fo:table-cell>
								    <fo:table-cell padding="2px" border="1pt solid black">
						        		<fo:block>Broj prijave: <fo:block font-weight="bold"> <xsl:value-of select="//broj-prijave" /></fo:block> </fo:block>
						      		</fo:table-cell>					  
						    	</fo:table-row>
						    	<fo:table-row>
						    		<fo:table-cell>
								        <fo:block></fo:block>
								    </fo:table-cell>
						        	<fo:table-cell padding="2px" border="1pt solid black">
						        		<fo:block>Datum podnošenja: <fo:block font-weight="bold"> <xsl:value-of select="//datum-podnosenja" /></fo:block> </fo:block>
						      		</fo:table-cell>						      		
						    	</fo:table-row>
							</fo:table-body>
						</fo:table>
								
							
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
            
		</fo:root>
			
	</xsl:template>
	
</xsl:stylesheet>