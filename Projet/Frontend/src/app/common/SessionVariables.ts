import { JsonpClientBackend } from "@angular/common/http";
import { User } from "../models/User";

export class SessionVariables{
    public static connectedUser:any=null;
    public static connectionToken:any="";//"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsb3VibmFAZ21haWwuY29tIiwiZXhwIjoxNjU1NDEyNjcwfQ.HUHHp9u0apYFrWEPd2QxM8tSGGm1fIvUvzr2tjziELgjFv4ZFdhNdcAWaTSIchchZ9J02Wwpw3d1DpxMAMRMkQ";
    public static serverUrl:string="http://localhost:9090";

    public static searchKeyword:String="";
    public static searchPage:number=0;
    public static searchLimit:number=10;
    public static selectedMO:any={};

    public static updateSessionVariables() {
        localStorage.setItem("SessionVars",JSON.stringify(
            {
                user:this.connectedUser,
                token:this.connectionToken,
                searchKeyword:this.searchKeyword,
                searchPage:this.searchPage,
                searchLimit:this.searchLimit,
                selectedMO:this.selectedMO
            }));
    }

    public static getSessionVariables() {
        if(localStorage.getItem("SessionVars")!=null){
        this.connectedUser=JSON.parse(localStorage.getItem("SessionVars")!).user;
        this.connectionToken=JSON.parse(localStorage.getItem("SessionVars")!).token;

        this.selectedMO=JSON.parse(localStorage.getItem("SessionVars")!).selectedMO;
        
        this.searchKeyword=JSON.parse(localStorage.getItem("SessionVars")!).searchKeyword;
        this.searchPage=JSON.parse(localStorage.getItem("SessionVars")!).searchPage;
        this.searchLimit=JSON.parse(localStorage.getItem("SessionVars")!).searchLimit;
        
        }
    }

    public static clearSessionVariables(){
        this.connectedUser=null;
        this.connectionToken=null;
        this.searchKeyword="";
        this.searchPage=0;
        this.searchLimit=10;
        this.selectedMO={};
        localStorage.clear();
    }
    
}