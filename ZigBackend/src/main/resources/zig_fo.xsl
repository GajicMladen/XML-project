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
            </fo:layout-master-set>

            <fo:page-sequence master-reference="zig-page">
                <fo:flow flow-name="xsl-region-body" font-size="10px">
                    <fo:block font-family="sans-serif" font-size="15px" font-weight="bold" text-align="center">
                        ZAHTEV ZA PRIZNANJE ŽIGA
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="10px" font-weight="bold" text-align="center">
                        Zavod za inktealnu svojinu,Knjeginja Ljubica 6, Beograd
                    </fo:block>
                    <fo:block border="1pt solid black">
                        <fo:block border-bottom="1pt solid black" >
                            1. Podnosilac prijave: ime i prezime/poslovno ime, ulica i broj, poštanski broj, mesto,
                            država prebivališta/sedišta:
                        </fo:block>
                        
                        <fo:block  padding="10pt 0" border-bottom="1px solid black">
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:ime"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:prezime"/>
                        </fo:block>
                        <fo:table >
                            <fo:table-column column-width="33%"/>
                            <fo:table-column column-width="33%"/>                            
                            <fo:table-column column-width="33%"/>
                            <fo:table-body>
                                <fo:table-row>
                                <fo:table-cell border-right="1pt solid black">
                                    <fo:block >
                                        Telefon: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:telefon"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="1pt solid black">
                                    <fo:block >
                                        E-mail: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:email"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell >
                                    <fo:block >
                                        Faks:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:kontakt/b:fax"/>
                                    </fo:block>
                                </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table> 
                    </fo:block>

                    <fo:block border="1pt solid black">
                        <fo:block border-bottom="1pt solid black" >
                            2. Punomoćnik: ime i prezime/poslovno ime, ulica i broj, poštanski broj, mesto, država
 prebivališta/sedišta:
                        </fo:block>
                        
                        <fo:block  padding="10pt 0" border-bottom="1px solid black">
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:ime"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:prezime"/>
                        </fo:block>
                        <fo:table >
                            <fo:table-column column-width="33%"/>
                            <fo:table-column column-width="33%"/>                            
                            <fo:table-column column-width="33%"/>
                            <fo:table-body>
                                <fo:table-row>
                                <fo:table-cell border-right="1pt solid black">
                                    <fo:block >
                                        Telefon: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:telefon"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="1pt solid black">
                                    <fo:block >
                                        E-mail: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:email"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell >
                                    <fo:block >
                                        Faks:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:kontakt/b:fax"/>
                                    </fo:block>
                                </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table> 
                    </fo:block>

                    <fo:block border="1pt solid black">
                        <fo:block border-bottom="1pt solid black" >
                            3. Podaci o zajedničkom predstavniku ako postoji više podnosilaca prijave:
                        </fo:block>
                        
                        <fo:block  padding="10pt 0" border-bottom="1px solid black">
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:ime"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:prezime"/>
                        </fo:block>
                        <fo:table >
                            <fo:table-column column-width="33%"/>
                            <fo:table-column column-width="33%"/>                            
                            <fo:table-column column-width="33%"/>
                            <fo:table-body>
                                <fo:table-row>
                                <fo:table-cell border-right="1pt solid black">
                                    <fo:block >
                                        Telefon: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:telefon"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="1pt solid black">
                                    <fo:block >
                                        E-mail: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:email"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell >
                                    <fo:block >
                                        Faks:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:kontakt/b:fax"/>
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
                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                        <fo:block>4. Prijava se podnosi za (upisati X):</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black" >
                                        <fo:block>v) izgled znaka:</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                        <fo:block>
                                            <fo:table width="100%" table-layout="fixed">
                                                <fo:table-column column-width="33.33%"/>
                                                <fo:table-column column-width="33.33%"/>
                                                <fo:table-column column-width="33.33%"/>
                                                <fo:table-body>
                                                    <fo:table-row>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black" number-rows-spanned="3">
                                                        <fo:block>a)</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>individualni žig</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block> </fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>kolektivni žig </fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block> </fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>žig garancije</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block> </fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                            <fo:table width="100%" table-layout="fixed">
                                                <fo:table-column column-width="33.33%"/>
                                                <fo:table-column column-width="33.33%"/>
                                                <fo:table-column column-width="33.33%"/>
                                                <fo:table-body>
                                                    <fo:table-row>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black" number-rows-spanned="4">
                                                        <fo:block>b)</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>verbalni znak (znak u reči)</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block></fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>grafički znak; boju, kombinaciju boja</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block> </fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>kombinovani znak</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block> </fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block>drugu vrstu znaka (navesti koju)</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-bottom="1pt solid black" border-right="1pt solid black">
                                                        <fo:block> </fo:block>
                                                    </fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                            <fo:block border-bottom="1pt solid black">
                                                5. Naznačenje boje, odnosno boja iz kojih se znak
                                                sastoji:
                                                <fo:block></fo:block>
                                            </fo:block>
                                            <fo:block border-bottom="1pt solid black">
                                                6. Transliteracija znaka*:
                                                <fo:block></fo:block>
                                            </fo:block>
                                            <fo:block border-bottom="1pt solid black">
                                                7. Prevod znaka*:
                                                <fo:block></fo:block>
                                            </fo:block>
                                            <fo:block border-bottom="1pt solid black">
                                                8. Opis znaka:
                                                <fo:block></fo:block>
                                            </fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell >
                                        <fo:block> </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>



                    </fo:block>
<!--                    <fo:block>-->
<!--                        <xsl:variable name="numOfCols" select="23"/>-->
<!--                        <xsl:variable name="colWidth" select="4"/>-->
<!--                        <fo:table border="solid 1px black" number-columns-spanned="{$numOfCols}" font-family="serif" margin="50px auto 50px auto" >-->
<!--                            <xsl:for-each select="1 to $numOfCols">-->
<!--                                <fo:table-column column-width="{$colWidth}%"/>-->
<!--                            </xsl:for-each>-->
<!--                            <fo:table-body>-->
<!--                                <fo:table-row >-->
<!--                                    <xsl:for-each select="1 to $numOfCols">-->
<!--                                        <fo:table-cell >-->
<!--                                            <fo:block></fo:block>-->
<!--                                        </fo:table-cell>-->
<!--                                    </xsl:for-each>-->
<!--                                </fo:table-row>-->
<!--                                <fo:table-row border="1px solid black">-->
<!--                                    <fo:table-cell number-columns-spanned="{$numOfCols}">-->
<!--                                    <fo:block linefeed-treatment="preserve">-->
<!--                                            1. Podnosilac prijave: ime i prezime/poslovno ime, ulica i broj, poštanski broj, mesto,-->
<!--                                            država prebivališta/sedišta:-->
<!--                                    </fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                </fo:table-row>-->
<!--                                <fo:table-row border="1px solid black">-->
<!--                                    <fo:table-cell number-columns-spanned="{$numOfCols}">-->
<!--                                        <fo:block linefeed-treatment="preserve">-->
<!--                                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:ime"/>-->
<!--                                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:prezime"/>-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                </fo:table-row>-->
<!--                                <fo:table-row border="1px solid black">-->
<!--                                    <fo:table-cell number-columns-spanned="{floor($numOfCols div 3)}">-->
<!--                                        <fo:block linefeed-treatment="preserve">-->
<!--                                            Telefon: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:telefon"/>-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                    <fo:table-cell number-columns-spanned="{floor($numOfCols div 3)}">-->
<!--                                        <fo:block linefeed-treatment="preserve">-->
<!--                                            E-mail: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:email"/>-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell >-->
<!--                                    <fo:table-cell number-columns-spanned="{floor($numOfCols div 3)}">-->
<!--                                        <fo:block linefeed-treatment="preserve">-->
<!--                                            Faks: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:fax"/>-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                </fo:table-row>-->

<!--                                <fo:table-row border="1px solid black">-->
<!--                                    <fo:table-cell number-columns-spanned="{$numOfCols}">-->
<!--                                        <fo:block linefeed-treatment="preserve">-->
<!--                                            2. Punomoćnik: ime i prezime/poslovno ime, ulica i broj, poštanski broj, mesto, država-->
<!--                                            prebivališta/sedišta:-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell>-->

<!--                                </fo:table-row>-->
<!--                                <fo:table-row border="1px solid black">-->
<!--                                    <fo:table-cell  number-columns-spanned="{$numOfCols}">-->
<!--                                        <fo:block linefeed-treatment="preserve">-->
<!--                                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:ime"/>-->
<!--                                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:prezime"/>-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                </fo:table-row>-->
<!--                                <fo:table-row border="1px solid black">-->
<!--                                    <fo:table-cell number-columns-spanned="{floor($numOfCols div 3)}">-->
<!--                                        <fo:block linefeed-treatment="preserve">-->
<!--                                            Telefon:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:kontakt/b:telefon"/>-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                    <fo:table-cell number-columns-spanned="{floor($numOfCols div 3)}">-->
<!--                                        <fo:block linefeed-treatment="preserve">-->
<!--                                            E-mail:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:kontakt/b:email"/>-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                    <fo:table-cell number-columns-spanned="{floor($numOfCols div 3)}">-->
<!--                                        <fo:block linefeed-treatment="preserve">-->
<!--                                            Faks:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:kontakt/b:fax"/>-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                </fo:table-row>-->

<!--                                <fo:table-row border="1px solid black">-->
<!--                                    <fo:table-cell number-columns-spanned="{$numOfCols}">-->
<!--                                        <fo:block linefeed-treatment="preserve">-->
<!--                                            3. Podaci o zajedničkom predstavniku ako postoji više podnosilaca prijave:-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                </fo:table-row>-->
<!--                                <fo:table-row border="1px solid black">-->
<!--                                    <fo:table-cell>-->
<!--                                        <fo:block>-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                </fo:table-row>-->
<!--                                <fo:table-row border="1px solid black">-->
<!--                                    <fo:table-cell number-columns-spanned="floor($numOfCols div 4)">-->
<!--                                        <fo:block>-->
<!--                                            Telefon:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:kontakt/b:telefon"/>-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                    <fo:table-cell number-columns-spanned="{floor($numOfCols div 3)}">-->
<!--                                        <fo:block>-->
<!--                                            E-mail:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:kontakt/b:email"/>-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                    <fo:table-cell number-columns-spanned="{floor($numOfCols div 3)}">-->
<!--                                        <fo:block>-->
<!--                                            Faks:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:kontakt/b:fax"/>-->
<!--                                        </fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                </fo:table-row>-->

<!--                                <xsl:variable name="amount" select="sum(//b:book/b:price)"/>-->
<!--                                <xsl:variable name="count" select="count(//b:book)"/>-->
<!--                                <xsl:for-each select="b:bookstore/b:book">-->
<!--                                    <xsl:sort select="@category"/>-->
<!--                                    <xsl:sort select="b:price"/>-->
<!--                                    <fo:table-row border="1px solid darkgrey">-->
<!--                                        <fo:table-cell padding="10px">-->
<!--                                            <fo:block>-->
<!--                                                <xsl:value-of select="position()" />-->
<!--                                            </fo:block>-->
<!--                                        </fo:table-cell>-->

<!--                                        <fo:table-cell padding="10px">-->
<!--                                            <fo:block font-weight="bold">-->
<!--                                                <xsl:choose>-->
<!--                                                    <xsl:when test="b:price &lt; 40">-->
<!--                                                        * <xsl:value-of select="b:title"/>-->
<!--                                                    </xsl:when>-->
<!--                                                    <xsl:otherwise>-->
<!--                                                        <xsl:value-of select="b:title"/>-->
<!--                                                    </xsl:otherwise>-->
<!--                                                </xsl:choose>-->
<!--                                                <fo:inline vertical-align="super" font-size="50%">-->
<!--                                                    <xsl:value-of select="@category"/>-->
<!--                                                </fo:inline>-->
<!--                                            </fo:block>-->
<!--                                        </fo:table-cell>-->

<!--                                        <fo:table-cell padding="10px">-->
<!--                                            <xsl:if test="count(b:author) = 1">-->
<!--                                                <fo:block>- <xsl:value-of select="b:author"/></fo:block>-->
<!--                                            </xsl:if>-->
<!--                                            <xsl:if test="count(b:author) &gt; 1">-->
<!--                                                <xsl:for-each select="b:author">-->
<!--                                                    <fo:block>- <xsl:value-of select="text()"/></fo:block>-->
<!--                                                </xsl:for-each>-->
<!--                                            </xsl:if>-->
<!--                                        </fo:table-cell>-->

<!--                                        <fo:table-cell padding="10px">-->
<!--                                            <fo:block>-->
<!--                                                <xsl:value-of select="b:year"/>-->
<!--                                            </fo:block>-->
<!--                                        </fo:table-cell>-->

<!--                                        <fo:table-cell padding="10px">-->
<!--                                            <fo:block>-->
<!--                                                ($<xsl:value-of select="b:price"/>)-->
<!--                                            </fo:block>-->
<!--                                        </fo:table-cell>-->
<!--                                    </fo:table-row>-->
<!--                                </xsl:for-each>-->
<!--                                <fo:table-row>-->
<!--                                    <fo:table-cell background-color="#4caf50" font-family="sans-serif" color="white" font-size="16px" number-columns-spanned="4" padding="10px">-->
<!--                                        <fo:block>Average price:</fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                    <fo:table-cell background-color="#4caf50" font-family="sans-serif" color="white" font-size="16px" padding="10px">-->
<!--                                        <fo:block>$<xsl:value-of select="round($amount div $count * 100) div 100"/></fo:block>-->
<!--                                    </fo:table-cell>-->
<!--                                </fo:table-row>-->
<!--                            </fo:table-body>-->
<!--                        </fo:table>-->
<!--                    </fo:block>-->
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
