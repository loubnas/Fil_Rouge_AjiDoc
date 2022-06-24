import { Appointement } from "./Appointement";

export interface Payment
{
    id?                  : number,
    online?            : boolean,
    onlineReference?     : string,
    appointement?        :Appointement
}