import { MedicalOffice } from "./MedicalOffice";
import { User } from "./User";

export interface MedicalOfficeStaff
{
    id?              : number,
    user?            : User,
    medicalOfficeStaffUser?            : User,
    medicalOfficeId? : number,
    medicalOffice?   : MedicalOffice
}