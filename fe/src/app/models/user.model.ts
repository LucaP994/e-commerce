import { Address } from "./address.model";

export class User{
    id: number = 0;
    username: string = "";
    firstname: string = "";
    lastname: string = "";
    password: string = "";
    email: string = "";
    profileImg: string = "";
    dateOfBirthStr: string = "";
    boughtProductsReference: number[] = [];
    address: Address;
    constructor(){}

}