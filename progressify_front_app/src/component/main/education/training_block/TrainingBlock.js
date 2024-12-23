import styles from "./TrainingBlock.module.css";
import { useState } from "react";
import LessonsList from "./lesson/LessonsList";

const TrainingBlock = ({ block, setData }) => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleDetails = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className={styles.trainingBlock}>
      <details open={isOpen}>
        <summary onClick={toggleDetails}>{block.name}</summary>
        <LessonsList
          lessons={block.lessons}
          setData={setData}
          blockId={block.id}
        />
      </details>
    </div>
  );
};

export default TrainingBlock;
