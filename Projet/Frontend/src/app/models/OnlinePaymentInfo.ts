import { MedicalOffice } from "./MedicalOffice";

export interface OnlinePayementInfo
{
    id              : number,
    email           : string,
    key             : string,
    medicalOffice   : MedicalOffice
}