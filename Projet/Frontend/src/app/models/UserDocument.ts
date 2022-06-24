import { User } from "./User";

export interface UserDocument
{
    id          : number,
    document    : string,
    userId      : number,
    user        : User
}