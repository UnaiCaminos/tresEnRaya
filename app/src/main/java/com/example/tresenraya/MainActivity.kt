package com.example.tresenraya

import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}

@Composable
fun TicTacToeApp(){
    var currentScreen by remember { mutableStateOf("Home") }
    var isComputerOpponent by remember { mutableStateOf(false) }
    var computerDifficulty by remember { mutableStateOf("Fácil") } // Estado de dificultad

    when (currentScreen) {
        "Home" -> HomeScreen(
            onStartPlayerVsPlayer = {
                isComputerOpponent = false
                currentScreen = "Game"
            },
            onStartPlayerVsComputer = {
                isComputerOpponent = true
                currentScreen = "Game"
            },
            onOpenSettings = { currentScreen = "Settings" }
        )
        "Game" -> TicTacToeGame(
            isComputerOpponent = isComputerOpponent,
            computerDifficulty = computerDifficulty,
            onGoBack = { currentScreen = "Home" }
        )
        "Settings" -> SettingsScreen(
            computerDifficulty = computerDifficulty,
            onDifficultyChange = { computerDifficulty = it },
            onGoBack = { currentScreen = "Home" }
        )
    }
}

@Composable
fun HomeScreen(
    onStartPlayerVsPlayer: () -> Unit,
    onStartPlayerVsComputer: () -> Unit,
    onOpenSettings: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Tic Tac Toe", fontSize = 36.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))

        Button(onClick = onStartPlayerVsPlayer, modifier = Modifier.padding(8.dp)) {
            Text("Jugar contra otro jugador")
        }

        Button(onClick = onStartPlayerVsComputer, modifier = Modifier.padding(8.dp)) {
            Text("Jugar contra el ordenador")
        }

        Button(onClick = onOpenSettings, modifier = Modifier.padding(8.dp)) {
            Text("Ajustes")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToePreview() {
    TicTacToeApp()
}