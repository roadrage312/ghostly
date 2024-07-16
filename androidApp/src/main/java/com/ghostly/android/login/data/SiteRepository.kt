package com.ghostly.android.login.data

import com.ghostly.android.login.models.SiteResponse
import com.ghostly.android.network.ApiService
import com.ghostly.android.network.models.Result

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