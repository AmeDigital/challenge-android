package com.amedigital.coreui.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

private val currencyBR = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

private fun formatMoneyBR(value: Double, withSymbol: Boolean): String {
    val symbol: String = if (withSymbol) "R$ " else ""
    return currencyBR.format(value).replace("R$", symbol)
}

fun Double.toMoneyBR(withSymbol: Boolean = false): String {
    return formatMoneyBR(this, withSymbol)
}

