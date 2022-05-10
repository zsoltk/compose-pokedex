package com.sudhindra.composepokedex.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sudhindra.composepokedex.routes.Route

val dashboardRoutes = allRoutes.toMutableList()
    .apply { removeFirst() }.toList()

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardUi(navController: NavHostController) {
    LazyVerticalGrid(cells = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp)) {
        items(dashboardRoutes) { item ->
            DashboardItem(
                title = item.title,
                onClick = {
                    if (item !is Route.Pokemon)
                        navController.navigate(item.route)
                    else
                        navController.navigate(Route.Pokemon.defaultRoute())
                }
            )
        }
    }
}

@Composable
fun DashboardItem(title: String, onClick: () -> Unit) {
    Card(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(start = 16.dp)
                .aspectRatio(2.5f),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = title, style = MaterialTheme.typography.h6)
        }
    }
}
