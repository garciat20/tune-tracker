import React from "react";
import './MyFooter.css';
import { Container, Navbar } from "react-bootstrap";

export default function MyFooter(){
    return (
            <Navbar fixed="bottom" bg="dark" variant="dark" className="justify-content-center">
            <Navbar.Brand className="testing1">
                &copy; {new Date().getFullYear()} TuneTracker | Thomas Garcia | <a href="mailto:tmg2102@rit.edu">tmg2102@rit.edu</a>
            </Navbar.Brand>
            </Navbar>

);
}

// navbar is fluid by default i believe
// In React Bootstrap, you can use the className prop to add Bootstrap classes to components. To center the content of a Navbar, you can use the justify-content-center class which is a part of Bootstrap's flex utilities. This class is used to horizontally center the flex items inside a flex container. 
// fixed="bottom" prop to fix the "footer" to the bottom of the viewport. bg="dark" and variant="dark" props set the background and text color of the footer to dark