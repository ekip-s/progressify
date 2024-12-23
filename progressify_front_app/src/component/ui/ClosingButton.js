import styles from "./ClosingButton.module.css"

const ClosingButton = ({onClick}) => {
    return <button onClick={onClick} type="button" className={styles.button} aria-label="Close">
        ×
    </button>
}

export default ClosingButton;
