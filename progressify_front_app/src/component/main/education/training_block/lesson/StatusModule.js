import styles from "./StatusModule.module.css";
import global from "../../../../../global.module.css";
import { send } from "../../../../../data/sendHTTP";
import { useSelector } from "react-redux";

const StatusModule = ({ lesson, setData, blockId }) => {
  const token = useSelector((state) => state.auth.token);

  const toInProgressHandler = () => {
    send({
      url: `/lesson/api/v1/lessonId/${lesson.id}/status/IN_PROGRESS`,
      method: "PATCH",
      body: null,
      token,
      headers: {},
      setDataInfo: undefined,
      dataType: null,
    }).then(
      updateLesson(
        blockId,
        lesson.id,
        {
          ...lesson,
          status: "IN_PROGRESS",
          startAT: new Date(),
        },
        setData,
      ),
    );
  };

  const toDoneHandler = () => {
    send({
      url: `/lesson/api/v1/lessonId/${lesson.id}/status/DONE`,
      method: "PATCH",
      body: null,
      token,
      headers: {},
      setDataInfo: undefined,
      dataType: null,
    }).then(
      updateLesson(
        blockId,
        lesson.id,
        {
          ...lesson,
          status: "DONE",
          endAT: new Date(),
        },
        setData,
      ),
    );
  };

  return (
    <div
      className={`${global.flex} ${global.end} ${global.center} ${styles.statusModule}`}
    >
      <StatusNode data="🛇" status="NEW" currentStatus={lesson.status} />
      <div
        className={`${styles.arrow} ${lesson.status !== "NEW" && styles.inactive}`}
        onClick={toInProgressHandler}
      >
        ⇒
      </div>
      <StatusNode data="✎" status="IN_PROGRESS" currentStatus={lesson.status} />
      <div
        className={`${styles.arrow} ${lesson.status !== "IN_PROGRESS" && styles.inactive}`}
        onClick={toDoneHandler}
      >
        ⇒
      </div>
      <StatusNode data="✔" status="DONE" currentStatus={lesson.status} />
    </div>
  );
};

const StatusNode = ({ data, status, currentStatus }) => {
  return (
    <div
      className={`${styles.circle} ${status !== currentStatus && styles.inactive}`}
    >
      {data}
      <div className={styles.tooltip}>{`Статус: ${status}`}</div>
    </div>
  );
};

const updateLesson = (blockId, lessonId, updatedLesson, setData) => {
  setData((prevData) => {
    const updatedBlocks = prevData.blocks.map((block) => {
      if (block.id === blockId) {
        const updatedLessons = block.lessons.map((lesson) => {
          if (lesson.id === lessonId) {
            return { ...lesson, ...updatedLesson };
          }
          return lesson;
        });
        return { ...block, lessons: updatedLessons };
      }
      return block;
    });
    return { ...prevData, blocks: updatedBlocks };
  });
};

export default StatusModule;
