import styles from "./NewLesson.module.css";
import TextInput from "../atom/TextInput.jsx";
import Button from "../atom/Button.jsx";
import { useRef } from "react";
import { send } from "../../master_logic/sendHTTP.js";
import { useSelector } from "react-redux";
import PropTypes from "prop-types";

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

NewLesson.propTypes = {
  lessonsCount: PropTypes.number.isRequired,
  blockId: PropTypes.string.isRequired,
  setData: PropTypes.func.isRequired,
};

export default NewLesson;
