package com.ghostly.android.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghostly.android.datastore.DataStoreConstants
import com.ghostly.android.datastore.DataStoreRepository
import com.ghostly.android.login.models.LoginDetailsStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val dataStoreRepository: DataStoreRepository,
    private val loginDetailsStore: LoginDetailsStore,
) : ViewModel() {

    private val _storeName = MutableStateFlow<String?>(null)
    val storeName: StateFlow<String?> = _storeName

    private val _storeIcon = MutableStateFlow<String?>(null)
    val storeIcon: StateFlow<String?> = _storeIcon

    init {
        viewModelScope.launch {
            _storeName.value = dataStoreRepository.getString(DataStoreConstants.STORE_NAME)
            _storeIcon.value = dataStoreRepository.getString(DataStoreConstants.STORE_ICON)
        }
    }

    suspend fun clearLogin() {
        loginDetailsStore.delete()
        delay(1000)
    }

}