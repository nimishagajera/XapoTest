package com.app.test.di.module

import android.content.Context
import com.app.test.network.APIService
import com.app.test.network.GenericException
import com.app.test.util.NetworkUtils
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
/*
* Module class to be inject for use network call using retrofit
*
* */
@Module
class NetworkModule {
    /**
     * okhttp method for connection and timeout
     */
    @Singleton
    @Provides
    fun getHttpClient(): OkHttpClient.Builder {
        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

        return okHttpClient.newBuilder()
    }

    /**
     * getRetrofitBuilder method
     */
    @Singleton
    @Provides
    fun getRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    /**
     * @param context
     * @param httpClient
     * @param retrofit
     */
    @Singleton
    @Provides
    fun getService(context: Context, httpClient: OkHttpClient.Builder, retrofit: Retrofit.Builder): APIService {
        httpClient.addInterceptor { chain: Interceptor.Chain ->

            if (!NetworkUtils.isNetworkAvailable(context))
                throw GenericException(context, SocketException())

            var originalRequest = chain.request()
            val newRequest: Request.Builder = originalRequest.newBuilder()
            originalRequest = newRequest.build()
            val response = chain.proceed(originalRequest)
            response
        }

        retrofit
                .baseUrl(APIService.BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.build().create<APIService>(APIService::class.java)
    }

}