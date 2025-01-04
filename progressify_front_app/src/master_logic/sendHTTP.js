const send = async ({
  url,
  method,
  body,
  token,
  headers,
  setDataInfo,
  dataType,
}) => {
  const options = {
    method,
    headers: {
      "Content-Type": "application/json",
      ...headers,
      ...(token && { Authorization: `Bearer ${token}` }),
    },
    body: body ? JSON.stringify(body) : null,
  };

  try {
    const response = await fetch(
      `${import.meta.env.VITE_BACKEND_URL}${url}`,
      options,
    );

    if (!response.ok) {
      throw new Error(`Ошибка HTTP: ${response.status}`);
    }

    let result;

    if (dataType === "string") {
      result = await response.text();
    } else if (dataType === "json") {
      result = await response.json();
    }

    if (setDataInfo !== undefined) {
      setDataInfo(result);
    }
  } catch (err) {
    console.log(err.message);
  }
};

export { send };
