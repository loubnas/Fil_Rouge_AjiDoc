import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionVariables } from 'src/app/common/SessionVariables';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  
  public searchKeyword:String="";
  public searchVille:String="";
  public searchSpecialite:String="";

  search():void{

    SessionVariables.searchKeyword=this.searchKeyword;
    SessionVariables.searchPage=0;
    SessionVariables.searchLimit=10;
    SessionVariables.updateSessionVariables();
    
    this.router.navigateByUrl("/searchDoctor");
  }

}
