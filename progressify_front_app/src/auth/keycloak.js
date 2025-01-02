import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  url: "https://darkmatterauth.ru",
  realm: "dark_matter",
  clientId: "progressify_react_app",
});

export default keycloak;
