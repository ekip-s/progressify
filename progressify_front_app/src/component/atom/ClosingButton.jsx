import styles from "./ClosingButton.module.css";
import PropTypes from "prop-types";

const ClosingButton = ({ onClick }) => {
  return (
    <button
      onClick={onClick}
      type="button"
      className={styles.button}
      aria-label="Close"
    >
      ×
    </button>
  );
};

ClosingButton.propTypes = {
  onClick: PropTypes.func,
};
export default ClosingButton;
