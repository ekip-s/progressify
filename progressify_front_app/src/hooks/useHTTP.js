import { useEffect, useMemo, useState } from "react";
import info from "../info/info.json";

const useHTTP = ({ url, method, body, token, headers }) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const memoizedBody = useMemo(
    () => (body ? JSON.stringify(body) : null),
    [body],
  );
  const memoizedHeaders = useMemo(
    () => ({
      "Content-Type": "application/json",
      ...headers,
      ...(token && { Authorization: `Bearer ${token}` }),
    }),
    [headers, token],
  );

  useEffect(() => {
    if (data) return;

    setLoading(true);
    setError(null);
    const fetchData = async () => {
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
        const response = await fetch(`${info.backendURL}${url}`, options);

        if (!response.ok) {
          throw new Error(`Ошибка HTTP: ${response.status}`);
        }

        const result = await response.json();
        setData(result);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [url, method, memoizedBody, memoizedHeaders]);
  return { data, loading, error, setData };
};

export default useHTTP;
