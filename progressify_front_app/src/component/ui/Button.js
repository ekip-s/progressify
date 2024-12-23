import styles from "./Button.module.css";

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

export default Button;
