import { useSelector } from "react-redux";
import { useNavigate } from "react-router";
import { useEffect } from "react";
import EducationList from "../page/EducationList.jsx";
import NewEducation from "../page/NewEducation.jsx";
import EducationInfo from "../page/EducationInfo.jsx";

const MainRouter = () => {
  const activePage = useSelector((state) => state.page.activePage);
  const isAuth = useSelector((state) => state.auth.authenticated);
  const navigate = useNavigate();

  useEffect(() => {
    if (!isAuth) {
      navigate("/home");
    }
  }, [isAuth]);

  if (activePage === null) {
    return <EducationList />;
  } else if (activePage === "new_edu") {
    return <NewEducation />;
  } else {
    return <EducationInfo />;
  }
};

export default MainRouter;
