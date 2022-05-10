package com.sudhindra.composepokedex.models.item

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("default")
    val defaultSprite: String
)
