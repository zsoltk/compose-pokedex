package com.sudhindra.composepokedex.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.FileProvider
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import java.io.File
import java.io.FileOutputStream

fun Context.sharePokemon(pokemon: Pokemon, drawable: Drawable) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.putExtra(Intent.EXTRA_TEXT, pokemon.pokemonShareString)
    try {
        val bitmap: Bitmap = (drawable as BitmapDrawable).bitmap
        val file = File(externalCacheDir, "Share.jpg")
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
        intent.putExtra(
            Intent.EXTRA_STREAM,
            FileProvider.getUriForFile(this, applicationContext.packageName + ".provider", file)
        )
        intent.type = "image/png"
    } catch (e: Exception) {
        e.printStackTrace()
    }
    startActivity(Intent.createChooser(intent, "Share Pokemon Via..."))
}
