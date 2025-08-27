package yb.kinomaptestandroid.di

import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import yb.kinomaptestandroid.data.services.KinomapService
import java.util.concurrent.TimeUnit

fun provideHttpClient(): OkHttpClient = OkHttpClient
    .Builder()
    .readTimeout(30, TimeUnit.SECONDS)
    .connectTimeout(30, TimeUnit.SECONDS)
    .build()

fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonFactory: GsonConverterFactory
): Retrofit = Retrofit.Builder()
    .baseUrl("https://api.kinomap.dev/v4")
    .client(okHttpClient)
    .addConverterFactory(gsonFactory)
    .build()

fun provideKinomapService(
    retrofit: Retrofit
): KinomapService = retrofit.create(KinomapService::class.java)

val serviceModule = module {
    singleOf(::provideHttpClient)
    singleOf(::provideGsonConverterFactory)
    single { provideRetrofit(get(), get()) }
    single { provideKinomapService(get()) }
}