<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:b="http://www.tim777.rs/dokumentZ1"
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="zig-page">
                    <fo:region-body margin="0.75in" font-size="10px"/>
                    
                </fo:simple-page-master>
                <fo:simple-page-master master-name="second-zig-page">
                    <fo:region-body margin="0.75in" font-size="10px"/>

                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="zig-page">
                <fo:flow flow-name="xsl-region-body" font-size="10px">
                    <fo:block font-family="sans-serif" font-size="15px" font-weight="bold" text-align="center">
                        ZAHTEV ZA PRIZNANJE ŽIGA
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="10px" font-weight="bold" text-align="center" padding="0 0 20pt 0">
                        Zavod za inktealnu svojinu,Knjeginja Ljubica 6, Beograd
                    </fo:block>
                    <fo:block border="1pt solid black">
                        <fo:block  padding="5pt 0 0 0" border-bottom="1pt solid black" >
                            1. Podnosilac prijave: ime i prezime/poslovno ime, ulica i broj, poštanski broj, mesto,
                            država prebivališta/sedišta:
                        </fo:block>
                        
                        <fo:block  padding="10pt 0" border-bottom="1px solid black">
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:ime"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:prezime"/> ,
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:adresa/b:ulica"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:adresa/b:broj"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:adresa/b:postanski_broj"/> ,
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:adresa/b:drzava"/>

                        </fo:block>
                        <fo:table >
                            <fo:table-column column-width="33%"/>
                            <fo:table-column column-width="33%"/>                            
                            <fo:table-column column-width="33%"/>
                            <fo:table-body>
                                <fo:table-row>
                                <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black">
                                    <fo:block >
                                        Telefon: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:telefon"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black">
                                    <fo:block >
                                        E-mail: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:email"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell  padding="5pt 0 0 0" >
                                    <fo:block >
                                        Faks:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:kontakt/b:fax"/>
                                    </fo:block>
                                </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table> 
                    </fo:block>

                    <fo:block border="1pt solid black">
                        <fo:block  padding="5pt 0 0 0" border-bottom="1pt solid black" >
                            2. Punomoćnik: ime i prezime/poslovno ime, ulica i broj, poštanski broj, mesto, država
 prebivališta/sedišta:
                        </fo:block>
                        
                        <fo:block  padding="10pt 0" border-bottom="1pt solid black" >
<!--                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:punomocnik/@type = 'TPravno_lice'">-->
<!--                                <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:naziv"/>,-->
<!--                            </xsl:if>-->
<!--                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:punomocnik/@type = 'TFizicko_lice'">-->
                                <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:ime"/>
                                <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:prezime"/> ,
<!--                            </xsl:if>-->

                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:adresa/b:ulica"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:adresa/b:broj"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:adresa/b:postanski_broj"/> ,
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:adresa/b:drzava"/>
                        </fo:block>
                        <fo:table >
                            <fo:table-column column-width="33%"/>
                            <fo:table-column column-width="33%"/>                            
                            <fo:table-column column-width="33%"/>
                            <fo:table-body>
                                <fo:table-row>
                                <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black">
                                    <fo:block >
                                        Telefon: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:kontakt/b:telefon"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black">
                                    <fo:block >
                                        E-mail: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:kontakt/b:email"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell  padding="5pt 0 0 0" >
                                    <fo:block >
                                        Faks:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:kontakt/b:fax"/>
                                    </fo:block>
                                </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table> 
                    </fo:block>

                    <fo:block border="1pt solid black">
                        <fo:block  padding="5pt 0 0 0" border-bottom="1pt solid black" >
                            3. Podaci o zajedničkom predstavniku ako postoji više podnosilaca prijave:
                        </fo:block>
                        
                        <fo:block  padding="10pt 0" border-bottom="1pt solid black" >
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:ime"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:prezime"/> ,
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:adresa/b:ulica"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:adresa/b:broj"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:adresa/b:postanski_broj"/> ,
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:adresa/b:drzava"/>
                        </fo:block>
                        <fo:table >
                            <fo:table-column column-width="33%"/>
                            <fo:table-column column-width="33%"/>                            
                            <fo:table-column column-width="33%"/>
                            <fo:table-body>
                                <fo:table-row>
                                <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black">
                                    <fo:block >
                                        Telefon: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:kontakt/b:telefon"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black">
                                    <fo:block >
                                        E-mail: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:kontakt/b:email"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell  padding="5pt 0 0 0" >
                                    <fo:block >
                                        Faks:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:kontakt/b:fax"/>
                                    </fo:block>
                                </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table> 
                    </fo:block>
                    <fo:block>
                        <fo:table >
                            <fo:table-column column-width="50%" border="1pt solid black"/>
                            <fo:table-column column-width="50%" border="1pt solid black"/>
                            <fo:table-body border="1pt solid black">
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                        <fo:block>4. Prijava se podnosi za (upisati X):</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="15pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black" >
                                        <fo:block>v) izgled znaka:</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell >
                                        <fo:block>
                                            <fo:table width="100%" table-layout="fixed">
                                                <fo:table-column column-width="10%"/>
                                                <fo:table-column column-width="70%"/>
                                                <fo:table-column column-width="20%"/>
                                                <fo:table-body>
                                                    <fo:table-row>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black" number-rows-spanned="3">
                                                        <fo:block text-align="center">a)</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>individualni žig</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block text-align="center">
                                                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:tip_ziga='individualni_zig'">
                                                                X
                                                            </xsl:if>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>kolektivni žig </fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block text-align="center">
                                                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:tip_ziga='kolektivni_zig'">
                                                                X
                                                            </xsl:if>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>žig garancije</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block text-align="center">
                                                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:tip_ziga='zig_garancije'">
                                                                X
                                                            </xsl:if>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                            <fo:table width="100%" table-layout="fixed">
                                                <fo:table-column column-width="10%"/>
                                                <fo:table-column column-width="70%"/>
                                                <fo:table-column column-width="20%"/>
                                                <fo:table-body>
                                                    <fo:table-row>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black" number-rows-spanned="5">
                                                        <fo:block text-align="center">b)</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>verbalni znak (znak u reči)</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block text-align="center">
                                                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:vrsta_znaka='verbalni'">
                                                                X
                                                            </xsl:if>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>grafički znak; boju, kombinaciju boja</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block text-align="center">
                                                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:vrsta_znaka='graficki'">
                                                                X
                                                            </xsl:if>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>kombinovani znak</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block text-align="center">
                                                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:vrsta_znaka='kombinovani'">
                                                                X
                                                            </xsl:if>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                            <fo:block>trodimenzionalni znak</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                            <fo:block text-align="center">
                                                                <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:vrsta_znaka='trodimenzionalni'">
                                                                    X
                                                                </xsl:if>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>drugu vrstu znaka (navesti koju)</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell  padding="5pt 0 0 0" border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block text-align="center">
                                                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:vrsta_znaka='drugo'">
                                                                X
                                                            </xsl:if>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                            <fo:block  padding="5pt 0 0 0" border-bottom="1pt solid black">
                                                5. Naznačenje boje, odnosno boja iz kojih se znak
                                                sastoji:
                                                    <xsl:for-each select="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:boje_znaka">
                                                        <xsl:value-of select="./b:boja"/>,
                                                    </xsl:for-each>
                                            </fo:block>
                                            <fo:block  padding="5pt 0 0 0" border-bottom="1pt solid black">
                                                6. Transliteracija znaka*:
                                                <fo:block>
                                                    <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:transliteracija"/>
                                                </fo:block>
                                            </fo:block>
                                            <fo:block  padding="5pt 0 0 0" border-bottom="1pt solid black">
                                                7. Prevod znaka*:
                                                <fo:block>
                                                    <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:prevod"/>
                                                </fo:block>
                                            </fo:block>
                                            <fo:block  padding="5pt 0 0 0" border-bottom="1pt solid black">
                                                8. Opis znaka:
                                                <fo:block>
                                                    <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:opis_znaka"/>
                                                </fo:block>
                                            </fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" >
                                        <fo:block> </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block border="1pt solid black"  padding="5pt 0 0 0">
                        9. Zatraženi brojeve klasa robe i usluga prema Ničanskoj klasifikaciji :

<!--                        <xsl:for-each select="b:zahtev_za_priznanje_ziga/b:brojevi_klasa_robe_usluga">-->
<!--                            <xsl:value-of select="./b:broj"/> ,-->
<!--                        </xsl:for-each>-->

                    </fo:block>
                    <fo:block>
                        <xsl:variable name="list">
                            <xsl:for-each select="b:zahtev_za_priznanje_ziga/b:brojevi_klasa_robe_usluga/b:broj">
                                <xsl:text>|</xsl:text>
                                 <xsl:value-of select="."/>
                                <xsl:text>|</xsl:text>
                            </xsl:for-each>
                        </xsl:variable>
                        <fo:table>
                            <xsl:for-each select="1 to 23">
                                <fo:table-column />
                            </xsl:for-each>
                            <fo:table-body>
                                <fo:table-row>
                                    <xsl:for-each select="1 to 23">
                                        <fo:table-cell  padding="5pt 0 0 0" border="1pt solid black">
                                            <fo:block-container text-align="center" >
                                                <fo:block>
                                                <xsl:value-of select="."/>
                                                <xsl:variable name="currentValue" select="string(.)"/>
                                                <xsl:if test="contains($list,concat('|',$currentValue,'|'))">
<!--                                                    <fo:block-container>-->
                                                        <fo:block>
                                                            X
                                                        </fo:block>
                                                </xsl:if>
                                                </fo:block>
                                            </fo:block-container>
                                        </fo:table-cell>
                                    </xsl:for-each>
                                </fo:table-row>
                                <fo:table-row>
                                    <xsl:for-each select="24 to 45" >
                                        <fo:table-cell  padding="5pt 0 0 0" border="1pt solid black">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="."/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </xsl:for-each>

                                    <fo:table-cell  padding="5pt 0 0 0" border="1pt solid black">
                                        <fo:block>

                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block border="1pt solid black">
                        <fo:block  padding="5pt 0 0 0">
                            10. Zatraženo pravo prvenstva i osnov:
                        </fo:block>
                        <fo:block>
                            Pravo: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zatrazeno_pravo_prvenstva/b:pravo"/>
                            <fo:block>
                                Osnov: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zatrazeno_pravo_prvenstva/b:osnov"/>
                            </fo:block>

                        </fo:block>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
            <fo:page-sequence master-reference="second-zig-page">
                <fo:flow flow-name="xsl-region-body" font-size="10px">
                    <fo:block>
                        <fo:table border="1pt solid black">
                            <fo:table-column column-width="45%"/>
                            <fo:table-column column-width="10%"/>
                            <fo:table-column column-width="45%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            11. Plaćene takse:
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            Dinara
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black" number-rows-spanned="4">
                                        <fo:block text-align="center">
                                            Potpis podnosioca zahteva
                                            <fo:block padding="20pt 0 0 0">
                                                * Pečat, ukoliko je potreban u skladu sa zakonom
                                            </fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            a) osnovna taksa
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:placanje/b:onsovna_taksa"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            b) za________klasa
                                            <fo:block>
                                                v) za grafičko rešenje
                                            </fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:placanje/b:graficko_resenje"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            UKUPNO
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:placanje/b:ukupno"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block padding="40pt 0 0 0">
                        <fo:table  border="1pt solid black">
                            <fo:table-column column-width="50%"/>
                            <fo:table-column column-width="10%"/>
                            <fo:table-column column-width="40%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black" number-columns-spanned="3">
                                        <fo:block text-align="center">
                                            POPUNjAVA ZAVOD
                                        </fo:block>
                                    </fo:table-cell>

                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black" number-columns-spanned="2">
                                        <fo:block>
                                            Prilozi uz zahtev:
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black" number-rows-spanned="9" >
                                        <fo:block text-align="center" padding="35pt 0 0 0">
                                            Broj prijave žiga:
                                            <fo:block>
                                                <xsl:value-of select="b:zahtev_za_priznanje_ziga/@broj_zahteva" />
                                            </fo:block>
                                            <fo:block>
                                                Datum podnošenja:
                                            </fo:block>
                                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/@datum_podnosenja" />
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            Primerak znaka
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>

                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            Spisak robe i usluga**
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>

                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            Punomoćje
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>

                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            Generalno punomoćje ranije priloženo
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>

                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            Punomoćje će biti naknadno dostavljeno
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>

                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            Opšti akt o kolektivnom žigu/žigu garancije
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>

                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            Dokaz o pravu prvenstva
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>

                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>
                                            Dokaz o uplati takse
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding="5pt 0 0 0" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>

                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block padding="150pt 0 0 0">
                        **Uz zaokruživanje broja klase robe/usluga Ničanske klasifikacije u rubrici 9 dostavlja se i spisak koji
                        sadrži konkretne nazive robe koju podnosilac prijave proizvodi, odnosno usluga koje pruža. U cilju
                        određenja obima zaštite koja se stiče žigom, spisak treba da sadrži jasne i precizne nazive robe i
                        usluga. U tu svrhu mogu se koristiti pojmovi iz detaljne Liste roba i usluga ili MGS Manager aplikacije,
                        dostupnih na sajtu Zavoda. Ukoliko se u spisak unose termini iz Liste klasa Ničanske klasifikacije,
                        zaštita obuhvata samo tako imenovane, konkretne robe/usluge u njihovom jasnom i nedvosmislenom
                        značenju.
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
