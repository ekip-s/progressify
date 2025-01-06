import styles from "./LogoAndLink.module.css";
import PropTypes from "prop-types";

const LogoAndLink = ({ link, imageSrc, isReflectText = true, text }) => {
  return (
    <a
      href={link}
      target="_blank"
      rel="noopener noreferrer"
      className={styles.logoAndLink}
    >
      <div>
        <img src={imageSrc} alt={text} />
        {isReflectText && <span>{text}</span>}
      </div>
    </a>
  );
};

LogoAndLink.propTypes = {
  link: PropTypes.string.isRequired,
  imageSrc: PropTypes.string,
  isReflectText: PropTypes.bool,
  text: PropTypes.string,
};

export default LogoAndLink;
