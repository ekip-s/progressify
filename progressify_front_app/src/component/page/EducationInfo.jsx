import styles from "./EducationInfo.module.css";
import gstyle from "../../global.module.css";
import { useDispatch, useSelector } from "react-redux";
import useHTTP from "../../master_logic/hooks/useHTTP.js";
import ClosingButton from "../atom/ClosingButton.jsx";
import { pageActions } from "../../store/page-slice.js";
import { Fragment, useRef } from "react";
import TrainingBlock from "../organism/TrainingBlock.jsx";
import TextInput from "../atom/TextInput.jsx";
import Button from "../atom/Button.jsx";
import { send } from "../../master_logic/sendHTTP.js";

const EducationInfo = () => {
  const activePage = useSelector((state) => state.page.activePage);
  const token = useSelector((state) => state.auth.token);
  const dispatchActions = useDispatch();
  const newBlockNameRef = useRef();
  const { data, loading, setData } = useHTTP({
    url: `/education/api/v1/eduId/${activePage}`,
    method: "GET",
    body: null,
    token,
    headers: {},
  });

  const closePageHandler = () => {
    dispatchActions(pageActions.clearPage());
  };

  const deletePageHandler = () => {
    const isConfirmed = window.confirm(
      `Вы действительно хотиете удалить обучение: ${data.name}?`,
    );

    if (isConfirmed) {
      send({
        url: `/education/api/v1/eduId/${data.id}`,
        method: "DELETE",
        body: undefined,
        token,
        headers: {},
        setDataInfo: undefined,
        dataType: undefined,
      }).then(closePageHandler);
    }
  };

  const setBlockHandler = (newBlock) => {
    setData((prevData) => ({
      ...prevData,
      blocks: [...prevData.blocks, newBlock],
    }));
  };

  const newBlockHandler = (event) => {
    event.preventDefault();
    const body = {
      num: data.blocks.length + 1,
      name: newBlockNameRef.current.value,
      eduId: data.id,
    };
    send({
      url: "/training_block/api/v1",
      method: "POST",
      body,
      token,
      headers: {},
      setDataInfo: setBlockHandler,
      dataType: "json",
    });
    newBlockNameRef.current.value = "";
  };

  if (loading) return <div>Loading...</div>;

  return (
    <Fragment>
      <section className={styles.educationInfo}>
        <div className={gstyle.flexBetween}>
          <div>
            <h2>{data.name}</h2>
            <p>{data.description}</p>
          </div>
          <div>
            <Button
              className={styles.deleteBTN}
              type="button"
              text="Удалить"
              onClick={deletePageHandler}
            />
            <ClosingButton onClick={closePageHandler} />
          </div>
        </div>
      </section>
      <section className={styles.trainingBlock}>
        {data.blocks.map((block) => (
          <TrainingBlock key={block.id} block={block} setData={setData} />
        ))}
      </section>
      <section className={styles.newBlock}>
        <form className={gstyle.flexBetween} onSubmit={newBlockHandler}>
          <TextInput
            labelText="Введи название блока обучения:"
            required={true}
            refLink={newBlockNameRef}
          />
          <Button type="submit" text="Сохранить блок" />
        </form>
      </section>
    </Fragment>
  );
};

export default EducationInfo;
