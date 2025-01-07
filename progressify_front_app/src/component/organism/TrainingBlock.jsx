import styles from "./TrainingBlock.module.css";
import { useState } from "react";
import PropTypes from "prop-types";
import LessonsList from "./LessonsList.jsx";
import ProgressBar from "../molecule/ProgressBar.jsx";

const TrainingBlock = ({ block, setData }) => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleDetails = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className={styles.trainingBlock}>
      <details open={isOpen}>
        <summary onClick={toggleDetails}>
          <div>{block.name}</div>
          <ProgressBar
            completedTasks={
              block.lessons.filter((task) => task.status === "DONE").length
            }
            totalTasks={block.lessons.length}
          />
        </summary>
        <LessonsList
          lessons={block.lessons}
          setData={setData}
          blockId={block.id}
        />
      </details>
    </div>
  );
};

TrainingBlock.propTypes = {
  block: PropTypes.shape({
    id: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    lessons: PropTypes.arrayOf(
      PropTypes.shape({
        id: PropTypes.string.isRequired,
        status: PropTypes.string.isRequired,
        name: PropTypes.string.isRequired,
        startAT: PropTypes.number,
        endAT: PropTypes.number,
      }),
    ),
  }),
  setData: PropTypes.func.isRequired,
};

export default TrainingBlock;
