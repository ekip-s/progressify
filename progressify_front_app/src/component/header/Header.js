import styles from "./Header.module.css";
import useKeycloak from "../../hooks/useKeycloak";
import Button from "../ui/Button";

const Header = () => {
  const { loginHandler, logoutHandler, isAuth } = useKeycloak();

  return (
    <header className={styles.header}>
      <div className={styles.wrapper}>
        <div className={styles.logo}>Progressifi</div>
        <div></div>
        <div>
          <nav>
            {isAuth ? (
              <Button text="Выйти" type="button" onClick={logoutHandler} />
            ) : (
              <Button text="Войти" type="button" onClick={loginHandler} />
            )}
          </nav>
        </div>
      </div>
    </header>
  );
};

export default Header;

