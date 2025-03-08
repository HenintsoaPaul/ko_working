import { LoginResponse } from "~/types/login";

export default defineEventHandler(async (event): Promise<LoginResponse> => {
  const body = await readBody(event);

  if (body.email === "test@gmail.com" && body.password === "password") {
    return {
      email: "test@gmail.com",
      name: "test",
      isAdmin: true,
    };
  } else {
    throw createError({ statusCode: 401, message: "Damn u" });
  }
});
