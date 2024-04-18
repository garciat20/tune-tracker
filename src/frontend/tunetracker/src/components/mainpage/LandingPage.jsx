import React from 'react';
import { Button, Col, Container, Form, Row, Card} from 'react-bootstrap'


export default function LandingPage() {

    return (
 
        <Container fluid style={{ height: '100vh' }} className='d-flex align-items-center'>
            <Row >
                <Col className="d-flex justify-content-center" >
                    <Form>
                        <Form.Group className='mb-2'>
                            <Form.Label>Email Address</Form.Label>
                            <Form.Control type="email" placeholder="Enter email" />
                        </Form.Group>
                        <Form.Group  className='mb-2'>
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" placeholder="Password" />
                        </Form.Group>
                        <Button variant="primary" type="submit" className="me-5">
                            Login
                        </Button>
                        <Button variant="primary" type="submit">
                            Signup
                        </Button>
                    </Form>
                </Col>
                <Col className="d-flex justify-content-center">
                <Card style={{width: "60%"}}>
                    <Card.Img variant="top" src="images/main_image.jpg" />
                    <Card.Body>
                    <Card.Text>
                        Welcome to tunetracker! You can browse as a guest or Log in/ Sign up to view some tunes, make a playlist and check out other's playlists!
                    </Card.Text>
                    </Card.Body>
                </Card>
                </Col>
            </Row>

        </Container>
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

