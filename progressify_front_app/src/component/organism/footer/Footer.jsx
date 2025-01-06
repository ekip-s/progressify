import styles from "./Footer.module.css";
import global from "../../../global.module.css";
import LogoAndLink from "../../atom/LogoAndLink.jsx";
import swaggerImg from "../../../assets/img/swagger.svg";
import githubImg from "../../../assets/img/github.svg";
import kanbanImg from "../../../assets/img/kanban.svg";
import emailImg from "../../../assets/img/email.svg";

const Footer = () => {
  return (
    <footer className={styles.footer}>
      <div className={`${global.flexBetween} ${global.leftAndRight}`}>
        <LogoAndLink
          link={import.meta.env.VITE_SWAGGER_LINK}
          text="Swagger"
          imageSrc={swaggerImg}
        />
        <LogoAndLink
          link="https://github.com/ekip-s/progressify"
          text="GitHub"
          imageSrc={githubImg}
        />
        <LogoAndLink
          link="https://ru.yougile.com/board/07qhbp2hraiy"
          text="Задачи"
          imageSrc={kanbanImg}
        />
        <LogoAndLink
          link="mailto:b9955047856@gmail.com?subject=Progressifi%20есть%20идея&body=%D0%9F%D1%80%D0%B5%D0%B4%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5%20(%D0%BE%D0%BF%D0%B8%D1%88%D0%B8%20%D0%BF%D0%BE%D0%B4%D1%80%D0%BE%D0%B1%D0%BD%D0%BE):"
          text="Предложить изменения"
          imageSrc={emailImg}
        />
      </div>
    </footer>
  );
};

export default Footer;
