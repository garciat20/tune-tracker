import React from 'react';
import {Card,Button, Row, Col, Container } from 'react-bootstrap';


export default function AboutMe() {
  return (
    <Container className='d-flex justify-content-center'>
      <Row className='d-flex align-items-center'>
            <Col>
            <Card style={{ width: '18rem' }}>
                <Card.Img variant="top" src="images/coding.jpg" />
                <Card.Body>
                    <Card.Title>Introduction</Card.Title>
                    <Card.Text>
                    Hello! My name is Thomas and I love the constant learning programming provides! :)
                    </Card.Text>
                    <Button variant="primary">github</Button>
                </Card.Body>
                </Card>
            </Col>
         </Row>
    </Container>
  );
}

