package com.sudhindra.composepokedex.models.item

data class Item(
    val id: Int,
    val name: String,
    val cost: Int,
    val sprites: Sprites
) {
    val itemSprite: String
        get() = sprites.defaultSprite
}
