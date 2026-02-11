package cz.musilto5.transparentaccounts.common.presentation

const val PLACEHOLDER = "—"

fun String?.orPlaceholder(): String = this?.takeIf { it.isNotBlank() } ?: PLACEHOLDER
