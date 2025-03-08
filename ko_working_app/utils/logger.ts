export const logApiCall = (method: string, url: string, data?: any) => {
  console.log(`[API CALL] ${method.toUpperCase()} ${url}`, data || "");
};

export const logApiResponse = (url: string, response: any) => {
  console.log(`[API RESPONSE] ${url}`, response);
};

export const logApiError = (url: string, error: any) => {
  console.error(`[API ERROR] ${url}`, error);
};
