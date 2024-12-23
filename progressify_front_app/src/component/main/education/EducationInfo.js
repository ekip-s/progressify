import styles from "./EducationInfo.module.css";
import gstyle from "../../../global.module.css";
import { useDispatch, useSelector } from "react-redux";
import useHTTP from "../../../hooks/useHTTP";
import ClosingButton from "../../ui/ClosingButton";
import { pageActions } from "../../../store/page-slice";
import { Fragment, useRef } from "react";
import TrainingBlock from "./training_block/TrainingBlock";
import TextInput from "../../ui/TextInput";
import Button from "../../ui/Button";
import { send } from "../../../data/sendHTTP";

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

  const setBlockHandler = (newBlock) => {
    setData((prevData) => ({
      ...prevData, // Копируем все существующие данные
      blocks: [...prevData.blocks, newBlock], // Добавляем новый объект в конец массива blocks
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
          <ClosingButton onClick={closePageHandler} />
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
