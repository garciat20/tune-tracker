import React from 'react'
import { Button, Container, Figure } from 'react-bootstrap'

export default function () {
    return (
        <h1>MainPage/Homepage | Add a sign in/sign up here (maybe image at the left side and login/signup at the right side)</h1>
        // <Container lassName="d-flex justify-content-center position-relative" fluid >
        //  <Figure className="position-relative">
        //     <Figure.Image 
        //         // className="mx-auto d-block"
        //         fluid
        //         width={500}
        //         height={500}
        //         alt="image of main page of tunetracker"
        //         src="/images/main_image.jpg"
        //     />
        //     <Figure.Caption >
        //         Welcome to TuneTracker! Log in to view some tunes, make a playlist and check out other's playlists!
        //     </Figure.Caption>
        // </Figure>
        // </Container>
       
    )
}

//1) The <Container> component is set to be a flex container with d-flex class and its contents are centered horizontally with justify-content-center class.

// 2)Inside the container, you have a <Figure> component which contains the main image and caption.

//3) The <Figure.Image> component has the mx-auto and d-block classes, which are Bootstrap utility classes for centering the image horizontally within the figure. mx-auto sets the left and right margins to auto, and d-block makes the image a block-level element.

