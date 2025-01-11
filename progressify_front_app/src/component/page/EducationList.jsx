import { Fragment } from "react";
import styles from "./EducationList.module.css";
import global from "../../global.module.css";
import Button from "../atom/Button.jsx";
import { useDispatch, useSelector } from "react-redux";
import { pageActions } from "../../store/page-slice.js";
import useHTTP from "../../master_logic/hooks/useHTTP.js";
import Education from "../molecule/Education.jsx";

const EducationList = () => {
  const dispatchActions = useDispatch();
  const token = useSelector((state) => state.auth.token);
  const { data, loading, setData } = useHTTP({
    url: `/education/api/v1`,
    method: "GET",
    body: null,
    token,
    headers: {},
  });

  const newEduHandler = () => {
    dispatchActions(pageActions.setActivePage("new_edu"));
  };

  const getInfoData = () => {
    if (loading) {
      return (
        <div className={`${global.flex} ${global.center} ${global.jcCenter}`}>
          <div className={styles.loading}>Загрузка ...</div>
        </div>
      );
    } else if (data.length === 0) {
      return (
        <div>
          <div className={`${global.flex} ${global.center} ${global.jcCenter}`}>
            <div className={styles.notEdu}>У тебя пока нет обучений!</div>
          </div>
          <div className={styles.btn}>
            <Button
              type="button"
              text="Новое Обучение"
              onClick={newEduHandler}
            />
          </div>
        </div>
      );
    } else {
      return (
        <Fragment>
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
          <div className={styles.btn}>
            <Button
              type="button"
              text="Новое Обучение"
              onClick={newEduHandler}
            />
          </div>
        </Fragment>
      );
    }
  };

  return (
    <Fragment>
      <section className={styles.educationList}>{getInfoData()}</section>
    </Fragment>
  );
};

export default EducationList;
