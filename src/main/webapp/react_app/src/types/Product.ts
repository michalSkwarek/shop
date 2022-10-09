import ICategoryData from "./Category";
import ICompanyData from "./Company";

export default interface IProductData {
    id?: any | null,
    name: string,
    description: string,
    unitPrice: number,
    category: ICategoryData,
    company: ICompanyData,
}