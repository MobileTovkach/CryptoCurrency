package com.example.air.cryptocurrency.model.selected_currency

import com.google.gson.annotations.SerializedName

data class SelectedCurrencyResponse(

	@field:SerializedName("price_usd")
	val priceUsd: String? = null,

	@field:SerializedName("symbol")
	val symbol: String? = null,

	@field:SerializedName("last_updated")
	val lastUpdated: String? = null,

	@field:SerializedName("total_supply")
	val totalSupply: String? = null,

	@field:SerializedName("24h_volume_usd")
	val jsonMember24hVolumeUsd: String? = null,

	@field:SerializedName("price_btc")
	val priceBtc: String? = null,

	@field:SerializedName("available_supply")
	val availableSupply: String? = null,

	@field:SerializedName("market_cap_usd")
	val marketCapUsd: String? = null,

	@field:SerializedName("percent_change_1h")
	val percentChange1h: String? = null,

	@field:SerializedName("percent_change_24h")
	val percentChange24h: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("max_supply")
	val maxSupply: String? = null,

	@field:SerializedName("rank")
	val rank: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("percent_change_7d")
	val percentChange7d: String? = null,

	@field:SerializedName("price_eur")
	val priceEur: String? = null,

	@field:SerializedName("24h_volume_eur")
	val jsonMember24hVolumeEur: String? = null,

	@field:SerializedName("market_cap_eur")
	val marketCapEur: String? = null,

	@field:SerializedName("price_cny")
	val priceCny: String? = null,

	@field:SerializedName("24h_volume_cny")
	val jsonMember24hVolumeCny: String? = null,

	@field:SerializedName("market_cap_cny")
	val marketCapCny: String? = null
)