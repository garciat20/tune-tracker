import './App.css';
import MyFooter from './components/Footer/MyFooter';
import MainPage from './components/mainpage/MainPage';
import NavigationBar from './components/nav/NavigationBar';

function App() {
  return (
    <div className="App">
      <NavigationBar />
      <MainPage />
      <MyFooter />
    </div>
  );
}

export default App;
