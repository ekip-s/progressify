import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  url: "http://localhost:8080",
  realm: "dark_matter",
  clientId: "progressify_react_app",
});

export default keycloak;
