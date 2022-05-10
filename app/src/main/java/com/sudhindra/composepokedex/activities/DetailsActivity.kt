package com.sudhindra.composepokedex.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.sudhindra.composepokedex.constants.BundleKeys
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.ui.screens.PokemonDetailsUi
import com.sudhindra.composepokedex.ui.utils.WithTheme
import com.sudhindra.composepokedex.utils.createIntent
import com.sudhindra.composepokedex.viemodel.details.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var assistedInjectFactory: DetailsViewModel.AssistedInjectFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemon = intent.getParcelableExtra<Pokemon>(BundleKeys.POKEMON)!!
        val viewModel = DetailsViewModel.provideFactory(assistedInjectFactory, pokemon)
            .create(DetailsViewModel::class.java)

        setContent {
            WithTheme {
                PokemonDetailsUi(
                    viewModel,
                    onNewPokemonSuccess = {
                        val intent = createIntent<DetailsActivity>()
                        intent.putExtra(BundleKeys.POKEMON, it)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}
