package com.moviescatalog.core.di



import android.util.Log
import com.moviescatalog.core.util.Constants
import com.moviescatalog.data.remote.api.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val response = chain.proceed(originalRequest)
                val responseBody = response.body?.string()

                Log.d("API_RESPONSE", "URL: ${originalRequest.url}")
                Log.d("API_RESPONSE", "Response: $responseBody")
                val url = originalRequest.url
                    .newBuilder()
                    .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                    .build()

                val newRequest = originalRequest.newBuilder().url(url).build()

                chain.proceed(newRequest)
            }
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApiService =
        retrofit.create(MovieApiService::class.java)
}