import styles from "./LessonsList.module.css";
import Lesson from "../molecule/Lesson.jsx";
import PropTypes from "prop-types";
import NewLesson from "../molecule/NewLesson.jsx";

const LessonsList = ({ lessons, setData, blockId }) => {
  return (
    <section className={styles.lessonsList}>
      {lessons.map((lesson) => (
        <Lesson
          key={lesson.id}
          lesson={lesson}
          setData={setData}
          blockId={blockId}
        />
      ))}
      <NewLesson
        lessonsCount={lessons.length}
        blockId={blockId}
        setData={setData}
      />
    </section>
  );
};

LessonsList.propTypes = {
  lessons: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      status: PropTypes.string.isRequired,
      name: PropTypes.string.isRequired,
      startAT: PropTypes.number,
      endAT: PropTypes.number,
    }),
  ),
  setData: PropTypes.func.isRequired,
  blockId: PropTypes.string.isRequired,
};

export default LessonsList;
