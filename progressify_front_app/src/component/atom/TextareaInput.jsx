import styles from "./TextareaInput.module.css";
import { InputTextarea } from "primereact/inputtextarea";
import PropTypes from "prop-types";

const TextareaInput = ({ placeholder, refLink }) => {
  return (
    <InputTextarea
      placeholder={placeholder}
      className={styles.inputTextarea}
      ref={refLink}
    />
  );
};

TextareaInput.propTypes = {
  placeholder: PropTypes.string.isRequired,
  refLink: PropTypes.shape({
    current: PropTypes.any,
  }).isRequired,
};

export default TextareaInput;
