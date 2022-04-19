package ru.zar1official.hackathon_final.data.network

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import ru.zar1official.hackathon_final.data.models.ChillSpaceEntity
import ru.zar1official.hackathon_final.data.models.WorkSpaceEntity

class ServiceImpl(private val client: HttpClient) : Service {
    private val setWorkPath = arrayOf("api", "setWorkPlace")
    private val getWorkPath = arrayOf("api", "getWorkPlace")
    private val setRestPath = arrayOf("api", "setRestPlace")
    private val getRestPath = arrayOf("api", "getRestPlace")

    override suspend fun changeBusyStatus(uId: Long, busy: Boolean) {
        client.post<HttpResponse> {
            url {
                path(*setWorkPath)
                formData {
                    parameter("uId", uId)
                    parameter("busyStatus", busy)
                }
            }
        }
    }

    override suspend fun changeLightWarm(uId: Long, warm: Int) {
        client.post<HttpResponse> {
            url {
                path(*setWorkPath)
                formData {
                    parameter("uId", uId)
                    parameter("warmControl", warm)
                }
            }
        }
    }

    override suspend fun changeLightBright(uId: Long, bright: Int) {
        client.post<HttpResponse> {
            url {
                path(*setWorkPath)
                formData {
                    parameter("uId", uId)
                    parameter("brightControl", bright)
                }
            }
        }
    }

    override suspend fun changeMicroclimate(uId: Long, microclimateType: Int) {
        client.post<HttpResponse> {
            url {
                path(*setWorkPath)
                formData {
                    parameter("uId", uId)
                    parameter("microclimateType", microclimateType)
                }
            }
        }
    }

    override suspend fun getWorkSpaceState(uId: Long): WorkSpaceEntity {
        return client.get {
            url {
                path(*getWorkPath)
                parameter("uId", uId)
            }
        }
    }

    override suspend fun changeMassageMode(uId: Long, mode: Int) {
        client.post<HttpResponse> {
            url {
                path(*setRestPath)
                formData {
                    parameter("uId", uId)
                    parameter("massageMode", mode)
                }
            }
        }
    }

    override suspend fun getChillSpaceState(uId: Long): ChillSpaceEntity {
        return client.get {
            url {
                path(*getRestPath)
                parameter("uId", uId)
            }
        }
    }
}