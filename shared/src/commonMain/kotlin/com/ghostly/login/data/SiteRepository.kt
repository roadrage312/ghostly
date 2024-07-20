package com.ghostly.login.data

import com.ghostly.login.models.SiteResponse
import com.ghostly.network.ApiService
import com.ghostly.network.models.Result

interface SiteRepository {
    suspend fun getSiteDetails(url: String): Result<SiteResponse>
}

class SiteRepositoryImpl(
    private val apiService: ApiService,
) : SiteRepository {
    override suspend fun getSiteDetails(url: String): Result<SiteResponse> {
        return apiService.getSiteDetails(url)
    }
}