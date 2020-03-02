package com.github.zsoltk.pokedex

import androidx.compose.Composable
import com.github.zsoltk.compose.router.Router
import com.github.zsoltk.pokedex.home.Home
import com.github.zsoltk.pokedex.home.Home.MenuItem
import com.github.zsoltk.pokedex.pokedex.Pokedex

interface Root {

    sealed class Routing {
        object Home : Routing()
        object Pokedex : Routing()
    }

    companion object {
        @Composable
        fun Content(defaultRouting: Routing = Routing.Home) {
            Router(defaultRouting) { backStack ->
                val onMenuItemSelected: (MenuItem) -> Unit = {
                    when (it) {
                        MenuItem.Pokedex -> backStack.push(Routing.Pokedex)
                    }
                }

                when (val routing = backStack.last()) {
                    is Routing.Home -> Home.Content(onMenuItemSelected)
                    is Routing.Pokedex -> Pokedex.Content()
                }
            }
        }
    }
}
