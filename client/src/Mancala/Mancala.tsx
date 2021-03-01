import React, { useState } from "react";
import { StartGame } from "./StartGame";
import { Play } from "./Play";
import type { GameState } from "../gameState";
import "./Mancala.css";

/**
 * The base component for the Mancala game. If there's no active game, the `StartGame` component allows
 * users to enter two player names and start a new game.
 * If there's an active game this component holds the game state. This game state can be passed as a prop
 * to child components as needed.
 * 
 * Child components can modify the game state by calling the setGameState (which they recieve as prop.)
 */
export function Mancala() {

    const [ gameState, setGameState ] = useState<GameState | undefined>(undefined);

    let storage = window.sessionStorage;
    if (storage) {
        let storedGameState = storage.gameState;
        if (storedGameState) {
            storedGameState = JSON.parse(storedGameState);
            if (JSON.stringify(storedGameState) != JSON.stringify(gameState)) {
                setGameState(storedGameState);
            }
        }
    }

    if (!gameState) {
        return <StartGame setGameState={setGameState} />
    }

    return <Play gameState={gameState} setGameState={setGameState} />
}