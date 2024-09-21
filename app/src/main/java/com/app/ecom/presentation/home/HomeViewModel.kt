package com.app.ecom.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecom.common.Resource
import com.app.ecom.domain.use_case.HomeUseCase
import com.app.ecom.presentation.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase): ViewModel(){

    val productListState = MutableStateFlow<HomeState>(HomeState())

    fun getProductsList() {
        homeUseCase.getProuctsList().onEach {
            when (it) {
                is Resource.Loading -> {
                    productListState.value = HomeState(isLoading = true)
                }
                is Resource.Error -> {
                    productListState.value = HomeState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    productListState.value = HomeState(productsListResponse = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}