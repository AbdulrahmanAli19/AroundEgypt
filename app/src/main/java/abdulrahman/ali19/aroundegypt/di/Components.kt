package abdulrahman.ali19.aroundegypt.di

import abdulrahman.ali19.aroundegypt.data.source.remote.KtorClient
import org.koin.dsl.module

private val networkModule = module {
    single { KtorClient().getKtorClient() }
}

val appUtils = listOf(
    networkModule
)
