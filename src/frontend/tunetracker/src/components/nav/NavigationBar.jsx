import React from 'react';
import { Container, Navbar, NavDropdown } from 'react-bootstrap';
import './NavigationBar.css';

export default function NavigationBar() {
    return (
        <Navbar bg="dark" variant="dark">
            <Container fluid> 
                <Navbar.Brand href="#home" id="brand"> 
                    <div className="p-2">TuneTracker</div>
                </Navbar.Brand>
                <div className="color-drop">
                    <NavDropdown title="Menu" menuVariant="dark" align="end">
                    <NavDropdown.Item id="d-item" href="#placeholder">Placeholder1</NavDropdown.Item>
                    <NavDropdown.Item id="d-item" href="#placeholder">Placeholder2</NavDropdown.Item>
                    </NavDropdown>
                </div>

            </Container>
        </Navbar>
    );
}
// https://getbootstrap.com/docs/5.3/utilities/flex/ --> pushign items to the right/left 
// 1) Fluid Container You can use <Container fluid /> for width: 100% across all viewport and device sizes.The align="end" property in the NavDropdown component is making the dropdown menu align to the end (far right) of its container, which in this case is the Container component. The Container component is a flex container, and the align property is part of Bootstrap's flex utilities, which allows you to control the alignment of flex items within a flex container. So, the align="end" is working because the Container is a flex container.
// 2) modify below to go back home when clicking icon (href="#home")
// 3) Brand is the brand name of the website
// 4) As of Bootstrap 5 beta, left and right have been replaced by start and end for RTL support.  Therefore the margin utilities changed for Bootstrap 5 beta:
// 4) ml-auto => ms-auto (start)
// 4) mr-auto => me-auto (end)