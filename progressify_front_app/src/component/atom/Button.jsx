import styles from "./Button.module.css";
import PropTypes from "prop-types";

const Button = ({ type, text, className, onClick }) => {
  const handleClick = type === "button" ? onClick : null;

  return (
    <button
      className={`${styles.button} ${[className]}`}
      onClick={handleClick}
      type={type}
    >
      {text}
    </button>
  );
};

Button.propTypes = {
  type: PropTypes.string.isRequired,
  text: PropTypes.string.isRequired,
  className: PropTypes.string,
  onClick: PropTypes.func,
};

export default Button;
