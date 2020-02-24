package com.github.zsoltk.pokedex

import androidx.ui.material.Typography
import androidx.ui.text.font.FontWeight
import androidx.ui.text.font.ResourceFont
import androidx.ui.text.font.fontFamily

val appFontFamily = fontFamily(
    fonts = listOf(
        ResourceFont(R.font.circularstd_book),
        ResourceFont(R.font.circularstd_medium, FontWeight.W600),
        ResourceFont(R.font.circularstd_black, FontWeight.Bold),
        ResourceFont(R.font.circularstd_bold, FontWeight.W900)
    )
)

private val defaultTypography = Typography()
val themeTypography = Typography(
    h1 = defaultTypography.h1.copy(fontFamily = appFontFamily),
    h2 = defaultTypography.h2.copy(fontFamily = appFontFamily),
    h3 = defaultTypography.h3.copy(fontFamily = appFontFamily),
    h4 = defaultTypography.h4.copy(fontFamily = appFontFamily),
    h5 = defaultTypography.h5.copy(fontFamily = appFontFamily),
    h6 = defaultTypography.h6.copy(fontFamily = appFontFamily),
    subtitle1 = defaultTypography.subtitle1.copy(fontFamily = appFontFamily),
    subtitle2 = defaultTypography.subtitle2.copy(fontFamily = appFontFamily),
    body1 = defaultTypography.body1.copy(fontFamily = appFontFamily),
    body2 = defaultTypography.body2.copy(fontFamily = appFontFamily),
    button = defaultTypography.button.copy(fontFamily = appFontFamily),
    caption = defaultTypography.caption.copy(fontFamily = appFontFamily),
    overline = defaultTypography.overline.copy(fontFamily = appFontFamily)
)