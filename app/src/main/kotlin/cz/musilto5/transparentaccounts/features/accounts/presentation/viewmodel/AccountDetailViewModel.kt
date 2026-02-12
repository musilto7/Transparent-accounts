package cz.musilto5.transparentaccounts.features.accounts.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.musilto5.transparentaccounts.common.domain.error.AppError
import cz.musilto5.transparentaccounts.common.domain.error.ExceptionToAppErrorMapper
import cz.musilto5.transparentaccounts.common.domain.model.AccountId
import cz.musilto5.transparentaccounts.features.accounts.domain.repository.TransparentAccountsRepository
import cz.musilto5.transparentaccounts.features.accounts.presentation.mapper.AccountDetailToViewObjectMapper
import cz.musilto5.transparentaccounts.features.accounts.presentation.model.AccountDetailViewObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class AccountDetailScreenState {
    data object Loading : AccountDetailScreenState()
    data class Success(val detail: AccountDetailViewObject) : AccountDetailScreenState()
    data class Error(val error: AppError) : AccountDetailScreenState()
}

class AccountDetailViewModel(
    private val accountId: AccountId,
    private val repository: TransparentAccountsRepository,
    private val accountDetailToViewObjectMapper: AccountDetailToViewObjectMapper,
    private val exceptionToAppErrorMapper: ExceptionToAppErrorMapper
) : ViewModel() {

    private val _state = MutableStateFlow<AccountDetailScreenState>(AccountDetailScreenState.Loading)
    val state: StateFlow<AccountDetailScreenState> = _state.asStateFlow()

    init {
        loadDetail()
    }

    fun retry() {
        _state.value = AccountDetailScreenState.Loading
        loadDetail()
    }

    private fun loadDetail() {
        viewModelScope.launch {
            try {
                val detail = repository.getAccountDetail(accountId)
                _state.value = AccountDetailScreenState.Success(accountDetailToViewObjectMapper.map(detail))
            } catch (e: Throwable) {
                _state.value = AccountDetailScreenState.Error(exceptionToAppErrorMapper.map(e))
            }
        }
    }
}
