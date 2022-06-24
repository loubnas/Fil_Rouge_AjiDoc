import { MedicalOffice } from "./MedicalOffice";
import { UserDocument } from "./UserDocument";
import { UserExtraInfo } from "./UserExtraInfo";

export interface User{
    id?              : number,
    firstname?       : string ,
    lastname?        : string,
    adress?          : string,
    phone?           : string,
    ville?           : string,
    email?           : String,
    password?        : string,
    validatedBy?     : User,
    validateDate?    : Date,
    type?            : string,
    image?           : string,
    userExtraInfo?   : UserExtraInfo,
    medicalOffices?   : MedicalOffice[],
    documents?       : UserDocument[]
    medicalOfficeId?       : number
}