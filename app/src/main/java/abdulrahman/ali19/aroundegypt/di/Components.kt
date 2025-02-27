package abdulrahman.ali19.aroundegypt.di

import abdulrahman.ali19.aroundegypt.data.repository.home.HomeRepositoryImpl
import abdulrahman.ali19.aroundegypt.data.source.local.LocalDataProvider
import abdulrahman.ali19.aroundegypt.data.source.local.home.HomeLocalDataSource
import abdulrahman.ali19.aroundegypt.data.source.local.home.HomeLocalDataSourceImpl
import abdulrahman.ali19.aroundegypt.data.source.remote.KtorClient
import abdulrahman.ali19.aroundegypt.data.source.remote.home.HomeRemoteDataSource
import abdulrahman.ali19.aroundegypt.data.source.remote.home.HomeRemoteDataSourceImpl
import abdulrahman.ali19.aroundegypt.domain.repository.home.HomeRepository
import abdulrahman.ali19.aroundegypt.presentation.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val NetworkModule = module {
    single { KtorClient().getKtorClient() }
}

private val DatabaseModule = module {
    single { LocalDataProvider(androidContext()) }
}

val appUtils = listOf(
    NetworkModule,
    DatabaseModule
)

val HomeModule = module {
    single<HomeRemoteDataSource> { HomeRemoteDataSourceImpl(get()) }
    single<HomeLocalDataSource> { HomeLocalDataSourceImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get(), get()) }
    viewModelOf(::HomeViewModel)
}