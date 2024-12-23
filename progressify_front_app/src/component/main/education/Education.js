import styles from "./Education.module.css";
import { formatDateTime } from "../../../logic/data-time-logic";
import { useDispatch } from "react-redux";
import { pageActions } from "../../../store/page-slice";

const Education = ({ edu }) => {
  const dispatchActions = useDispatch();

  const addActivePageHandler = () => {
    dispatchActions(pageActions.setActivePage(edu.id));
  };

  const getNameLink = () => {
    return (
      <div className={styles.tooltipContainer} onClick={addActivePageHandler}>
        <button className={styles.linkBtn}>{edu.name}</button>
        <span className={styles.tooltip}>{edu.description}</span>
      </div>
    );
  };

  const getCreatedAT = () => {
    return edu.startAT ? formatDateTime(edu.startAT) : "Нужно бы начать";
  };

  const getEndAT = () => {
    return edu.endAT ? formatDateTime(edu.endAT) : "В процессе";
  };

  return (
    <tr>
      <td>{getNameLink()}</td>
      <td>{getCreatedAT()}</td>
      <td>{getEndAT()}</td>
      <td></td>
    </tr>
  );
};

export default Education;
