package br.com.aline.contactbook

enum class Region {
    RO,
    AC,
    AM,
    RR,
    PA,
    AP,
    TO,
    MA,
    PI,
    CE,
    RN,
    PB,
    PE,
    AL,
    SE,
    MG,
    ES,
    RJ,
    SP,
    PR,
    SC,
    RS,
    MS,
    MT,
    GO,
    DF
}
fun getRegionList():List<Region>{
    val regionList = mutableListOf<Region>()
    regionList.add(Region.RO)
    regionList.add(Region.AC)
    regionList.add(Region.AM)
    regionList.add(Region.RR)
    regionList.add(Region.PA)
    regionList.add(Region.AP)
    regionList.add(Region.TO)
    regionList.add(Region.MA)
    regionList.add(Region.PI)
    regionList.add(Region.CE)
    regionList.add(Region.RN)
    regionList.add(Region.PB)
    regionList.add(Region.PE)
    regionList.add(Region.AL)
    regionList.add(Region.SE)
    regionList.add(Region.MG)
    regionList.add(Region.ES)
    regionList.add(Region.RJ)
    regionList.add(Region.SP)
    regionList.add(Region.PR)
    regionList.add(Region.SC)
    regionList.add(Region.RS)
    regionList.add(Region.MS)
    regionList.add(Region.MT)
    regionList.add(Region.GO)
    regionList.add(Region.DF)

    return regionList

}