import React from "react";

export function PlayingHeader(props: any) {
    let vs = props.firstPlayer.name + " vs " + props.secondPlayer.name + "   ";

    let winnerOrOnTurn = "";
    if (props.endOfGame) {
        if (props.winner) {
            winnerOrOnTurn += props.winner + " has won the game, what a performance!";
        } else {
            winnerOrOnTurn += "It is a draw.";
        }
    } else {
        winnerOrOnTurn += "Player on turn: ";
        if (props.firstPlayer.hasTurn) {
            winnerOrOnTurn += props.firstPlayer.name;
        } else {
            winnerOrOnTurn += props.secondPlayer.name;
        }
    }

    return  <div>
                <p>{vs}{" "}</p>
                <p>{winnerOrOnTurn}</p>
            </div>
}