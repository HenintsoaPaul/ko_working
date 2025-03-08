<script setup lang="ts">
import { z } from 'zod';
import type { Form, FormSubmitEvent } from '#ui/types';
import { useLogin } from '~/composable/useLogin';
import type { LoginRequest, LoginResponse } from '~/types/login';

const schema = z.object({
    email: z.string().email('Invalid email'),
    password: z.string().min(3, 'Must be at least 3 characters')
});

type Schema = z.output<typeof schema>;

const formState = reactive({
    email: undefined,
    password: undefined
});

const form = ref<Form<Schema>>();
const formError = ref<string>('');

const { authenticate, setLoginResponse } = useLogin();
const apiUrl = "/api/login";
const router = useRouter();

const onSubmit = async (event: FormSubmitEvent<Schema>) => {
    try {
        // Setup payload
        const loginRequest = reactive<LoginRequest>({
            email: formState.email,
            password: formState.password
        });
        console.log("loginRequest: ", loginRequest);


        // Authenticate with backend server
        const loginResponse: LoginResponse = await authenticate(loginRequest, apiUrl);
        console.log("loginResponse: ", loginResponse);

        // Save user authenticated in the state manager
        setLoginResponse(loginResponse);

        // redirection
        router.push("/dashboard");
    }
    catch (err: any) {
        if (err.statusCode === 422) {
            form.value!.setErrors(err.data.errors.map((err: any) => ({
                // Map validation errors to { path: string, message: string }
                message: err.message,
                path: err.path,
            })))
        } else {
            formError.value = err.message;
        }
    }
};
</script>

<template>
    <div>
        <UForm :schema="schema" :state="formState" @submit="onSubmit">
            <UFormGroup label="Email" name="email">
                <UInput v-model="formState.email" type="email" />
            </UFormGroup>

            <UFormGroup label="Password" name="password">
                <UInput v-model="formState.password" type="password" />
            </UFormGroup>

            <p class="form-error" v-if="formError">
                {{ formError }}
            </p>

            <UButton type="submit">
                Submit
            </UButton>
        </UForm>
    </div>
</template>

<style></style>