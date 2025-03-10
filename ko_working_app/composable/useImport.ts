import type { ApiResponse, FileUploadResponse } from "~/types/api";

export const useImport = () => {
  // Send csv file
  const sendCsv = async (
    formData: FormData,
    apiUrl: string
  ): Promise<ApiResponse<FileUploadResponse>> => {
    try {
      logApiCall("POST", apiUrl, formData);

      return await $fetch<ApiResponse<FileUploadResponse>>(apiUrl, {
        method: "POST",
        body: formData,
      });
    } catch (err) {
      console.error("Error during useImport().sendCsv", err);
      throw err;
    }
  };

  return {
    sendCsv,
  };
};
