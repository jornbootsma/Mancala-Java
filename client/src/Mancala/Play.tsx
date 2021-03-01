import React from "react";
import type { GameState } from "../gameState";
import { Pit } from "./Pit";
import { PlayingHeader } from "./PlayingHeader";
import "./Play.css";

type PlayProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function Play({ gameState, setGameState }: PlayProps) {

    let playingHeader = [] as any;
    playingHeader.push(<PlayingHeader
                        key={gameState.gameStatus.winner}
                        endOfGame={gameState.gameStatus.endOfGame}
                        winner={gameState.gameStatus.winner}
                        firstPlayer={gameState.players[0]}
                        secondPlayer={gameState.players[1]}/>);
    
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
        </div>
        // <div className={`grid-container`}>
        //     {/* <div className="firstLine">{firstLine}</div>
        //     <div className="onturn">{onturn}</div> */}

        //     <div className="playingbowl0">
        //         <button onClick={(e) => tryPlayPit(0)} disabled={!gameState.players[0].hasTurn || gameState.gameStatus.endOfGame}>{board[0]}</button>
        //     </div>
        //     <div className="playingbowl1">
        //         <button onClick={(e) => tryPlayPit(1)} disabled={!gameState.players[0].hasTurn || gameState.gameStatus.endOfGame}>{board[1]}</button>
        //     </div>
        //     <div className="playingbowl2">
        //         <button onClick={(e) => tryPlayPit(2)} disabled={!gameState.players[0].hasTurn || gameState.gameStatus.endOfGame}>{board[2]}</button>
        //     </div>
        //     <div className="playingbowl3">
        //         <button onClick={(e) => tryPlayPit(3)} disabled={!gameState.players[0].hasTurn || gameState.gameStatus.endOfGame}>{board[3]}</button>
        //     </div>
        //     <div className="playingbowl4">
        //         <button onClick={(e) => tryPlayPit(4)} disabled={!gameState.players[0].hasTurn || gameState.gameStatus.endOfGame}>{board[4]}</button>
        //     </div>
        //     <div className="playingbowl5">
        //         <button onClick={(e) => tryPlayPit(5)} disabled={!gameState.players[0].hasTurn || gameState.gameStatus.endOfGame}>{board[5]}</button>
        //     </div>
        //     <div className="kalaha6">
        //         <button disabled={!gameState.players[0].hasTurn}>{board[6]}</button>
        //     </div>

        //     <div className="playingbowl7">
        //         <button onClick={(e) => tryPlayPit(7)} disabled={!gameState.players[1].hasTurn || gameState.gameStatus.endOfGame}>{board[7]}</button>
        //     </div>
        //     <div className="playingbowl8">
        //         <button onClick={(e) => tryPlayPit(8)} disabled={!gameState.players[1].hasTurn || gameState.gameStatus.endOfGame}>{board[8]}</button>
        //     </div>
        //     <div className="playingbowl9">
        //         <button onClick={(e) => tryPlayPit(9)} disabled={!gameState.players[1].hasTurn || gameState.gameStatus.endOfGame}>{board[9]}</button>
        //     </div>
        //     <div className="playingbowl10">
        //         <button onClick={(e) => tryPlayPit(10)} disabled={!gameState.players[1].hasTurn || gameState.gameStatus.endOfGame}>{board[10]}</button>
        //     </div>
        //     <div className="playingbowl11">
        //         <button onClick={(e) => tryPlayPit(11)} disabled={!gameState.players[1].hasTurn || gameState.gameStatus.endOfGame}>{board[11]}</button>
        //     </div>
        //     <div className="playingbowl12">
        //         <button onClick={(e) => tryPlayPit(12)} disabled={!gameState.players[1].hasTurn || gameState.gameStatus.endOfGame}>{board[12]}</button>
        //     </div>
        //     <div className="kalaha13">
        //         <button disabled={!gameState.players[1].hasTurn}>{board[13]}</button>
        //     </div>
        // </div>
    )
}