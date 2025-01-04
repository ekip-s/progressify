import { Fragment } from "react";
import styles from "./EducationList.module.css";
import Button from "../../ui/Button";
import { useDispatch, useSelector } from "react-redux";
import { pageActions } from "../../../store/page-slice";
import useHTTP from "../../../hooks/useHTTP";
import Education from "./Education";

const EducationList = () => {
  const dispatchActions = useDispatch();
  const clientId = useSelector((state) => state.auth.clientId);
  const token = useSelector((state) => state.auth.token);
  const { data, setData } = useHTTP({
    url: `/education/api/v1/userId/${clientId}`,
    method: "GET",
    body: null,
    token,
    headers: {},
  });

  const newEduHandler = () => {
    dispatchActions(pageActions.setActivePage("new_edu"));
  };

  const getInfoData = () => {
    if (!data) {
      return (
        <div>
          <div>У тебя пока нет обучений!</div>
        </div>
      );
    } else {
      return (
        <table className={styles.table}>
          <thead>
            <tr>
              <th>Обучение</th>
              <th>Начато:</th>
              <th>Завершено:</th>
              <th>Прогресс</th>
            </tr>
          </thead>
          <tbody>
            {data.map((edu) => (
              <Education key={edu.id} edu={edu} setData={setData} />
            ))}
          </tbody>
        </table>
      );
    }
  };

  return (
    <Fragment>
      <section className={styles.educationList}>{getInfoData()}</section>
      <div className={styles.btn}>
        <Button type="button" text="Новое Обучение" onClick={newEduHandler} />
      </div>
    </Fragment>
  );
};

export default EducationList;
