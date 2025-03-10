<template>
  <div>
    <h1>Upload CSV File</h1>
    <div v-if="apiMessage">
      <p>{{ apiMessage }}</p>
    </div>
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
import { getApiError } from '~/utils/logger';

const file = ref(null);
const { sendCsv } = useImport();

const apiMessage = ref("");

const host = "http://localhost:8080";
const apiUrl = "/api/upload-csv/espace";

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
    apiMessage.value = response.message;

    logApiResponse(apiUrl, response);
  } catch (error) {
    const err = getApiError(error);
    apiMessage.value = err.message;

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