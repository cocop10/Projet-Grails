package lecoincoin

import tp.mbds.com.Illustration
import tp.mbds.com.Role
import tp.mbds.com.SaleAd
import tp.mbds.com.User
import tp.mbds.com.UserRole

class BootStrap {

    def init = { servletContext ->
        def roleAdmin = new Role(authority: "ROLE_ADMIN").save()
        def roleUser = new Role(authority: "ROLE_USER").save()
        def roleMode = new Role(authority: "ROLE_MODERATEUR").save()

        def userAdmin = new User(username: "admin", password: "password").save()
        def userClient = new User(username: "user", password: "password").save()
        def userMode = new User(username: "moderateur", password: "password").save()

        UserRole.create(userAdmin,roleAdmin, true)
        UserRole.create(userClient,roleUser, true)
        UserRole.create(userMode,roleMode, true)

        (1..5).each {
            Integer index->
                def annonceInstance = new SaleAd(title: "title "+index, description: "description "+index,
                        longDescription: "longDescription "+index,
                        price: 100F*index)
                userClient.addToSaleAds(annonceInstance)
                (1..3).each {
                    annonceInstance.addToIllustrations(new Illustration(filename: "https:picsum.photos/200/300"))
                }
                userClient.addToSaleAds(annonceInstance)
        }

        userClient.save(flush: true, failOnError: true)
    }
    def destroy = {
    }
}
