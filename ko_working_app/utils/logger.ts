import type { ApiResponse } from "~/types/api";

export const logApiCall = (method: string, url: string, data?: any) => {
  console.log(`[API CALL] ${method.toUpperCase()} ${url}`, data || "");
};

export const logApiResponse = (url: string, response: any) => {
  console.log(`[API RESPONSE] ${url}`, response);
};

export const logApiError = (url: string, error: any) => {
  let err = getApiError(error);

  console.error(`[API ERROR] ${url}`, err);
};

export const getApiError = (error: any): ApiResponse<any>|any => {
  if (error instanceof Error && 'response' in error) {
    return (error as any).response._data;
  }

  return error;
}
