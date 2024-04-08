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
    role: string = "";
    constructor(){}

}

export class AccessUser{
    client_id = "ecommerce_kc";
    username: string = "";
    password: string = "";
    grant_type: string = "password";
}

export class LoggedUser{
        name:string = "";
        username:string = "";
        email:string = "";
        userRoles: string[] = [];
        isAdmin: boolean = false;
        isLogged: boolean = false;
}