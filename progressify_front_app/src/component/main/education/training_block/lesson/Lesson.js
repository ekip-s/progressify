import styles from "./Lesson.module.css";
import StatusModule from "./StatusModule";
import { daysBetween } from "../../../../../logic/data-time-logic";

const Lesson = ({ lesson, setData, blockId }) => {
  return (
    <div id={lesson.id} className={styles.lesson}>
      <div className={styles.info}>
        <h3>{lesson.name}</h3>
        <p>{getStatusCount(lesson)}</p>
      </div>
      <StatusModule lesson={lesson} setData={setData} blockId={blockId} />
    </div>
  );
};

const getStatusCount = (lesson) => {
  if (lesson.status === "NEW") {
    return "Еще не начали делать";
  } else if (lesson.status === "IN_PROGRESS") {
    return `В работе ${daysBetween(new Date(lesson.startAT), new Date())}`;
  } else if (lesson.status === "DONE") {
    return `Сделано за ${daysBetween(new Date(lesson.startAT), new Date())}`;
  }
};

export default Lesson;
