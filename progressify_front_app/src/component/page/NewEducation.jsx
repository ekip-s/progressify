import styles from "./NewEducation.module.css";
import Card from "../atom/Card.jsx";
import Button from "../atom/Button.jsx";
import TextInput from "../atom/TextInput.jsx";
import TextareaInput from "../atom/TextareaInput.jsx";
import { useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { pageActions } from "../../store/page-slice.js";
import { send } from "../../master_logic/sendHTTP.js";
import ClosingButton from "../atom/ClosingButton.jsx";

const NewEducation = () => {
  const token = useSelector((state) => state.auth.token);
  const dispatchActions = useDispatch();
  const nameRef = useRef();
  const descriptionRef = useRef();

  const createNewEduHandler = (event) => {
    event.preventDefault();
    send({
      url: "/education/api/v1",
      method: "POST",
      body: {
        name: nameRef.current.value,
        description: descriptionRef.current.value,
      },
      token,
      headers: {},
      setDataInfo: transitionToNewEduHandler,
      dataType: "string",
    });
  };

  const transitionToNewEduHandler = (id) => {
    dispatchActions(pageActions.setActivePage(id.replace(/^"|"$/g, "")));
  };

  const clearPageHandler = () => {
    dispatchActions(pageActions.clearPage());
  };

  return (
    <section className={styles.newEducation}>
      <Card className={styles.formPdn}>
        <form onSubmit={createNewEduHandler}>
          <div className={styles.flexBox}>
            <h3>Новый курс:</h3>
            <ClosingButton onClick={clearPageHandler} />
          </div>
          <div>
            <TextInput
              labelText="Название курса:"
              max={50}
              refLink={nameRef}
              required
            />
            <TextareaInput
              placeholder="Введи описание курса"
              max={200}
              refLink={descriptionRef}
            />
          </div>
          <div className={styles.btn}>
            <Button type="submit" text="Создать" />
          </div>
        </form>
      </Card>
    </section>
  );
};

export default NewEducation;
