import React from "react";
import type { GameState } from "../gameState";
import { Pit } from "./Pit";
import { PlayingHeader } from "./PlayingHeader";
import { RevengeButton } from "./RevengeButton";
import { QuitGameButton } from "./QuitGameButton";
import "./Play.css";

type PlayProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function Play({ gameState, setGameState }: PlayProps) {

    let playingHeader = <PlayingHeader
                        key={gameState.gameStatus.winner}
                        endOfGame={gameState.gameStatus.endOfGame}
                        winner={gameState.gameStatus.winner}
                        firstPlayer={gameState.players[0]}
                        secondPlayer={gameState.players[1]}/>
    
    let pitsFirstPlayer = [] as any;
    gameState.players[0].pits.forEach(pit => {
        pitsFirstPlayer.push(
            <Pit key={pit.index}
            pitIndex={pit.index}
            numberOfStones={pit.nrOfStones}
            disabled={!gameState.players[0].hasTurn || gameState.gameStatus.endOfGame}
            tryPlayPit={tryPlayPit}/>
        );
    });

    let pitsSecondPlayer = [] as any;
    gameState.players[1].pits.reverse().forEach(pit => {
        pitsSecondPlayer.push(
            <Pit key={pit.index}
            pitIndex={pit.index}
            numberOfStones={pit.nrOfStones}
            disabled={!gameState.players[1].hasTurn || gameState.gameStatus.endOfGame}
            tryPlayPit={tryPlayPit}/>
        );
    });

    let revengeButton = <RevengeButton key={gameState.gameStatus.endOfGame}
                                       endOfGame={gameState.gameStatus.endOfGame}
                                       revenge={revenge}/>
                            
    let quitGameButton = <QuitGameButton key={gameState.gameStatus.endOfGame}
                                         quitGame={quitGame}/>

    async function revenge() {
        try {
            const response = await fetch('mancala/api/start', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ nameplayer1: gameState.players[0].name, nameplayer2: gameState.players[1].name })
            });

            if (response.ok) {
                const restartGameState = await response.json();
                sessionStorage.setItem("gameState", JSON.stringify(restartGameState));
                setGameState(restartGameState);
            } else {
                console.error(response.statusText);
            }
        } catch (error) {
            console.error(error.toString());
        }
    }

    async function quitGame() {
        sessionStorage.removeItem("gameState");
        setGameState("");
    }

    async function tryPlayPit(index: number) {
        if (gameState.gameStatus.endOfGame) {
            return;
        }

        try {
            const response = await fetch('mancala/api/play', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ pitIndex: index })
            });
            
            if (response.ok) {
                const newGameState = await response.json();
                sessionStorage.setItem("gameState", JSON.stringify(newGameState));
                setGameState(newGameState);
            } else {
                console.error(response.statusText);
            }
        } catch (error) {
            console.error(error.toString());
        }
    }

    return (
        <div className={"grid-container"}>
            <div className="playingHeader">
                {playingHeader}
            </div>

            <div className="pitsFirstPlayer">
                {pitsFirstPlayer}
            </div>
            
            <div className="kalaha1">
                <button disabled={!gameState.players[0].hasTurn}>{gameState.players[0].pits[6].nrOfStones}</button>
            </div>

            <div className="pitsSecondPlayer">
                {pitsSecondPlayer}
            </div>
            
            <div className="kalaha2">
                <button disabled={!gameState.players[1].hasTurn}>{gameState.players[1].pits[0].nrOfStones}</button>
            </div>

            <div className="revenge">
                {revengeButton}
            </div>

            <div className="quitGame">
                {quitGameButton}
            </div>
        </div>
    )
}