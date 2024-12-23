import { useSelector } from "react-redux";
import { useEffect } from "react";
import { useNavigate } from "react-router";
import EducationList from "./education/EducationList";
import NewEducation from "./education/NewEducation";
import EducationInfo from "./education/EducationInfo";

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
