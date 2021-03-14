@file:Suppress("RemoveEmptyParenthesesFromAnnotationEntry")

package com.zachklipp.compose.backstack

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

/**
 * Wrap your [Backstack] with this composable to get convenient gesture-based control of the
 * inspector when [enabled] is true.
 *
 * ## Example
 *
 * ```
 * var inspectionEnabled by state { false }
 * InspectionGestureDetector(inspectionEnabled) { inspectionParams ->
 *   Backstack(
 *     backstack = …,
 *     inspectionParams = inspectionParams
 *   ) { screen -> … }
 * }
 * ```
 *
 * @param enabled When true, gestures will be intercepted and used to generate
 * [InspectionParams] passed to [children]. When false, [children] will always be passed
 * null.
 */
@Composable
fun InspectionGestureDetector(
  enabled: Boolean,
  children: @Composable (InspectionParams?) -> Unit
) {
  var inspectionParams: InspectionParams by remember { mutableStateOf(InspectionParams()) }

  val controlModifier = if (!enabled) Modifier else {
    Modifier.pointerInput(Unit) {
      detectTransformGestures { _, pan, zoom, _ ->
        inspectionParams = inspectionParams.copy(
          scale = inspectionParams.scale * zoom,
          // Dragging left-and-right rotates around the vertical Y axis.
          rotationYDegrees = inspectionParams.rotationYDegrees + (pan.x / 5f)
        )
      }
    }
  }

  Box(modifier = controlModifier) {
    children(inspectionParams.takeIf { enabled })
  }
}
