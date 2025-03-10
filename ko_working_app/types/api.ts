export interface ApiResponse<T> {
  status: number;
  message: string; 
  data: T | null; 
  timestamp?: string;
}

export interface FileUploadResponse {
  fileName: string;
  fileSize: number;
  uploadPath: string;
}
