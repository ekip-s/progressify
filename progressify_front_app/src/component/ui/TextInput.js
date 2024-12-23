import styles from "./TextInput.module.css";
import { InputText } from "primereact/inputtext";

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

export default TextInput;
