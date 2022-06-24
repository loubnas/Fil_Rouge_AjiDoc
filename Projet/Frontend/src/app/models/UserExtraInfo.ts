import { User } from "./User";

export interface UserExtraInfo
{
    id?          : number,
    diplome?     : string,
    specialite?  : string ,

    diplomeImage? : string,
    cinImage? : string,
    
    
    description?     : string,
    user?        : User
}