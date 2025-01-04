import styles from "./App.module.css";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router";
import Header from "./component/organism/header/Header.jsx";
import HomePage from "./component/page/HomePage.jsx";
import MainRouter from "./component/molecule/MainRouter.jsx";

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
