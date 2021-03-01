import React from "react";

export function RevengeButton(props: any) {
    if (props.endOfGame) {
        return <button onClick={(e) => props.restart()}>Play again</button>
    }
    return <p></p>;
}