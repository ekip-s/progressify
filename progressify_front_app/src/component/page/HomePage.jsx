import styles from "./HomePage.module.css";
import Card from "../atom/Card.jsx";
import { Fragment } from "react";
import { useSelector } from "react-redux";
import Button from "../atom/Button.jsx";
import { useNavigate } from "react-router";

const HomePage = () => {
  const isAuth = useSelector((state) => state.auth.authenticated);
  const navigate = useNavigate();

  return (
    <Fragment>
      <Card className={styles.card}>
        <h3 className={styles.homeH}>Вижу результат — иду к цели!</h3>
        <p className={styles.homeP}>
          Сайт для отслеживания прогресса обучения. Когда видишь свой прогресс,
          становится легче двигаться к цели. Наглядный прогресс мотивирует
          продолжать, укрепляя уверенность и создавая ощущение успеха на каждом
          этапе пути.
        </p>
      </Card>
      {isAuth && (
        <div className={styles.btn}>
          <Button
            text="Начать"
            type="button"
            onClick={() => navigate("/education")}
          />
        </div>
      )}
    </Fragment>
  );
};

export default HomePage;
