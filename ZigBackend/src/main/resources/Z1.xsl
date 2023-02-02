<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"

    xmlns:b="http://www.tim777.rs/dokumentZ1"
    version = "2.0">


    <xsl:template match="/">
        <html>
            <head>

                <style>
                    table{
                    padding-top: 50px;
                    margin-left: 15%;
                    width: 70%;
                    }
                    p{
                    width: 70%;
                    margin-left: 15%;
                    }

                    td{
                    border: 1pt solid black;
                    padding: 5px;
                    margin: 0;
                    }
                    .title{
                    padding-top: 50px;
                    font-weight: bold;
                    font-size: large;
                    text-align: center;
                    }
                </style>
            </head>
            <body>
                <p class="title">
                    ZAHTEV ZA PRIZANJE ZIGA<br />
                    Zavod za inktealnu svojinu,Knjeginja Ljubica 6, Beograd
                </p>
                <table>

                    <tr>
                        <td colspan="23">
                            1. Podnosilac prijave: ime i prezime/poslovno ime, ulica i broj, poštanski broj, mesto,
                            država prebivališta/sedišta ideeee li:
                        </td>
                    </tr>
                    <tr style="height: 100px;">
                        <td colspan="23">
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:ime"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:prezime"/> ,
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:adresa/b:ulica"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:adresa/b:broj"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:adresa/b:postanski_broj"/> ,
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:adresa/b:drzava"/>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="7">
                            Telefon:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:telefon"/>
                        </td>
                        <td colspan="9">
                            E-mail:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:email"/>
                        </td>
                        <td colspan="9">
                            Faks:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:fax"/>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="23">
                            2. Punomoćnik: ime i prezime/poslovno ime, ulica i broj, poštanski broj, mesto, država
                            prebivališta/sedišta:
                        </td>
                    </tr>
                    <tr style="height: 100px;">
                        <td colspan="23">
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:ime"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:prezime"/> ,
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:adresa/b:ulica"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:adresa/b:broj"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:adresa/b:postanski_broj"/> ,
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:punomocnik/b:adresa/b:drzava"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="7">
                            Telefon:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:telefon"/>
                        </td>
                        <td colspan="9">
                            E-mail:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:email"/>
                        </td>
                        <td colspan="9">
                            Faks:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:fax"/>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="23">
                            3. Podaci o zajedničkom predstavniku ako postoji više podnosilaca prijave:
                        </td>
                    </tr>
                    <tr style="height: 100px;">
                        <td colspan="23">
                           <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:ime"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:prezime"/> ,
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:adresa/b:ulica"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:adresa/b:broj"/>
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:adresa/b:postanski_broj"/> ,
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zajednicki_posrednik/b:adresa/b:drzava"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="7">
                            Telefon:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:telefon"/>
                        </td>
                        <td colspan="9">
                            E-mail:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:email"/>
                        </td>
                        <td colspan="9">
                            Faks:<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:podnosilac/b:kontakt/b:fax"/>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="11">
                            4. Prijava se podnosi za (upisati X):
                        </td>
                        <td rowspan="2" colspan="12">
                            v) izgled znaka:
                        </td>
                    </tr>
                    <tr>
                        <td rowspan="3" >
                            a)
                        </td>
                        <td colspan="7">
                            individualni zig
                        </td>
                        <td colspan="3">

                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:tip_ziga='individualni_zig'">
                                X
                            </xsl:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="7">kolektivni zig</td>
                        <td colspan="3">

                        </td>
                        <td colspan="12" rowspan="11">
                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:tip_ziga='kolektivni_zig'">
                                X
                            </xsl:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="7">
                            zig generacije
                        </td>
                        <td colspan="3">
                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:tip_ziga='zig_garancije'">
                                X
                            </xsl:if>

                        </td>
                    </tr>
                    <tr>
                        <td rowspan="5">
                            b)
                        </td>
                        <td colspan="7">
                            verbalni znak
                        </td>
                        <td colspan="3">
                                                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:vrsta_znaka='verbalni'">
                                                                X
                                                            </xsl:if>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="7">
                        grafički znak; boju, kombinaciju boja
                        </td>
                        <td colspan="3">
                                                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:vrsta_znaka='graficki'">
                                                                X
                                                            </xsl:if>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="7">
                            kombinovani znak
                        </td>
                        <td colspan="3">
                                                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:vrsta_znaka='kombinovani'">
                                                                X
                                                            </xsl:if>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="7">
                            trodimenzionalni znak
                        </td>
                        <td colspan="3">
                                                                <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:vrsta_znaka='trodimenzionalni'">
                                                                    X
                                                                </xsl:if>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="7">
                            druga vrska znaka
                        </td>
                        <td colspan="3">
                                                            <xsl:if test="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:vrsta_znaka='drugo'">
                                                                X
                                                            </xsl:if>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="11">
                            5. Naznačenje boje, odnosno boja iz kojih se znak
                            sastoji:
                                                    <xsl:for-each select="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:boje_znaka">
                                                        <xsl:value-of select="./b:boja"/>,
                                                    </xsl:for-each>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="11">
                            6. Transliteracija znaka*: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:transliteracija"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="11">
                            7. Prevod znaka*: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:prevod"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="11">
                            8. Opis znaka: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:opis_ziga/b:znak/b:opis_znaka"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="23">
                            9. Zatrazeni brojeve klasa robe i usluga prema Ničanskoj klasifikaciji :
                        </td>
                    </tr>
                    <tr>
                        <xsl:for-each select="b:zahtev_za_priznanje_ziga/b:brojevi_klasa_robe_usluga/b:broj">
                            <td>
                                <xsl:value-of select="."/>
                            </td>
                        </xsl:for-each>
                    </tr>
                    <tr>
                        <td colspan="23">
                            10. Zatraženo pravo prvenstva i osnov:<br />
                            Pravo: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zatrazeno_pravo_prvenstva/b:pravo"/><br />
                            Osnov: <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:zatrazeno_pravo_prvenstva/b:osnov"/>
                        </td>
                    </tr>
                </table>

                <table>
                    <tr>
                        <td>
                            11. Plaćene takse: Dinara Potpis podnosioca zahteva
                        </td>
                        <td>
                            Dinara
                        </td>
                        <td rowspan="4">
                            Potpis podnosioca zahteva<br />
                            * Pečat, ukoliko je potreban u skladu sa zakonom
                        </td>
                    </tr>
                    <tr>
                        <td>
                            a) osnovna taksa
                        </td>
                        <td>
 <xsl:value-of select="b:zahtev_za_priznanje_ziga/b:placanje/b:onsovna_taksa"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            b) za________klasa
                            v) za grafičko rešenje
                        </td>
                        <td>
<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:placanje/b:graficko_resenje"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            UKUPNO
                        </td>
                        <td>
<xsl:value-of select="b:zahtev_za_priznanje_ziga/b:placanje/b:ukupno"/>
                        </td>
                    </tr>
                </table>

                <table>
                    <tr>
                        <td colspan="3">
                            POPUNjAVA ZAVOD
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            Prilozi uz zahtev:
                        </td>
                        <td rowspan="9">
                            Broj prijave žiga:<br />
                             <xsl:value-of select="b:zahtev_za_priznanje_ziga/@broj_zahteva" />
                            <br />Datum podnošenja:
                            <xsl:value-of select="b:zahtev_za_priznanje_ziga/@datum_podnosenja" />

                        </td>
                    </tr>
                    <tr>
                        <td>Primerak znaka </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>Spisak robe i usluga** </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>Punomoćje  </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>Generalno punomoćje ranije priloženo </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>Punomoćje će biti naknadno dostavljeno  </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>Opšti akt o kolektivnom žigu/žigu garancije  </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>Dokaz o pravu prvenstva</td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>Dokaz o uplati takse </td>
                        <td>

                        </td>
                    </tr>
                </table>

                <p margin-top="150px">
                    **Uz zaokruživanje broja klase robe/usluga Ničanske klasifikacije u rubrici 9 dostavlja se i spisak koji
                    sadrži konkretne nazive robe koju podnosilac prijave proizvodi, odnosno usluga koje pruža. U cilju
                    određenja obima zaštite koja se stiče žigom, spisak treba da sadrži jasne i precizne nazive robe i
                    usluga. U tu svrhu mogu se koristiti pojmovi iz detaljne Liste roba i usluga ili MGS Manager aplikacije,
                    dostupnih na sajtu Zavoda. Ukoliko se u spisak unose termini iz Liste klasa Ničanske klasifikacije,
                    zaštita obuhvata samo tako imenovane, konkretne robe/usluge u njihovom jasnom i nedvosmislenom
                    značenju.
                </p>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>