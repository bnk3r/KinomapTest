package yb.kinomaptestandroid.ui.presentation.animations.explosion.models

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import kotlin.math.pow
import kotlin.random.Random

/**
 * Particle model class for the explosion effect.
 * Each particle is self-handled and determine its own pathing using the 2nd and 3rd kinetic equations of motion.
 * @param density you must provide the local density (for DP & pixels calculation)
 * @param color color of the particle
 * @param startXPos Horizontal starting position in the canvas
 * @param startYPos Vertical starting position in the canvas
 * @param maxHorizontalDisplacement Maximum distance traveled horizontally
 * @param maxVerticalDisplacement maximum distance traveled vertically
 */
data class Particle(
    val density: Density,
    val color: Color,
    val startXPos: Int,
    val startYPos: Int,
    val maxHorizontalDisplacement: Float,
    val maxVerticalDisplacement: Float
) {
    val velocity = 4 * maxVerticalDisplacement
    val acceleration = (-2) * velocity
    var currentXPos = 0f
    var currentYPos = 0f
    var visibilityThresholdLow = ((-0..14).random() / 100f)
    var visibilityThresholdHigh = ((-0..40).random() / 100f)
    var initialXDisplacement = with(density) { 10.dp.toPx() } * ((-100..100).random() / 100f)
    var initialYDisplacement = with(density) { 10.dp.toPx() } * ((-100..100).random() / 100f)
    var alpha = 0f
    var currentRadius = 0f
    var startRadius = with(density) { 2.dp.toPx().toInt() }
    var endRadius = if (Random.Default.nextDouble(1.0) > 0.2) {
        (startRadius..(with(density) { 7.dp.toPx().toInt() })).random()
    } else {
        (with(density) { 1.5.dp.toPx().toInt() }..startRadius).random()
    }

    private fun mapRange(range1: FloatRange, range2: FloatRange, value: Float): Float {
        return (range2.from + (value - range1.from) * (range2.to - range2.from) / (range1.to - range1.from)).toFloat()
    }

    fun updateProgress(explosionProgress: Float) {
        val trajectoryProgress =
            if (explosionProgress < visibilityThresholdLow || (explosionProgress > (1 - visibilityThresholdHigh))) {
                alpha = 0f
                return
            } else {
                mapRange(
                    range1 = FloatRange(0.0, 1.0),
                    range2 = FloatRange(0.0, 1.4),
                    value = explosionProgress - visibilityThresholdLow
                )
            }
        alpha = if (trajectoryProgress < 0.7f) {
            1f
        } else {
            mapRange(
                range1 = FloatRange(0.0, 0.3),
                range2 = FloatRange(1.0, 0.0),
                value = trajectoryProgress - 0.7f
            )
        }
        currentRadius = startRadius + (endRadius - startRadius) + trajectoryProgress
        val currentTime = mapRange(
            range1 = FloatRange(0.0, 1.0),
            range2 = FloatRange(0.0, 1.4),
            value = trajectoryProgress
        )
        val verticalDisplacement =
            currentTime * velocity + 0.5 * acceleration * currentTime.pow(2)
        currentYPos = (startXPos + initialXDisplacement - verticalDisplacement).toFloat()
        currentXPos =
            startYPos + initialYDisplacement + maxHorizontalDisplacement * trajectoryProgress
    }
}