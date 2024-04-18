import React from "react";
import './MyFooter.css';
import { Navbar } from "react-bootstrap";

export default function MyFooter(){
    return (

            <Navbar bg="dark" variant="dark" className="justify-content-center">
                <Navbar.Text className="footer-text">
                    &copy; {new Date().getFullYear()} TuneTracker | Thomas Garcia | <a id ="link" href="mailto:tmg2102@rit.edu">tmg2102@rit.edu</a>
                </Navbar.Text>
            </Navbar>

);
}

// navbar is fluid by default 
// In React Bootstrap, you can use the className prop to add Bootstrap classes to components. To center the content of a Navbar, you can use the justify-content-center class which is a part of Bootstrap's flex utilities. This class is used to horizontally center the flex items inside a flex container. 
// fixed="bottom" prop to fix the "footer" to the bottom of the viewport. bg="dark" and variant="dark" props set the background and text color of the footer to dark