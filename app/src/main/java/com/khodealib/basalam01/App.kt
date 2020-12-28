package com.khodealib.basalam01

import android.app.Application
import androidx.room.Room
import com.khodealib.basalam01.data.database.product.AppDao
import com.khodealib.basalam01.data.database.product.AppDatabase
import com.khodealib.basalam01.data.repo.product.ProductRepository
import com.khodealib.basalam01.data.repo.product.ProductRepositoryImpl
import com.khodealib.basalam01.data.repo.product.source.ProductLocalDataSource
import com.khodealib.basalam01.data.repo.product.source.ProductRemoteDataSource
import com.khodealib.basalam01.feature.main.MainViewModel
import com.khodealib.basalam01.service.api.ApiService
import com.khodealib.basalam01.service.api.ApiServiceProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())


        val modulesApp = module {

            single<ApiService> {
                ApiServiceProvider.createApiServiceInstance()
            }

            single {
                provideDatabase(androidApplication())
            }

            single { provideDao(get()) }

            factory<ProductRepository> {
                ProductRepositoryImpl(
                    ProductRemoteDataSource(get()),
                    ProductLocalDataSource(get())
                )
            }

            viewModel {

                MainViewModel(get())
            }

        }

        startKoin {

            androidContext(this@App)
            modules(modulesApp)

        }
    }

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    fun provideDao(database: AppDatabase): AppDao {
        return database.appDao
    }
}