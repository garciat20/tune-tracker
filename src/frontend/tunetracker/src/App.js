import './App.css';
import MyFooter from './components/Footer/MyFooter';
import LandingPage from './components/mainpage/LandingPage';
import NavigationBar from './components/nav/NavigationBar';

function App() {
  return (
    <div className="App">
      <NavigationBar />
      <LandingPage />
      <MyFooter />
    </div>
  );
}

export default App;
