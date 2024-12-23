import styles from "./App.module.css";
import Header from "./component/header/Header";
import HomePage from "./component/home/HomePage";
import MainRouter from "./component/main/MainRouter";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router";

const App = () => {
  return (
    <Router>
      <div className={styles.wrapper}>
        <Header />
        <main className={styles.main}>
          <div className={styles.wrapperMain}>
            <Routes>
              <Route path="/" element={<Navigate to="/home" />} />
              <Route path="/home" element={<HomePage />} />
              <Route path="/education" element={<MainRouter />} />
            </Routes>
          </div>
        </main>
        <footer className={styles.footer}>footer</footer>
      </div>
    </Router>
  );
};

export default App;
