package yb.kinomaptestandroid.badges.data.api

import retrofit2.http.GET
import yb.kinomaptestandroid.badges.data.models.TestData

// Not hidden for test purpose
const val KINOMAP_TOKEN =
    "Y7pNWqI4nlYuGBILm46tqw57aKInntGTpzQau30To8WDSt6ZOU60GHWG8QSyWIs1TsFrnheftxBmmFWxR4eKhUWruEndo0aXaZVC6tn9fWhdBDb0ThVvmY6E"

interface KinomapService {

    @GET("badges/mobile-tech-test?appToken=$KINOMAP_TOKEN")
    suspend fun getTechTestData(): TestData

}