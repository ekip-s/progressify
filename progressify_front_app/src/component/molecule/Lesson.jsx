import styles from "./Lesson.module.css";
import StatusModule from "./StatusModule";
import { daysBetween } from "../../master_logic/data-time-logic.js";
import PropTypes from "prop-types";

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
    return `Сделано за ${daysBetween(new Date(lesson.startAT), new Date(lesson.endAT))}`;
  }
};

Lesson.propTypes = {
  lesson: PropTypes.shape({
    id: PropTypes.string.isRequired,
    status: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    startAT: PropTypes.string,
    endAT: PropTypes.string,
  }),
  setData: PropTypes.func.isRequired,
  blockId: PropTypes.string.isRequired,
};

export default Lesson;
