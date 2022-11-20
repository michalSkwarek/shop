import ICategory from "./ICategory";
import ICompany from "./ICompany";

export default interface IProduct {
    id?: any | null,
    name: string,
    description: string,
    unitPrice: number | null,
    category: ICategory | null,
    company: ICompany | null,
}