import React from "react";

export function RevengeButton(props: any) {
    if (props.endOfGame) {
        return <button onClick={(e) => props.revange()} disabled={props.endOfGame} >Play again</button>
    }
    return <p></p>;
}