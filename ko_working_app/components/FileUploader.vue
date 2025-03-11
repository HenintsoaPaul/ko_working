<template>
  <div>
    <h2>{{ title }}</h2>
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
import { ref, defineProps } from 'vue';
import { useImport } from '~/composable/useImport';
import type { ApiResponse, FileUploadResponse } from '~/types/api';
import { getApiError } from '~/utils/logger';

const props = defineProps<{
  title: string;
  apiUrl: string;
}>();

const apiMessage = ref("");
const file = ref(null);
const { sendCsv } = useImport();

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
    const response: ApiResponse<FileUploadResponse> = await sendCsv(formData, props.apiUrl);
    apiMessage.value = response.message;

    logApiResponse(props.apiUrl, response);
  } catch (error) {
    const err = getApiError(error);
    apiMessage.value = err.message;

    logApiError(props.apiUrl, error);
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