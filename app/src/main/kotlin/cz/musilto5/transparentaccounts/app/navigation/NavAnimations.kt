package cz.musilto5.transparentaccounts.app.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.navigation3.ui.NavDisplay

/**
 * Navigation transition metadata for [AccountDetail] screen.
 * Slide in from right / out to left when pushing, reverse when popping.
 */
fun accountDetailTransitionMetadata(): Map<String, Any> = buildMap {
    putAll(
        NavDisplay.transitionSpec {
            slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth })
                .togetherWith(slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }))
        }
    )
    putAll(
        NavDisplay.popTransitionSpec {
            slideInHorizontally(initialOffsetX = { fullWidth -> -fullWidth })
                .togetherWith(slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }))
        }
    )
}
