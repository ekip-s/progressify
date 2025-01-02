import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  url: "https://darkmatterauth.ru:8443",
  realm: "dark_matter",
  clientId: "progressify_react_app",
});

export default keycloak;
