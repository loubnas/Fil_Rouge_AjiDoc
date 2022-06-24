import { MedicalOffice } from "./MedicalOffice";
import { Payment } from "./Payment";
import { User } from "./User";

export interface Appointement
{
    id?                  : number,
    dateTimeAppointement?: string,
    acceptanceMode?      : string,
    acceptanceDate?      : Date,
    appointementDate?      : Date,
    validationMode?      : string,
    validationDate?      : Date,
    userId?              : number,
    user?                : User,
    medicalOfficeId?      : number,
    medicalOffice?        : MedicalOffice,
    payment?             : Payment
}