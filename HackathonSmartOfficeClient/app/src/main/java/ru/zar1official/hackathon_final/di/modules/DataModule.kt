package ru.zar1official.hackathon_final.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import org.koin.dsl.module
import ru.zar1official.hackathon_final.data.network.NetworkConstants
import ru.zar1official.hackathon_final.data.network.Service
import ru.zar1official.hackathon_final.data.network.ServiceImpl
import ru.zar1official.hackathon_final.data.repositories.OfficeRepository
import ru.zar1official.hackathon_final.data.storage.StorageConstants
import ru.zar1official.hackathon_final.domain.repositories.Repository
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

private fun provideKtorClient(): HttpClient {
    return HttpClient(CIO) {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = NetworkConstants.baseHost
                port = NetworkConstants.basePort
            }
        }
        /* I use it to connect to localhost, by some reasons it's not trusted*/
        engine {
            https {
                trustManager = object : X509TrustManager {
                    override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {}

                    override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {}

                    override fun getAcceptedIssuers(): Array<X509Certificate>? = null
                }
            }
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                })
        }
    }
}

private val Context.dataStore by preferencesDataStore(name = StorageConstants.storageSettingsName)

private fun provideDataStorage(context: Context) = context.dataStore

val dataModule = module {

    single<HttpClient> {
        provideKtorClient()
    }

    single<Service> {
        return@single ServiceImpl(client = get())
    }
    single<Repository> {
        return@single OfficeRepository(service = get(), dataStore = get())
    }

    single<DataStore<Preferences>> {
        provideDataStorage(context = get())
    }
}