package com.app.test.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
/*
* Module class for Context use in every where in app
* */
@Module
class   ContextModule(private val context: Context) {

    @Singleton
    @Provides
    fun getContext():Context {
        return context
    }
}