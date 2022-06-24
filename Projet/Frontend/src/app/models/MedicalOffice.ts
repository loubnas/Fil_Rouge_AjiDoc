import { MedicalOfficeStaff } from "./MedicalOfficeStaff";
import { OnlinePayementInfo } from "./OnlinePaymentInfo";
import { Review } from "./Review";
import { User } from "./User";

export interface MedicalOffice
{
    id?                  : number,
    name?                : string,
    adress?              : string,
    phone?               : string,
    image?               : string,
    administrator?       : User,
    onlinePaymentInfo?   : OnlinePayementInfo,
    staff?               : MedicalOfficeStaff[],
    staffList?           : User[],
    reviews?             : Review[]
}