package com.example.assignment_elkdocs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
//import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.util.Collections.rotate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    MaterialTheme {
        AnimatedScreen()
    }
}

@Composable
fun AnimatedScreen() {
    // Animation state
    var animationState by remember { mutableStateOf(0) }

    // Timer to change animation state
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L / 90) // Adjust delay to match the video FPS
            animationState = (animationState + 1) % 993 // Loop through frames
        }
    }

    // Update transition based on animation state
    val transition = updateTransition(targetState = animationState, label = "animationTransition")

    // Define animation specs
    val animatedValue by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 1000) }, label = "animatedValue"
    ) { state ->
        // Map state to animated value (example)
        state / 993f
    }

    // Drawing the animated elements
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Example animated circle
            Canvas(modifier = Modifier.size(100.dp)) {
                rotate(animatedValue * 360) {
                    drawCircle(Color.Blue, radius = size.minDimension / 2)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            // Example animated rectangle
            Box(
                modifier = Modifier
                    .size(100.dp, 50.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .rotate(animatedValue * 180)
            )
        }
    }
}
