<template>
  <div>
    <h1>Upload CSV File</h1>
    <form @submit.prevent="submitForm">
      <input type="file" @change="handleFileUpload" accept=".csv" />
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useImport } from '~/composable/useImport';
import type { ApiResponse, FileUploadResponse } from '~/types/api';

const file = ref(null);
const { sendCsv } = useImport();

const host = "http://localhost:8080";
const apiUrl = "/api/upload-csv";

const handleFileUpload = (event: any) => {
  file.value = event.target.files[0];
};

const submitForm = async () => {
  if (!file.value) {
    alert("Please select a file first!");
    return;
  }

  const formData = new FormData();
  formData.append("file", file.value);

  try {
    const response: ApiResponse<FileUploadResponse> = await sendCsv(formData, host + apiUrl);

    logApiResponse(apiUrl, response);
  } catch (error) {
    logApiError(apiUrl, error);
  }
};
</script>

<style scoped>
h1 {
  margin-bottom: 20px;
}

form {
  display: flex;
  flex-direction: column;
}

input[type="file"] {
  margin-bottom: 10px;
}

button {
  width: 100px;
}
</style>