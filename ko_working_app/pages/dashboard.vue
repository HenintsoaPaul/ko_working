<script setup lang="ts">
import { useLogin } from '~/composable/useLogin';

const { loginResponse } = useLogin();
</script>

<template>
    <h1>Welcome, {{ loginResponse?.name }}</h1>
</template>