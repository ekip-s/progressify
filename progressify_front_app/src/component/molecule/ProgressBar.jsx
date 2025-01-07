import styles from "./ProgressBar.module.css";
import PropTypes from "prop-types";

const ProgressBar = ({ totalTasks, completedTasks }) => {
  const percentage =
    totalTasks === 0 ? 0 : Math.min((completedTasks / totalTasks) * 100, 100);

  const getColor = (percentage) => {
    if (percentage === 100) return "#28a745"; // Ярко зеленый для 100%
    if (percentage >= 66) return "#28a745"; // Зеленый для процентов > 66
    if (percentage >= 33) return "#ffc107"; // Оранжевый для процентов от 33 до 66
    return "#dc3545"; // Красный для процентов < 33
  };

  const progressBarColor = getColor(percentage);

  return (
    <div className={styles.container}>
      <div className={styles.heading}>
        <strong>
          {completedTasks}/{totalTasks} уроков завершено
        </strong>
      </div>

      <div className={styles.progressBar}>
        <div
          className={styles.progress}
          style={{
            width: `${percentage}%`,
            backgroundColor: progressBarColor,
          }}
        ></div>
      </div>

      <div className={styles.percentage} style={{ color: progressBarColor }}>
        {Math.round(percentage)}%
      </div>
    </div>
  );
};

ProgressBar.propTypes = {
  totalTasks: PropTypes.number.isRequired,
  completedTasks: PropTypes.number.isRequired,
};

export default ProgressBar;
