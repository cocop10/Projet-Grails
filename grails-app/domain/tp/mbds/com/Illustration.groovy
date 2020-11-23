package tp.mbds.com

class Illustration {

    String filename

    static belongsTo = [saleAds: SaleAd]

    static constraints = {
        filename blank: false, nullable: false
    }
}
