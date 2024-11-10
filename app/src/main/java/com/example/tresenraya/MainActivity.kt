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

@Composable
fun TicTacToeGame(
    isComputerOpponent: Boolean,
    computerDifficulty: String, // Recibe la dificultad seleccionada
    onGoBack: () -> Unit
) {
    var board by remember { mutableStateOf(List(3) { MutableList(3) { "" } }) }
    var currentPlayer by remember { mutableStateOf("X") }
    var winner by remember { mutableStateOf<String?>(null) }
    var gameOver by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = onGoBack, modifier = Modifier.padding(8.dp)) {
            Text("Volver a inicio")
        }

        if (winner != null || gameOver) {
            Text(
                text = winner?.let { "¡Ganador: $it!" } ?: "¡Empate!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = {
                board = List(3) { MutableList(3) { "" } }
                winner = null
                gameOver = false
                currentPlayer = "X"
            }) {
                Text(text = "Reiniciar")
            }
        } else {
            Text(
                text = "Turno de $currentPlayer",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Tablero de 3x3
            for (row in 0..2) {
                Row {
                    for (col in 0..2) {
                        TicTacToeCell(
                            value = board[row][col],
                            onClick = {
                                if (board[row][col] == "" && winner == null && currentPlayer == "X") {
                                    makeMove(board, row, col, currentPlayer)
                                    currentPlayer = "O"
                                    winner = checkWinner(board)
                                    if (winner == null && board.flatten().none { it == "" }) {
                                        gameOver = true
                                    } else if (isComputerOpponent && currentPlayer == "O" && winner == null) {
                                        computerMove(board, computerDifficulty)
                                        currentPlayer = "X"
                                        winner = checkWinner(board)
                                        if (winner == null && board.flatten().none { it == "" }) {
                                            gameOver = true
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToePreview() {
    TicTacToeApp()
}