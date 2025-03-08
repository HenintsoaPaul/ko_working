export interface LoginRequest {
    email?: string,
    password?: string
}

export interface LoginResponse {
    email: string,
    name: string,
    isAdmin: boolean
}