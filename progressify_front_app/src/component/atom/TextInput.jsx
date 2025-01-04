import styles from "./TextInput.module.css";
import { InputText } from "primereact/inputtext";
import PropTypes from "prop-types";

const TextInput = ({
  labelText,
  smallText,
  required,
  max,
  refLink,
  className,
}) => {
  return (
    <div className={styles.textInput}>
      <div className={styles.wrapper}>
        <label>{labelText}</label>
        <InputText
          maxLength={max}
          type="text"
          className={`${styles.inputText} ${className}`}
          ref={refLink}
          required={required}
        />
        <small>{smallText}</small>
      </div>
    </div>
  );
};

TextInput.propTypes = {
  labelText: PropTypes.string,
  smallText: PropTypes.string,
  required: PropTypes.bool,
  max: PropTypes.number,
  refLink: PropTypes.shape({
    current: PropTypes.any,
  }).isRequired,
  className: PropTypes.string,
};

export default TextInput;
