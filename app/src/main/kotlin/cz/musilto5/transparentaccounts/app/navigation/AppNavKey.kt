package cz.musilto5.transparentaccounts.app.navigation

import androidx.navigation3.runtime.NavKey
import cz.musilto5.transparentaccounts.common.domain.model.AccountId
import kotlinx.serialization.Serializable

@Serializable
data object Accounts : NavKey

@Serializable
data class AccountDetail(val accountId: AccountId) : NavKey
