<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" />

    <xsl:template match="/">
        <ListGems>
            <xsl:apply-templates />
        </ListGems>
    </xsl:template>

    <xsl:template match="gem">
        <origin_product>
            <continent>
                <xsl:value-of select="Origin/continent"/>
            </continent>

            <country>
                <xsl:value-of select="Origin/country"/>
            </country>

            <region>
                <xsl:value-of select="Origin/region"/>
            </region>

            <gem GemID="{@GemID}">
                <Name>
                    <xsl:value-of select="Name"/>
                </Name>

                <Value weightName="{Value/@weightName}">
                    <xsl:value-of select="Value"/>
                </Value>

                <Preciousness>
                    <xsl:value-of select="Preciousness"/>
                </Preciousness>

                <VisualParams>
                    <colorName>
                        <xsl:value-of select="VisualParams/colorName"/>
                    </colorName>
                    <transparencyValue weightTransparency="{VisualParams/transparencyValue/@weightTransparency}">
                        <xsl:value-of select="VisualParams/transparencyValue"/>
                    </transparencyValue>
                    <quantitySides quantitySideName="{VisualParams/quantitySides/@quantitySideName}">
                        <xsl:value-of select="VisualParams/quantitySides"/>
                    </quantitySides>
                </VisualParams>
            </gem>

        </origin_product>
    </xsl:template>

</xsl:stylesheet>