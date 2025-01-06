import styles from "./Footer.module.css";
import global from "../../../global.module.css";
import LogoAndLink from "../../atom/LogoAndLink.jsx";

const Footer = () => {
  return (
    <footer className={styles.footer}>
      <div className={`${global.flexBetween} ${global.leftAndRight}`}>
        <LogoAndLink
          link={import.meta.env.VITE_SWAGGER_LINK}
          text="Swagger"
          imageSrc="src/assets/img/swagger.svg"
        />
        <LogoAndLink
          link="https://github.com/ekip-s/progressify"
          text="GitHub"
          imageSrc="src/assets/img/github.svg"
        />
        <LogoAndLink
          link="https://ru.yougile.com/board/07qhbp2hraiy"
          text="Задачи"
          imageSrc="src/assets/img/kanban.svg"
        />
        <LogoAndLink
          link="mailto:b9955047856@gmail.com?subject=Progressifi%20есть%20идея&body=%D0%9F%D1%80%D0%B5%D0%B4%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5%20(%D0%BE%D0%BF%D0%B8%D1%88%D0%B8%20%D0%BF%D0%BE%D0%B4%D1%80%D0%BE%D0%B1%D0%BD%D0%BE):"
          text="Предложить изменения"
          imageSrc="src/assets/img/email.svg"
        />
      </div>
    </footer>
  );
};

export default Footer;
