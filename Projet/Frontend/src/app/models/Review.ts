import { MedicalOffice } from "./MedicalOffice";
import { User } from "./User";

export interface Review
{
    id?              : number,
    review?          : string,
    score?           : number,
    userId?          : number,
    user?            : User,
    medicalOfficeId? : number,
    medicalOffice?   : MedicalOffice
}