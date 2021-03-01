import React from "react";

export function QuitGameButton(props: any) {
    return <button onClick={(e) => props.quitGame()}>Quit</button>
}