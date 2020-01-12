package com.github.zsoltk.pokedex.common

import androidx.animation.FloatPropKey
import androidx.animation.Infinite
import androidx.animation.LinearEasing
import androidx.animation.transitionDefinition
import androidx.compose.Composable
import androidx.ui.core.Draw

private val rotation = FloatPropKey()

private fun createDefinition(duration: Int) = transitionDefinition {
    state(0) { this[rotation] = 0f }
    state(1) { this[rotation] = 360f }

    transition {
        rotation using repeatable {
            animation = tween {
                easing = LinearEasing
                this.duration = duration
            }
            iterations = Infinite
        }
    }
}

@Composable
fun Rotate(duration: Int, children: @Composable() () -> Unit) {
    Transition(definition = createDefinition(duration), initState = 0, toState = 1) {
        Draw(children = children) { canvas, parent ->
            canvas.save()
            canvas.translate(parent.width.value / 2, parent.height.value / 2)
            canvas.rotate(it[rotation])
            canvas.translate(-parent.width.value / 2, -parent.height.value / 2)
            drawChildren()
            canvas.restore()
        }
    }
}
