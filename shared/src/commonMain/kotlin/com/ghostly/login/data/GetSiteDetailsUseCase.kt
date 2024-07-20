package com.ghostly.login.data

import com.ghostly.login.models.SiteResponse
import com.ghostly.network.models.Result

interface GetSiteDetailsUseCase {
    suspend operator fun invoke(url: String): Result<SiteResponse>
}

class GetSiteDetailsUseCaseImpl(
    private val siteRepository: SiteRepository
) : GetSiteDetailsUseCase {
    override suspend fun invoke(url: String): Result<SiteResponse> {
        return siteRepository.getSiteDetails(url)
    }
}