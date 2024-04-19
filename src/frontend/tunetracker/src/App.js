import './App.css';
import MyFooter from './components/Footer/MyFooter';
import LandingPage from './components/mainpage/LandingPage';
import NavigationBar from './components/nav/NavigationBar';
import AboutMe from './components/mainpage/AboutMe';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <NavigationBar />
          <Routes>
              <Route exact path="/" element={<LandingPage/>} />
              <Route exact path="/about-me" element={<AboutMe/>} />
          </Routes>
        <MyFooter />
      </div>
    </BrowserRouter>
  );
}

export default App;
