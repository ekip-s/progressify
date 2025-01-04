import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  url: import.meta.env.VITE_KEYCLOAK_URL,
  realm: "dark_matter",
  clientId: "progressify_react_app",
});

export default keycloak;
