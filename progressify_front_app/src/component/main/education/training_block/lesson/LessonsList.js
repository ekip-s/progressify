import styles from "./LessonsList.module.css";
import Lesson from "./Lesson";
import NewLesson from "./NewLesson";

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
export default LessonsList;
