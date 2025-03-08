import type { LoginRequest, LoginResponse } from "~/types/login";

export const useLogin = () => {
  // Send payload to backend in order to authenticate it
  const authenticate = async (
    loginRequest: LoginRequest,
    apiUrl: string
  ): Promise<LoginResponse> => {
    try {
      return await $fetch<LoginResponse>(apiUrl, {
        method: "POST",
        body: loginRequest,
      });
    } catch (err) {
      console.error("Error during authentication", err);
      throw err;
    }
  };

  // Create a global loginResponse state to track current logged user's informations
  const loginResponse = useState<LoginResponse | null>(
    "loginResponse",
    () => null
  );
  
  // Function to set user after login
  const setLoginResponse = (lr: LoginResponse) => {
    loginResponse.value = lr;
  };

  // Function to clear user data (logout)
  const logout = () => {
    loginResponse.value = null;
  };

  return {
    authenticate,
    loginResponse,
    setLoginResponse,
    logout,
  };
};
