package com.saurabhbhardwaj.kotlincoroutines.module

import android.content.Context
import com.saurabhbhardwaj.kotlincoroutines.data.api.ApiHelper
import com.saurabhbhardwaj.kotlincoroutines.data.api.ApiHelperImpl
import com.saurabhbhardwaj.kotlincoroutines.data.api.RetrofitBuilder
import com.saurabhbhardwaj.kotlincoroutines.data.local.DatabaseBuilder
import com.saurabhbhardwaj.kotlincoroutines.data.local.DatabaseHelper
import com.saurabhbhardwaj.kotlincoroutines.data.local.DatabaseHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
class AppModule {

    @Singleton
    @Provides
    fun provideApiHelper(): ApiHelper {
        return ApiHelperImpl(RetrofitBuilder.apiService)
    }

    @Singleton
    @Provides
    fun provideDatabaseHelper(@ApplicationContext appContext: Context): DatabaseHelper {
        return DatabaseHelperImpl(DatabaseBuilder.getInstance(appContext))
    }
}