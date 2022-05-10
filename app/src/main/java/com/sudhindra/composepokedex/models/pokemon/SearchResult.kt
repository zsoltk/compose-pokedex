package com.sudhindra.composepokedex.models.pokemon

data class SearchResult(
    val results: List<Result>
) {
    data class Result(
        val name: String,
        val url: String
    ) {
        val pokemonIdFromUrl: Int
            get() = url.substringAfter("pokemon/")
                .replace("/", "")
                .toInt()
        val pokemonSpeciesIdFromUrl: Int
            get() = url.substringAfter("pokemon-species/")
                .replace("/", "")
                .toInt()
        val typeIdFromUrl: Int
            get() = url.substringAfter("type/")
                .replace("/", "")
                .toInt()
        val regionIdFromUrl: Int
            get() = url.substringAfter("region/")
                .replace("/", "")
                .toInt()
    }
}
