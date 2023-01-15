<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:b="http://www.ftn.uns.ac.rs/xpath/examples"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
	<xsl:template match="/">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="Patent-zahtev">
					<fo:region-body margin="0.75in"/>
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="Patent-zahtev">
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<fo:table table-layout="fixed" width="50%" text-align="center" border-width="2px">
							<fo:table-column column-width="50%" />
							<fo:table-column column-width="50%" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell text-align="center" border="solid" border-width="2px" number-columns-spanned="2">
										<fo:block font-size="10pt">Popunjava zavod</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell border="solid" border-width="2px" number-columns-spanned="2" margin-left="3px">
										<fo:block text-align="start"  padding-top="4px" font-size="10pt" margin-bottom="15px">Broj prijave</fo:block>
										<fo:block margin-top="4px">
											<xsl:value-of select="//podaci_zavod/broj_prijave" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell border="solid" border-width="2px" margin-left="3px">
										<fo:block text-align="start" padding-top="4px" font-size="10pt" margin-bottom="27px">Datum prijema</fo:block>
										<fo:block margin-top="4px">
											<xsl:value-of select="//podaci_zavod/datum_prijema" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid" border-width="2px" margin-left="3px">
										<fo:block text-align="start" padding-top="4px" font-size="10pt" margin-bottom="15px">Priznati datum podnošenja</fo:block>
										<fo:block margin-top="4px">
											<xsl:value-of select="//podaci_zavod/datum_podnosenja" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row margin-top="500px">
									<fo:table-cell text-align="left" number-columns-spanned="2">
										<fo:block margin-top="8px" font-size="10pt">Republika Srbija</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left" number-columns-spanned="2">
										<fo:block font-size="10pt">Zavod za intelektualnu svojinu</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left" number-columns-spanned="2">
										<fo:block font-size="10pt">Kneginje Ljubice broj 5	</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left" number-columns-spanned="2">
										<fo:block font-size="10pt">11000 Beograd</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block text-align="center" font-size="20pt" margin-top="30px" margin-bottom="8px">
						ZAHTEV ZA PRIZNANJE PATENTA
					</fo:block>
					<fo:block>
						<fo:table table-layout="fixed" width="100%" border="solid black" border-width="2px" font-family="Arial" margin-bottom="30px">
							<fo:table-column column-width="33%" />
							<fo:table-column column-width="33%" />
							<fo:table-column column-width="34%" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell text-align="start" border="solid" border-width="2px" number-columns-spanned="3">
										<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">Polje broj I - NAZIV PRONALASKA</fo:block>
										<fo:block text-align="left" font-size="8pt" margin-top="6px" margin-left="6px">Na srpskom jeziku: 
											
											
											
											
											
											
											<fo:inline font-size="16pt" >
												<xsl:value-of select="//naziv[@lang='sr']" />
											</fo:inline>
										</fo:block>
										<fo:block text-align="left" font-size="8pt" margin-top="6px" margin-left="6px" margin-bottom="6px">Na engleskom jeziku:  
											
											
											
											
											
											
											<fo:inline font-size="16pt" >
												<xsl:value-of select="//naziv[@lang='en']" />
											</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="start" border="solid" border-width="1px" number-columns-spanned="3">
										<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
											Polje broj II - PODNOSILAC PRIJAVE &#160; &#160; &#160;
											
											
											
											
											
											<xsl:variable name="pronalazac" select="//podnosilac_pronalazac = true()"/>
											<xsl:if test="$pronalazac">
												<fo:inline font-size="12pt" color="darkgreen">Podnosilac JESTE pronalazač</fo:inline>
											</xsl:if>
											<xsl:if test="not($pronalazac)">
												<fo:inline font-size="12pt" color="darkred">Podnosilac NIJE pronalazač</fo:inline>
											</xsl:if>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="start" border-top="none" border-right="solid" border-width="1px">
										<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
											<xsl:if test="//podnosilac_prijave/fizicko_lice">
												<fo:inline font-size="10pt">Ime i prezime: 
													
													
													
													
													
													<fo:inline font-size="13pt">
														<xsl:value-of select="//podnosilac_prijave/fizicko_lice/ime" />&#160;
														
														
														
														
														
														<xsl:value-of select="//podnosilac_prijave/fizicko_lice/prezime" />
													</fo:inline>
												</fo:inline>
											</xsl:if>
											<xsl:if test="//podnosilac_prijave/poslovno_ime">
												<fo:inline font-size="10pt">Poslovno ime: 
													
													
													
													
													
													<fo:inline font-size="13pt">
														<xsl:value-of select="//podnosilac_prijave/poslovno_ime" />
													</fo:inline>
												</fo:inline>
											</xsl:if>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="start" border-top="none" border-right="solid" border-width="1px">
										<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
											<fo:block font-size="10pt">Država: 
												
												
												
												
												
												<fo:inline font-size="13pt">
													<xsl:value-of select="//podnosilac_prijave//drzava" />
												</fo:inline>
											</fo:block>
											<fo:block font-size="10pt">Mesto: 
												
												
												
												
												
												<fo:inline font-size="13pt">
													<xsl:value-of select="//podnosilac_prijave//mesto" />
												</fo:inline>
											</fo:block>
											<fo:block font-size="10pt">Ulica i broj: 
												
												
												
												
												
												<fo:inline font-size="13pt">
													<xsl:value-of select="//podnosilac_prijave//ulica" />&#160;
													
													
													
													
													
													<xsl:value-of select="//podnosilac_prijave//broj" />
												</fo:inline>
											</fo:block>
											<fo:block font-size="10pt">Poštanski broj: 
												
												
												
												
												
												<fo:inline font-size="13pt">
													<xsl:value-of select="//podnosilac_prijave//postanski_broj" />
												</fo:inline>
											</fo:block>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="start" border-top="none" border-width="1px">
										<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
											<fo:block font-size="10pt">Broj telefona: 
												
												
												
												
												
												<fo:inline font-size="13pt">
													<xsl:value-of select="//podnosilac_prijave//broj_telefona" />
												</fo:inline>
											</fo:block>
											<fo:block font-size="10pt">Broj faksa: 
												
												
												
												
												
												<fo:inline font-size="13pt">
													<xsl:value-of select="//podnosilac_prijave//broj_faksa" />
												</fo:inline>
											</fo:block>
											<fo:block font-size="10pt">E-mail: 
												
												
												
												
												
												<fo:inline font-size="13pt">
													<xsl:value-of select="//podnosilac_prijave//e-mail" />
												</fo:inline>
											</fo:block>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<xsl:if test="//podnosilac_prijave/fizicko_lice">
									<fo:table-row>
										<fo:table-cell text-align="start" border="solid" border-width="1px" number-columns-spanned="3">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px">Državljanstvo: 
												
												
												
												
												
												
												<fo:inline font-size="16pt" >
													<xsl:value-of select="//podnosilac_prijave//drzavljanstvo" />
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:if>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<xsl:variable name="pronalazac" select="//podnosilac_pronalazac = true()"/>
					<xsl:variable name="pronalazac_naveden" select="//podaci_o_pronalazacu/pronalazac_naveden = true()"/>
					<xsl:if test="not($pronalazac)">
						<fo:block>
							<fo:table table-layout="fixed" width="100%" border="solid black" border-width="2px" font-family="Arial" margin-bottom="30px" keep-together.within-page="1">
								<fo:table-column column-width="33%" />
								<fo:table-column column-width="33%" />
								<fo:table-column column-width="34%" />
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell text-align="start" border="solid" border-width="1px" number-columns-spanned="3">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
											Polje broj III - Pronalazač &#160; &#160; &#160;
												
												
												
												
												
												<xsl:if test="$pronalazac_naveden">
													<fo:inline font-size="12pt" color="darkgreen">Pronalazač ŽELI da bude naveden</fo:inline>
												</xsl:if>
												<xsl:if test="not($pronalazac_naveden)">
													<fo:inline font-size="12pt" color="darkred">Pronalazač NE ŽELI da bude naveden</fo:inline>
												</xsl:if>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<xsl:if test="$pronalazac_naveden">
										<fo:table-row>
											<fo:table-cell text-align="start" border-top="none" border-right="solid" border-width="1px">
												<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
													<fo:inline font-size="10pt">Ime i prezime: 
															
														
														
														
														
														<fo:inline font-size="13pt">
															<xsl:value-of select="//pronalazac/fizicko_lice/ime" />&#160;
																
															
															
															
															
															<xsl:value-of select="//pronalazac/fizicko_lice/prezime" />
														</fo:inline>
													</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="start" border-top="none" border-right="solid" border-width="1px">
												<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
													<fo:block font-size="10pt">Država: 
														
														
														
														
														
														<fo:inline font-size="13pt">
															<xsl:value-of select="//pronalazac//drzava" />
														</fo:inline>
													</fo:block>
													<fo:block font-size="10pt">Mesto: 
														
														
														
														
														
														<fo:inline font-size="13pt">
															<xsl:value-of select="//pronalazac//mesto" />
														</fo:inline>
													</fo:block>
													<fo:block font-size="10pt">Ulica i broj: 
														
														
														
														
														
														<fo:inline font-size="13pt">
															<xsl:value-of select="//pronalazac//ulica" />&#160;
															
															
															
															
															
															<xsl:value-of select="//pronalazac//broj" />
														</fo:inline>
													</fo:block>
													<fo:block font-size="10pt">Poštanski broj: 
														
														
														
														
														
														<fo:inline font-size="13pt">
															<xsl:value-of select="//pronalazac//postanski_broj" />
														</fo:inline>
													</fo:block>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="start" border-top="none" border-width="1px">
												<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
													<fo:block font-size="10pt">Broj telefona: 
														
														
														
														
														
														<fo:inline font-size="13pt">
															<xsl:value-of select="//pronalazac//broj_telefona" />
														</fo:inline>
													</fo:block>
													<fo:block font-size="10pt">Broj faksa: 
														
														
														
														
														
														<fo:inline font-size="13pt">
															<xsl:value-of select="//pronalazac//broj_faksa" />
														</fo:inline>
													</fo:block>
													<fo:block font-size="10pt">E-mail: 
														
														
														
														
														
														<fo:inline font-size="13pt">
															<xsl:value-of select="//pronalazac//e-mail" />
														</fo:inline>
													</fo:block>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</xsl:if>
								</fo:table-body>
							</fo:table>
						</fo:block>
					</xsl:if>
					<fo:block>
						<fo:table table-layout="fixed" width="100%" border="solid black" border-width="2px" font-family="Arial" margin-bottom="30px" keep-together.within-page="1">
							<fo:table-column column-width="33%" />
							<fo:table-column column-width="33%" />
							<fo:table-column column-width="34%" />
							<fo:table-body>
								<xsl:variable name="punomocnik_zastupanje" select="//punomocnik_zastupanje = true()"/>
								<xsl:variable name="punomocnik_prijem_pismena" select="//punomocnik_prijem_pismena = true()"/>
								<xsl:variable name="zajednicki_predstavnik" select="//zajednicki_predstavnik = true()"/>
								<fo:table-row>
									<fo:table-cell text-align="start" border="solid" border-width="1px" number-columns-spanned="3">
										<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
											Polje broj IV
											
											
											
											<xsl:if test="$punomocnik_zastupanje">
												<fo:block text-align="left" margin-top="6px" margin-left="10px" margin-bottom="12px" font-size="12pt" color="darkgreen">Punomoćnik za zastupanje</fo:block>
											</xsl:if>
											<xsl:if test="$punomocnik_prijem_pismena">
												<fo:block text-align="left" margin-top="6px" margin-left="10px" margin-bottom="12px" font-size="12pt" color="darkgreen">Punomoćnik za prijem pismena</fo:block>
											</xsl:if>
											<xsl:if test="$zajednicki_predstavnik">
												<fo:block text-align="left" margin-top="6px" margin-left="10px" margin-bottom="12px" font-size="12pt" color="darkgreen">Zajednički predstavnik</fo:block>
											</xsl:if>
											<xsl:if test="(not($punomocnik_zastupanje)) and (not($punomocnik_prijem_pismena)) and (not($zajednicki_predstavnik))">
												<fo:block text-align="left" margin-top="6px" margin-left="10px" margin-bottom="12px" font-size="12pt" color="darkred">Punomoćnik nije unesen</fo:block>
											</xsl:if>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<xsl:if test="($punomocnik_zastupanje) or ($punomocnik_prijem_pismena) or ($zajednicki_predstavnik)">
									<fo:table-row>
										<fo:table-cell text-align="start" border-top="none" border-right="solid" border-width="1px">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
												<xsl:if test="//punomocnik/fizicko_lice">
													<fo:inline font-size="10pt">Ime i prezime: 
													
																	
														
														
														
														<fo:inline font-size="13pt">
															<xsl:value-of select="//punomocnik/fizicko_lice/ime" />&#160;
														
																		
															
															
															
															<xsl:value-of select="//punomocnik/fizicko_lice/prezime" />
														</fo:inline>
													</fo:inline>
												</xsl:if>
												<xsl:if test="//punomocnik/poslovno_ime">
													<fo:inline font-size="10pt">Poslovno ime: 
														
														
														
														<fo:inline font-size="13pt">
															<xsl:value-of select="//punomocnik/poslovno_ime" />
														</fo:inline>
													</fo:inline>
												</xsl:if>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="start" border-top="none" border-right="solid" border-width="1px">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
												<fo:block font-size="10pt">Mesto: 
												
																
													
													
													
													<fo:inline font-size="13pt">
														<xsl:value-of select="//punomocnik//mesto" />
													</fo:inline>
												</fo:block>
												<fo:block font-size="10pt">Ulica i broj: 
												
																
													
													
													
													<fo:inline font-size="13pt">
														<xsl:value-of select="//punomocnik//ulica" />&#160;
														
														
														
														<xsl:value-of select="//punomocnik//broj" />
													</fo:inline>
												</fo:block>
												<fo:block font-size="10pt">Poštanski broj: 
												
																
													
													
													
													<fo:inline font-size="13pt">
														<xsl:value-of select="//punomocnik//postanski_broj" />
													</fo:inline>
												</fo:block>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="start" border-top="none" border-width="1px">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
												<fo:block font-size="10pt">Broj telefona: 
												
																
													
													
													
													<fo:inline font-size="13pt">
														<xsl:value-of select="//punomocnik//broj_telefona" />
													</fo:inline>
												</fo:block>
												<fo:block font-size="10pt">E-mail: 
													
													
													
													<fo:inline font-size="13pt">
														<xsl:value-of select="//punomocnik//e-mail" />
													</fo:inline>
												</fo:block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:if>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<xsl:if test="//adresa_za_dostavljanje">
						<fo:block>
							<fo:table table-layout="fixed" width="100%" border="solid black" border-width="2px" font-family="Arial" margin-bottom="30px">
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell text-align="start" border="solid" border-width="1px">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
												Polje broj V - Adresa za dostavljanje
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell text-align="start" border-top="none" border-right="solid" border-width="1px">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
												<fo:block font-size="10pt">Mesto: 
												
																
													
													
													
													<fo:inline font-size="13pt">
														<xsl:value-of select="//adresa_za_dostavljanje/mesto" />
													</fo:inline>
												</fo:block>
												<fo:block font-size="10pt">Ulica i broj: 
												
																
													
													
													
													<fo:inline font-size="13pt">
														<xsl:value-of select="//adresa_za_dostavljanje/ulica" />&#160;
														
														
														
														<xsl:value-of select="//adresa_za_dostavljanje/broj" />
													</fo:inline>
												</fo:block>
												<fo:block font-size="10pt">Poštanski broj: 
												
																
													
													
													
													<fo:inline font-size="13pt">
														<xsl:value-of select="//adresa_za_dostavljanje/postanski_broj" />
													</fo:inline>
												</fo:block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:block>
					</xsl:if>
					<fo:block>
						<fo:table table-layout="fixed" width="100%" border="solid black" border-width="2px" font-family="Arial" margin-bottom="30px">
							<fo:table-body>
								<xsl:variable name="elektronski" select="//elektronski = true()"/>
								<xsl:variable name="papirno" select="//papirno = true()"/>
								<fo:table-row>
									<fo:table-cell text-align="start" border="solid" border-width="1px">
										<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
												Polje broj VI - Način dostavljanja
											</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="start" border-top="none" border-right="solid" border-width="1px">
										<xsl:if test="$elektronski">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px" color="darkgreen">
													Podnosilac prijave je saglasan da zavod vrši dostavljanje pismena isključivo elektronskim putem u formi elektronskog dokumenta (u ovom slučaju neophodna je registracija na portalu "eUprave")
												</fo:block>
										</xsl:if>
										<xsl:if test="$papirno">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px" color="darkgreen">
													Podnosilac prijave je saglasan da zavod vrši dostavljanje pismena u papirnoj formi
												</fo:block>
										</xsl:if>
										<xsl:if test="(not($elektronski)) and (not($papirno))">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px" color="darkred">
													Podnosilac prijave nije pružio saglasnost za dostavu
												</fo:block>
										</xsl:if>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					<xsl:variable name="dopunska" select="//dopunska_prijava = true()"/>
					<xsl:variable name="izdvojena" select="//izdvojena_prijava = true()"/>
					<xsl:if test="($dopunska) or ($izdvojena)">
						<fo:block>
							<fo:table table-layout="fixed" width="100%" border="solid black" border-width="2px" font-family="Arial" margin-bottom="30px">
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell text-align="start" border="solid" border-width="1px">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
												Polje broj VII - &#160;
												
												
												
												<xsl:if test="$dopunska">
													<fo:inline color="darkgreen">
														Prijava je dopunska
													</fo:inline>
												</xsl:if>
												&#160;
												
												
												
												<xsl:if test="$izdvojena">
													<fo:inline color="darkgreen">
														Prijava je izdvojena
													</fo:inline>
												</xsl:if>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell text-align="start" border-top="none" border-right="solid" border-width="1px">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="10px">
												Broj prvobitne prijave/osnovne prijave, odnosno osnovnog patenta:&#160;
												
												
												
												<fo:inline font-size="16pt">
													<xsl:value-of select="//broj_osnovne_prijave" />
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell text-align="start" border-top="none" border-right="solid" border-width="1px">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px">
												Datum podnošenja prvobitne prijave/osnovne prijave:&#160;
												
												
												
												<fo:inline font-size="16pt">
													<xsl:value-of select="//datum_osnovne_prijave" />
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:block>
					</xsl:if>
					<xsl:if test="(count(//detalji_ranije_prijave) &gt; 0)">
						<fo:block>
							<fo:table table-layout="fixed" width="100%" border="solid black" border-width="2px" font-family="Arial">
								<fo:table-column column-width="33%" />
								<fo:table-column column-width="33%" />
								<fo:table-column column-width="34%" />
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell text-align="start" border="solid" border-width="1px" number-columns-spanned="3">
											<fo:block text-align="left" font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="16px" >
												Polje broj VIII - ZAHTEV ZA PRIZNANJE PRVENSTVA IZ RANIJIH PRIJAVA
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell text-align="center" border-top="none" border-right="solid" border-width="1px">
											<fo:block font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="10px">
												Datum podnošenja ranije prijave
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="center" border-top="none" border-right="solid" border-width="1px">
											<fo:block font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="10px">
												Broj ranije prijave
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="center" border-top="none" border-right="solid" border-width="1px">
											<fo:block font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="10px">
												Dvoslovna oznaka države, regionalne ili međunarodne organizacije
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<xsl:for-each select="//detalji_ranije_prijave">
										<fo:table-row>
											<fo:table-cell text-align="center" border="solid" border-width="1px">
												<fo:block font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="6px">
													<xsl:value-of select="datum_podnosenja"/>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" border="solid" border-width="1px">
												<fo:block font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="6px">
													<xsl:value-of select="broj_prijave"/>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" border="solid" border-width="1px">
												<fo:block font-size="10pt" margin-top="6px" margin-left="6px" margin-bottom="6px">
													<xsl:value-of select="dvoslovna_oznaka"/>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</xsl:for-each>
								</fo:table-body>
							</fo:table>
						</fo:block>
					</xsl:if>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>