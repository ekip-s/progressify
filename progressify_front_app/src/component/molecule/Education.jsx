import styles from "./Education.module.css";
import { formatDateTime } from "../../master_logic/data-time-logic.js";
import { useDispatch } from "react-redux";
import { pageActions } from "../../store/page-slice.js";
import PropTypes from "prop-types";
import ProgressBar from "./ProgressBar.jsx";

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
      <td>
        <ProgressBar
          totalTasks={edu.total}
          completedTasks={edu.doneEdu}
          isShowText={false}
        />
      </td>
    </tr>
  );
};

Education.propTypes = {
  edu: PropTypes.shape({
    id: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    description: PropTypes.string,
    startAT: PropTypes.string,
    endAT: PropTypes.string,
    total: PropTypes.number.isRequired,
    doneEdu: PropTypes.number.isRequired,
  }).isRequired,
};

export default Education;
