package com.androimads.retrolin

import com.google.gson.annotations.SerializedName

data class Response(
	@field:SerializedName("date")
	val date: String? = null,
	@field:SerializedName("rates")
	val rates: Rates? = null,
	@field:SerializedName("base")
	val base: String? = null)

