package com.enterprise.appstockmarketjetpackcompose.presentation.depencencyinjection

import android.content.Context
import com.enterprise.appstockmarketjetpackcompose.data.remotedatasource.mock.FlowMockApi
import com.enterprise.appstockmarketjetpackcompose.data.remotedatasource.mock.mockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideFlowMockApi(@ApplicationContext context: Context): FlowMockApi {
        return mockApi(context)
    }

}