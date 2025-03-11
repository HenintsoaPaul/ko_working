<template>
  <h1>Page d'import csv</h1>
  <template v-for="(elmt, i) in data">
    <FileUploader :title="elmt.title" :api-url="elmt.apiUrl" />
  </template>
</template>

<script setup lang="ts">
const hostUrl = useRuntimeConfig().public.hostUrl;
const apiUrl = ref<string>(`${hostUrl}/api/upload-csv`);

const data = ref([
  { apiUrl: `${apiUrl.value}/espace`, title: 'Import espace' },
  { apiUrl: `${apiUrl.value}/option`, title: 'Import option' },
  { apiUrl: `${apiUrl.value}/paiement`, title: 'Import paiement' },
  { apiUrl: `${apiUrl.value}/reservation`, title: 'Import reservation' },
]);
</script>