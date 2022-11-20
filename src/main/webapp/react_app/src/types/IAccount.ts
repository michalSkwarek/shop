export default interface IAccount {
    id?: any | null,
    email: string,
    password: string,
    confirmPassword: string,
    newsletter: boolean,
    acceptTerms: boolean,
}