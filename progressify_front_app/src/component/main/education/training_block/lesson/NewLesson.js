import styles from "./NewLesson.module.css";
import TextInput from "../../../../ui/TextInput";
import Button from "../../../../ui/Button";
import { useRef } from "react";
import { send } from "../../../../../data/sendHTTP";
import { useSelector } from "react-redux";

const NewLesson = ({ lessonsCount, blockId, setData }) => {
  const token = useSelector((state) => state.auth.token);
  const newLessonRef = useRef();

  const addNewLesson = (data) => {
    setData((prevData) => {
      const updatedBlocks = prevData.blocks.map((block) => {
        if (block.id === blockId) {
          return {
            ...block,
            lessons: [...block.lessons, data],
          };
        }
        return block;
      });
      return {
        ...prevData,
        blocks: updatedBlocks,
      };
    });
  };

  const newLessonHandler = (event) => {
    event.preventDefault();

    console.log(newLessonRef.current);

    const lesson = {
      num: ++lessonsCount,
      name: newLessonRef.current.value,
      blockId: blockId,
    };

    send({
      url: "/lesson/api/v1",
      method: "POST",
      body: lesson,
      token,
      headers: {},
      setDataInfo: addNewLesson,
      dataType: "json",
    });

    newLessonRef.current.value = null;
  };

  return (
    <div className={styles.newLesson}>
      <div className={styles.info}>
        <h3> + Добавить урок</h3>
      </div>
      <form id={`${blockId}_form`} onSubmit={newLessonHandler}>
        <TextInput
          className={styles.inputStyle}
          required
          max={255}
          refLink={newLessonRef}
        />
        <Button text="Сохранить" type="submit" />
      </form>
    </div>
  );
};

export default NewLesson;

//делаем обновление текущего состояния;
//чистим поле ввода;
