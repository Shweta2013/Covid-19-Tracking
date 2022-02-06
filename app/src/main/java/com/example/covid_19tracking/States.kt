package com.example.covid_19tracking

import com.google.gson.annotations.SerializedName
import java.util.*

data class States(
    //name of attribute in json matches with this val name so no need of @SerializedName
    val date: Date,
    @SerializedName("state") val name: String,
    @SerializedName("positive") val positiveCases: Long,
    @SerializedName("hospitalizedCurrently") val hospitalizedCurrently: Long,
    @SerializedName("death") val deathCount: Long
)
