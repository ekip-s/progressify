import { useEffect } from "react";
import Keycloak from "../../auth/keycloak.js";
import { useSelector, useDispatch } from "react-redux";
import authActions from "../../store/auth-slice.js";

const keycloak = Keycloak;

const useKeycloak = () => {
  const isAuth = useSelector((state) => state.auth.authenticated);
  const dispatchActions = useDispatch();

  const addAuthInfoHandler = (auth) => {
    dispatchActions(authActions.actions.authInit(auth));
  };

  useEffect(() => {
    if (!isAuth) {
      keycloakInit();
    }
    if (isAuth) {
      dispatchActions(authActions.actions.addData(getUserInfo()));
    }
  }, [isAuth]);

  const getUserInfo = () => {
    const token = getAccessToken();
    const payloadBase64 = token
      .split(".")[1]
      .replace(/-/g, "+")
      .replace(/_/g, "/");
    const decodedToken = JSON.parse(atob(payloadBase64));
    return {
      token,
      clientId: decodedToken.sub,
    };
  };

  const getAccessToken = () => keycloak.token;

  const keycloakInit = () => {
    if (isAuth) {
      return;
    }

    keycloak
      .init({ onLoad: "check-sso" })
      .then((auth) => {
        addAuthInfoHandler(auth);
      })
      .catch((e) => console.log("Ошибка: " + e));
  };

  const loginHandler = () => {
    keycloak.login().catch(() => {
      console.log("Ошибка при авторизации");
      addAuthInfoHandler(false);
    });
  };
  const logoutHandler = () => {
    keycloak.logout();
  };

  return { keycloakInit, loginHandler, logoutHandler, isAuth };
};

export default useKeycloak;
