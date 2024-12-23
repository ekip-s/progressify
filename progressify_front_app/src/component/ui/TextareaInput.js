import styles from "./TextareaInput.module.css";
import { InputTextarea } from "primereact/inputtextarea";

const TextareaInput = ({ placeholder, refLink }) => {
  return (
    <InputTextarea
      placeholder={placeholder}
      className={styles.inputTextarea}
      ref={refLink}
    />
  );
};

export default TextareaInput;
