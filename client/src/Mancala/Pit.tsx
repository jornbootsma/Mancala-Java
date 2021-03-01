import React from "react";

export function Pit(props: any) {
    if (props.pitIndex == 6 || props.pitIndex == 13) { return null; }
    return <button onClick={(e) => props.tryPlayPit(props.pitIndex)} disabled={props.disabled}>{props.numberOfStones}</button>
}