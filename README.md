# Tres en Raya

Una aplicación de juego de Tres en Raya (Tic-Tac-Toe) desarrollada en Kotlin con Jetpack Compose. Permite jugar entre dos personas o contra el ordenador, con tres niveles de dificultad para el oponente automático.

## Funcionalidades principales

### 1. **Pantalla de Inicio**
   - Selección de modo de juego:
     - **Jugador vs Jugador**: Dos jugadores toman turnos en el mismo dispositivo.
     - **Jugador vs Ordenador**: El usuario juega contra un oponente automático con dificultad configurable.
   - **Ajustes**: Permite configurar el nivel de dificultad del oponente automático.

### 2. **Pantalla de Juego**
   - Muestra un tablero de 3x3 donde los jugadores pueden hacer sus movimientos.
   - Muestra el turno actual y el ganador cuando la partida termina.
   - Al finalizar, permite reiniciar la partida o volver a la pantalla de inicio.

### 3. **Pantalla de Ajustes**
   - Permite seleccionar la dificultad del ordenador entre tres opciones:
     - **Fácil**: Movimientos completamente aleatorios.
     - **Medio**: El ordenador intenta bloquear al jugador cuando es posible.
     - **Difícil**: Estrategia avanzada, tratando de ganar o bloquear al jugador y priorizando posiciones clave en el tablero.

## Cómo usar la aplicación

1. **Inicio del juego**:
   - Abre la aplicación. En la pantalla principal, elige si deseas jugar contra otro jugador o contra el ordenador.
   - Si deseas cambiar la dificultad del ordenador, selecciona **Ajustes** y elige entre **Fácil**, **Medio** o **Difícil**.

2. **Jugar una partida**:
   - Haz clic en una casilla vacía del tablero para colocar tu símbolo.
   - Alterna los turnos hasta que haya un ganador o empate.
   - Cuando la partida termine, puedes reiniciar el juego o volver a la pantalla de inicio.

3. **Niveles de Dificultad del Ordenador**:
   - La dificultad afecta la inteligencia del ordenador:
     - **Fácil**: Movimientos aleatorios, ideal para principiantes.
     - **Medio**: Intenta bloquear al jugador si está a punto de ganar.
     - **Difícil**: Añade una estrategia avanzada que trata de ganar o bloquear posiciones clave en el tablero.
