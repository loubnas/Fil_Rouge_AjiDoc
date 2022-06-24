import { AfterContentInit, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { SessionVariables } from 'src/app/common/SessionVariables';
import { MedicalOffice } from 'src/app/models/MedicalOffice';
import { MedicalOfficeService } from 'src/app/services/medical-office.service';

@Component({
  selector: 'app-search-doctor',
  templateUrl: './search-doctor.component.html',
  styleUrls: ['./search-doctor.component.css']
})
export class SearchDoctorComponent implements OnInit,AfterContentInit {

  constructor(private router:Router,private medicalOfficeService:MedicalOfficeService) { }

  public searchKeyword:String="";
  public searchedKeyword:String="";
  public searchPage:number=0;
  public searchLimit:number=1;

  public MOs:MedicalOffice[]=[];
  public searchResult:any={};

  ngAfterContentInit(): void {
    
  

  }

  
  ngOnInit(): void {
    SessionVariables.getSessionVariables();
    this.searchKeyword=SessionVariables.searchKeyword;
    this.searchedKeyword=SessionVariables.searchKeyword;
    
    this.searchPage=SessionVariables.searchPage;
    this.searchLimit=SessionVariables.searchLimit;

    this.medicalOfficeService.search(this.searchKeyword,this.searchPage,this.searchLimit).subscribe(data=>{
      this.MOs=data.content;
      this.searchResult=data;
      console.log(data);
    },
    error=>{

    });
  }

  details(mo:any){
    
    SessionVariables.selectedMO=mo;
    SessionVariables.updateSessionVariables();
    this.router.navigateByUrl("/profilDoctor");
  } 
  
  makeAppointement(mo:any){
    
    SessionVariables.selectedMO=mo;
    SessionVariables.updateSessionVariables();
    this.router.navigateByUrl("/makeAppointement");
  }

  search():void{
    SessionVariables.searchKeyword=this.searchKeyword;
    SessionVariables.searchPage=0;
    SessionVariables.searchLimit=10;
    SessionVariables.updateSessionVariables();
    this.searchPage=0;

    
    this.searchedKeyword=SessionVariables.searchKeyword;
    
     
    this.medicalOfficeService.search(this.searchKeyword,this.searchPage,this.searchLimit).subscribe(data=>{
      this.MOs=data.content;
      this.searchResult=data;
      console.log(data);
    },
    error=>{

    });
  }

    loading: boolean=false;


    getPage(page: number) {
        this.loading = true;
        this.searchPage=page-1;
        SessionVariables.searchPage=this.searchPage;
        SessionVariables.updateSessionVariables();
        this.medicalOfficeService.search(this.searchKeyword,this.searchPage,this.searchLimit).subscribe(data=>{
          this.MOs=data.content;
          this.searchResult=data;
          console.log(data);
          this.loading=false;
        },
        error=>{
    
        });
        
    }

    defaultImage(event : any){
      event.target.src ="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAIBAQEBAQIBAQECAgICAgQDAgICAgUEBAMEBgUGBgYFBgYGBwkIBgcJBwYGCAsICQoKCgoKBggLDAsKDAkKCgr/2wBDAQICAgICAgUDAwUKBwYHCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgr/wgARCADvAVIDASIAAhEBAxEB/8QAHQAAAAcBAQEAAAAAAAAAAAAAAAECAwUGBwQICf/EABsBAAEFAQEAAAAAAAAAAAAAAAABAgMEBQYH/9oADAMBAAIQAxAAAAH0QZDDqgAAZc0PNWsQgptr1EBHMagoAZqAjNQJUagQazBsLMGwswaJwgbJZA2S0ghLiQbMEBkABkABkABkABggAAAAAIZyXQ3rZjd5g7DWtdSgunZCgoAo1glRrBCjUCDWByAsAhFZ8sVNb2Q74K1tln00h0r3PMpeQDROoBpHPgUF7di8mM52/wCxz8y+hNHn5YAT0gAAAMAQAAEGhtZ7ut+h1sPZmOqxhmoKnoGsKALCgJSjHEazc5BrMGw4FPLGOaJU+e7+86jKSgl3SHN7gYfsSFe629X0i8/URjZub9IytvVXYrOBaxpWCTVfX58vX0Xm5ABUMAARGwNr0COTqeNm7NSZLO32LPjWAtse/nfB2jZHR+r1kt2SagseFBSqDNThJqUCCcMMZ8ze+sVo79sjp63Vb5IdLW5GC5J/jo73TmWn5vYyMA1rLfQ2H3cfH3KLfHWvNHr/AB5smw2SEmtzz4AB8RggBRcrDTVqjGytZ6bkbItuQZL51w/0HlHLeyVL2N5F+kqVZVZLXmT4u7zJDodeb1Wz4Xbyu2+feRq+8lYvtnS8CkLEkDfU102p87mbRSLCWZCns6Hri5KJsztxso3Sr+GvW1DomJ3Ghz/M/HocePQHpOfGs6iPW4wABQAAAy81HY5K/O1mrt9HBHluctRPOPrmfbpee/ZrvRnW3FpXLmcni/1d4xyur9OUi4yNXWoEDqRBi/tz5+++tDD6Q4NXnGzUmxO4phqzN0MhaHLzmdGo0283BG1RdCxeV/Yq19nOd5XbXWdA6bgYMu3irxgAIgBgCAAJyzR8w1cKXAG3gCQj+qOTTnG3eT7pbjbjnVrEfSvknP6DceqE5c/oZ7orPQLRvT/lb1pp84As9HCZZ6uSxP0ACzLDc87yVK6G3EV4Wu7ilJpYzybutFvXPNXuvzB7Aq6VB1XCd3v4bVcsHNTfWABnUQAAAACLzrU8j18CcHKNjD6nozsY/WnGneT7lxaEPf1Uzz/X5pVb7HS+J1x4TtywvF08PTu7yXsY+bpiYlp1qeUNOptzNM9fJThbQtFeu3JxkhNNlfHIaFfnzm+TLbHwdid5XMeW261aY1MwuZnmAGNAAAsi13ItPFl+bqhtznpnvjJWKXSHG18r27tbscnPNkOr9Q0LjlNtXmmjobRePKHqlHIzvSRfzK9a2lUq6mxhz13Ieeuu1PvPDhu5VYEIW1WgTIx3bNLSL/GdWhKOzl6mPOJlayxO2apltY1qo3inwRc4MVawAAFkGvZHqYkxES8br4XVOQc82TQltr5ftXZyImblrmdUq3Za8+79ksVij+k8H3VF6Wn+eWr0IW1XhV4U9z+FGMwvVMs0K5NNe+vDfuGlXJtbcEZdfF1SySXF2t3ra6hcANpZ3KqwRcts5pBqlUe2KYwwYqVSAAM5VotD2MDsj5CM0crsnq3ZEffl8vRzHZSsnx9Wjfrli8p+pZXO5vptFbNSNtyPYEc1jeu+AJK30KbUUSMeEvdPhGvBi2g57oN2fS/Y3k/1dQrEg0wsT0xU27Q7m1p0UcACojiX2CnBPV6CIADPogAAAAFdqtmq+3zvRyMKHdus4rmEtv03VfPXTy/Z6N6d+bGuajPRl9zmde221zkr0M6rVgE1j62ss4bNSw+hYTHqntY2peVNZDIfMV72MpHO+mPOGzZ8NmTH9qV2bLCT1u0TMdDXH3EAKI5O4ItNZ7uHLzgAGNAMAQACu1C/1PYwsQplwzLlfTp0QQpa06IIwnBBBSdOBCE8UGQTogwpOCDCE4IMBOCDATggwE5b8z0WSv63lSTtcJ3TMXJ6VrLe/J9KmfqQBqpAqch2Rvk7op0fVIjJOpGYAAgQBc/XSsu884XsuM4XbmCFW+YIAYIAYIAYIAYIAYIAYIAYIAYIAeh53oclX2Skk7fA5TkNo8paFzVtQ8snPN7piPFhoeg8Gr9jkh5erdKWM0P054I9r5sFkBCq3xdneTFuy60MlNVuULDkyeYEOaOlxEAJcQ5hLiJASwiQEsIkBLyFY7gmFPOCsNjoBlbsWJ29nG4JrR5g1lyWeFirZoRRcXOR9qNyv2yICm69n0eJ6NgslbRbkiqVZsetDJgrv//EADQQAAAGAQIFAwEHBAMBAAAAAAABAgMEBQYHERASExYhCBQgMRUXIjIzNDUjJDZAJTBBJv/aAAgBAQABBQIeR5HkeePkeRuY8jyPI8jcx5HkeR5HkeR5HkeR5HkeR5HkfiHkeR54eeHnh5HkeR5Hn4yJLMVKr5vdi5YcX8Nhtw2Gw2Gw2Gw2Gw2Gw2Gw2Gw24bDb/QtZZv5Bwr3TdicNuBFw2Gw2+WwUpCCQ+w4NuGw2G3xlWVfBEazrpvz2+L0DkyO0sLODKsLGvhswmkNRgRfPYbcdhsMwyZvGKzILyxtXYqH3HcKzC+rr7YGNhsNhsLCfEq4eQ6iW1u5/Z7uGSRR6p29IKa5gXsH/AKYUhS8klz4sBLEaFbPpLbjsNuG3DbhsNhsNhsNV7Nb+Vm4uVKxHFDyFzF6HFq6724XBy0VrXMbQMaiZGq8u+Yt23FkHl7Esuu9p3bOVNx/0OPNsklcdbsiPT2rMePEiNECIEC4kXDYEXDbjsNVErYzLH4zcq7xmjg0VeVfDZntLNxsSn+iI7qnkjJ7T7FoFdRtil0pqG4ljpRGMqTSmuiryzHoEvHESDYOC+UqH8rOzc6pmZivllGc23N6S4mXTzzlJJSTMEC4ENhtx2Gw2Gw1XwdzIINTMYg27ciJ0ranVcmDIWX1huLTIGrCzTiGNRkzs2usqpWEY7LKfGyS8YpVtWsC5o62UsyxBZuYv8X3OkyZmZtvF1hXzHm1ai3L+N0UDULJ4z1dqpl7UnS3P8+ynLyBDYEQIfTjsNhsNg631GpOKY3jdkzEQ6npoIbcHa5c1LFe5Fd2GqrCncPwh1CM8t6iDeQjIue1qIVxBZqYDDeoS66DRY5GOHQcduFu504QSs/tMN83U1xsOa74aAY59nY6QLhZWcGnhX2tcieh+1k2yqy8tadWJaqx575eRtw2G24NtCkTMdmUD1JcxL2DwbTyIkL6ruwtK9i1r1LlYxkkWS1MjWmVQveUVxXWrGX5EzjlZBrzzPOiLYvjcR3n2TbWQdeKLciJBOSVrpPlOSZRmeLOYhcQIMmznUtfHqqkgXDVO8dtrljkakNLXs4aDJWyhpJl79pHGw2CfzBSErK7qJ8GclRLQyjdby+k2RAwY1VwNy6Z07zRdXFtMWoMgXtT4rWz7G61EvcSxhjGK35PfkLl5ryjRcvsV1fWMWea47TRLjXSlQzeXdlkdlojSe4yCArnjlwsZiK+velOyjosUq2Mes9JjS5E0qdWjJtOH6GDidmVXkpcNh9A450ySolEFJSoR+YSl8ywYMGLzFMV9mqidSh2khxazDqLHaioP5uo508kkxczZrM32razkwYctoscoUjHqenYsmo7DCSBAhn7hs4ZPeecOzuKysZxqwOxZye1KoFZa1tsgmXokSve9zB4bBG4NhBg3H2QiVHWFPspSndQMGDCIy3BntjAtr8iLasjtWEjBokevtXKkO18pr5n4DL3v5vGqPlsCBAgQzSIudiWnctiwx+wqYVlAhw48CJYVUC0hxIrMGHqG66/AiR/axthsNh9JHC6RNbr66PIbhGDBiM0nkvLNmnqc8grh4M1Y2nLp7ibeI45jvnUgKUfNZs9KR8bJ3oV9KW0LjBPaan6FwIcpKKdWQ8HzLEb1GQ0WSZHTVB45k1Rbnl98jHqPDIDWZahkNuBkE+ZHBX5TB8DDX6eqr/UqPUDFS1R6L44V3l9zeVePwcQyetXnQa3UqzY6sb43STVU1H7DjG8SC4f+8M3w2Ll9bTSclwLIa2xpsrrpsyoxuuyZvKc3yLCcRhYfSkC4HxdcJtKCV0zBg+DP6Wbp93nuuVf7nCdGscVR4haUlRcPXWM1uRV7Z/gbTsgy3J9o2XhtxfbJ5mvkONt+5Me6QDlisTLkWBAuD77MVnJvULZKmxtdNSpi8Piap5JIcxyIUY8YhORcwg6k0S5eumqVcvF/UhbNTokuPOjbg+JpIz/8B8DEc/6WQfi1Wu6iHe1jhoYZaQa+BHyE04TjYt2tlfGs/WEt7otirL/kCBAhl+OSctoKDQfAKQ4dXV1bTZfgtm+nOrG+pNMvD8SHORe6P6fXgwzGyxGi3G/xMHwMRv0rWr9xmXNuHD6r/Cc70xWSC4TWutG+Nb+4F2raK2rnbqf5EgQIRU7NoVyuSVGlluUlxWvT0+tudDZVnbZUuQ22plXMhzyrhuM+1nawDJY3qTwVwl+onA+niuvUHMctMHwMRD3TITOS6SzQXJ0RuDE13qOoWaHGHes0JSCbkfCs/dC6T/Yxf21N/IlwLyEFyokFsbyTW2xyG36hovNT+neN+KSaeRCeVKfxPmNxuPUSe+oIwd5osQ9PzJuambgwYMQ+CSJcgy3JnCqqPkVfjkumkr+qUKcVFZ6DS1pQmSvqPb8VfSpLyLsv+Ii/tqT+RLhGTzuhaSUkH/Se10jdbBPT2xy481/WcDDyHVA/qPUKe+oQwv8AhPTbH6mcHxMQPPBsi+EpvlkQYZR0+CE+Z1VfCQvps06doYyDxURf21H/ACJcIKeF1cR4UxtXOh5HUb1WR7rTnQ1PSwRtBNodVyt6f5eVplgcBmPUErfUQYb/AAXpjjkq6BnwMxVqJTRhn9PgpaUEwya3BYTR9fjdudGogI5IYybxBgE6USmRI9xHkMyGtyIQjR0VKJKcuyspet0I94w1DZ58J0Ua/wDgRPcSzD0ryw4mp7audDn5dxr8rfUYYd/A+mFkumD4TVmlisb6UJf5G/0+Ekk9QT5nIRmaj+OVK5aJj9EZY3ywE/khWkBWL1DKJUbVC4Rh2GaC5vJu7VxpD4kaVYRIyBDCmi/uBlqfdUGniF4tiTl+6YXa9dqhxHAWiUqHXi3zChqG5OqEeC7qlMkZZmP2PbDE6ye1j/pna5aLcHwfb6yEFypEd1t9rgtBLS77ltqQlSHfllv8ETqGo32tXjJ7SsmwCta/aofRXRMGiWlbc6oRJd6itx3IIzelMm6xvJaS4wOhHfOJjvrFRkGZ464iLqRblZv6jREIVl+TTXKvJ8jhysjySbZt9doddkddkddkddoaIyomLY73ljQgWcG0bDBczwyCcmso9P5KpmE/CyTtL+WVFvRdJEmHms+VQT+7rkd3XI7tuB3dcju65Hd10O7rkd3XI7uuR3dcju65Hd1yO7rkd3XI7uuR3dcju66Hd10O7rod3XQ7tuh3bcjBLGZkeT09NCo2BALd4a0WP2bptpO51dOPha/uflbQ12Fd9mWENvU9C0Wn+tpV/nO43FcRcpD1MWxsUeiznU0x45nnuL4FXZB6qevNoPU5WSH6i3rL2B8EGRL3Ixrz/P8A+tpX/nG/DWS+nUGH476jMrq06raix9RJ+mWtWMYliB+ozASEz1MYu0WS+pLKpbNZBvdVrSl08atn7vSa7oqLSnKIOFaTke/xLdA1rNR3n+tpX/m+4Mx6h5W1T8boz9jpVMeg2zOSYPYS8qt4OZYlevE16fsRfck4ruQ1D9Qd2Vz9++pw+/fU4ffvqcLvUfL8ikdzXI7muR3Pcjue5Hc9yO57kdz3I7nuR3Ncjua5Hc9yO57kdzXI7muRXWWUWzv2jlm6bDLFiPNyyU+djlqXHJ2XtBiXl0gFLzA48LKc4xmxjah64S0SNQtcYzGaWOpluns7Pd+y8+6TdJlzsRWH542dtGyWiU9PlPt6fzWrNp5hUR7I2Y6afKcUz6m08j636jxWPv31OHtnx7Z8e2fHt3x7d8e2fHtnx7Z8e3fHt3x7Z8e2fHtXx7WQPayB7WQPayB7WQPayBCftK4Hl2Tcp5hkpsuZHdPWPduSGTeX5E2UK6uoDszKMgnonZPb2EGuy+wgw3smfeK6yKbatp1GkExN1HvZq6/MJsCmkagTJqZmVzrZ20sGp8arn3lJIVluaLN7J8tki2yvL72p9s+Pbvj/xAA3EQABAwIDBAgEBgIDAAAAAAABAAIDBBEFEiEQMUFRExQgIjJSYZEGcbHwJDM0QoHBIzBAodH/2gAIAQMBAT8B2S1VPAbPcop4pxdhv/xYvh+uxGR0zjkbfeeP37JuCVeEVHfILSOHZsrKyocHnrmdJezeaqMAmiiMjXXA/jsUWHVNe60Y05o/C1mfm6/JVeH1NF493PslwaNVhtZ+Fa9zSWemu5YjVsrKjMwadgDsUOVtDGBusFW9LNE5jeKLcpsjBJ0Oe2nP7+Sa0vdlCgjjoYGxNRqtNykp2VtO+N3FEEGx2uIaLlT1ElQ+7isC+IZMMBhf3mH/AKKwPCq6sZ1lr7Nvu5qXCZomFxcNO3hmKNhZ0Uu7gq3EW0gaGC9xsZPAMIyO3/dlRW65HfmPqqrpS49Hv9U4zdHpbMoHSNF3b1OQZnEcztqRK+nc1guSnQyxmz2kfNUdHU104igbcrDaM0NAyHl9eKxmRzYA0cUFh9E6uqOjG7j8lQ0NJAO60fUqtwujq26tseYVTTvpZjE/hshGikjsO6gLmyl0bZXy6hUlYyrhD+PFHoyzTesTrG0sNge8d3/vYpXtjqmOduBWLVGBGPpKo5rcBf8ApM+KqWjkAo6cNZx5n7/lM+NI5pQzojr6qsxLrcPR5LbPhy0cUkn8ISDNYHVGYB1i7VfELB0kcvMbIdlhdSG79kYnDgI95UspjpySeCtNMM7tSiC3f2MZfZrW7ITllafVDZgc+XNH/KZA10l2t1ToWxyXLdVjc4kexg4D67Idk177IQMyo+/iDfT+1ic9u57qmcHM05n6qVt2djGWm7CrKNt5AENkT5IZA9m8LD691U7UWssQrXUp7ovdSPdI8udvK4KLZNv2QeJUr8lQX+v9BSu6Z5LlYZr7Htyuttxn8tqG9U/6hvzQUbczkGgLDZMlT81ib81RbkFYFPblKiOuyY7IfGowQTfY45RdRP6Rt1UDjtxrwNUYuVS61LPmNkA47KQ2qWqtN6l2yXddR+JHcpPFsiHe2WUw/wAeigZlFyp3i2XbjLu80KHc75f2FRMe+qblHFa3TGlrQEDdQaTNVUfxDkTYhPHdUXiTtyk8WyGEClMp4m3YlkyD1W/bizXSVLWt1KgwnEuikvC7hwPNYTRYjTyOLoXa+hVDh730bal4IdnAy+mmqxA1RxHI2IkHiuq1I/YVBTTdMMzDZNpqVzi8g39bqqoYpGZom6p0FUP2H2QpKoG+Q+y6vVH9h9k6kqs3gPsntcx1nBQvcYsvDsPvnN+xiYmZUMlj4cfVYHNPU4VFJK67iNfdWK15rXmrFaqxWvNa81rzVjzVU5zKdzgeCkkfI7M43Ki/L7DwS4nsNcGrDf0TP9dZ+lf8jsh8G15N1ZHZYLRNqZ2CzXGy63U+c+663U+c+663Vec+663Vec+663Vec+663Vec+663Vec+663U+c+663Vec+663Vec+6NTUOFi8+6yUWXf9+yk6NrrMOiuVcq6vssF/8QANREAAQMCAwQIBQQDAQAAAAAAAQACAwQREBIhBRMxkRQgIjIzQVFSFSMwQmE0cYHBodHw8f/aAAgBAgEBPwHCOnmlF2hSRSRGzxbHX6Gv0viVPSRtjGp/Cm2hBXRdkEEfQnq44Dl4lMr43PDLdSeojgF3IbVu7uqKeOXh1QCTYKenBflvZw9dFBEYWZSerZWVlNffOPmoAxjgSr3Czs4ef/iOgupCah5eUKfW91vHQyNc1DXGxOiigZC2wC2lsttaQ9ujlXvgHy8uvriFbqT0T5nZoxqoKZz3EnSysnwTdKD7aKbwXKHdgDMhus+vBSCMmzUzuDGnyCdpcdEJGOF2lTzxU8eeQ2CkqY6ickHiri9sKiYU8WZVVRK/zVNVzxHjoopGyszNwpxpdSNEw14oNJdZTmzMqNlNCYnWR3VtOKp4i93UlBdGQFQx1+8yw6FHY0s7b1EpJ/wvgwp4y4FCIibPfDaYMj2tWR2W5GiEZy3totlnsuZ6YQeeGUXupXZnopsGYXd3RqVFFvZwAFLRmJ1mcEWubx6my2audhKLxnAKuivZyzPIyq77ZStnxFjXOPnhB5oqckHCBoL1L2aU/lU0fmFOCH8lOzOzqbLcLOCupHWYUUE6kfIyxVZROpQDe6oaQ1VyhSvY2wwg44VHEYU/fUwzRhv4/tM7A0V9MJG5HkY7L77l5KbwXfthCLyBaqvg38Nls+DcRELVSiz1Ae1hUemEHfTiCAinuysJUL941VTeBx2WO25POin/AE7v2wphpfCXWMqHwxhO3S6g7+FR4mEAvJjU3MagjLBcqpf9uOyho4qX7f3/AKKqP07lwUOXdi2D9WlReGFxUgu1U/fwn8RFU0hNRk/GPFTS5B+Vxx2aQyAkqaupN4y0g5/hPqqGVvit5hVlXD8UdR6OYWl2fy8+yqAUEWzxaRrbfaul03vHNT1kAiOV4upK2qtlDh/FlS7RkY/LK4WXSaQ/eOabLRNNw8c10ul945qWpgL+8FmDhcKlYAS/qSX3hv1Nnhj4XMcq+CCKse1jdFlZ6LKz0WRnosjPRZWeiys9FlZ6LKz0WVnosrPRRxxmQCyaxrW2Cg0j6klPMXE26lPMIr3Va4PqXEfTi8UYU4+XiNG3WZ11UMAOb8lXTaeJotZbmL2o0tOeLByXRKb2DkuiU3sHJdEpvYOS6JTewcl0Sm9g5LolN7ByXRKb2DkuiU3sHJdEpvYOS6JTewckKWnH2DkrbT3h+W238f7/AO0VIJ9184AFWCsFluFYpsdx2wtzF6L/xABTEAABAgMDBggJBwoEBAcAAAABAgMABBEFEiEQEyIxQVEGIDIzYXGRoRQjNEKBkqKx4RUkMFJywdE1QENTYnOCk7LCY3WU8BYlJtJQVGR0hKPi/9oACAEBAAY/AtffGuNca41xr741xrjXGuNeTXGvJrMa418TXl1xrjWY1xrjXk1xrjXGvvjXGuNca41xrjX3xr7+LeeXTd0xoSxP8UXXEFNdtf8AwlbBcNGmqJGVC1bvzC8tVB0mPFvJV9k/RVnZ9ln944BFZOfZd/duA/SKmJpKil9V1CG/s1qdwwhqWs6znrzrhS2A0lSDQVxppDrhv5UupLmzXQwlLZ0aYV+n8JCQt5w3Zdo7Tv6oVNWraanVdPJHUIzrb60CutJpWJOyp2fcfl5p0N5p43iK7Rx3J6ffDbTYqtR2QqXstRlJXYsc6v8A7YvLaLitqlY++L7UupJHnIFDCZS02zNs71q8Ykde2E2hZz19tWHSDuP0U7ZjiVleaDzdQKBGAw26/fAXNPJbCzdQVmgJ3Vhq0HpIFTJOaUvZUUMUH07ksV6EowlIHSdIwmqa/VEP35wstsEJJQmpJPuhLLCFvzNbyXnxqoNmV5UhLZ565oNXqXvTCStF1V3EZTZ7LnzWSVdug4Lc2n0RpRoNdsVmHvQICmpeiDyVU5UIYLlGJo3HEbL3mq+76GrrgT6YRaUskFxIKQumz6sFmbYwVymloqIEvJMhDaMAlP5hPhQ5ZQR1XB+ESlnuvFvOqzaljZBlpPOG84S4p04kwi1kS/j20FIWkYkH3wFrbKa7DkSKazjBUR52GSbtQa2mSUfa1DvhKE1U88qiekmEuW446/MK5V126lME2Tar6D9V5QI90B+3JxU4oam+Sj4wtpqXS34IgusXRS7QffF5k7b7Z74amk6nGkr7RXjmXl1XaYFQipi44NBWvo6Yw27YceZcIqs6oLbx007d4gpBxGv6YWzZrF6al0UWkfpG9faIlJ98Xsw6kqFOUmEOy66tLTVKyqtYbQm1phgJWCUsLpex25UwEDUrWMjiB57zafaiQlliqWzfI6hWHH3bQtQtsuXXVyUuq42ek0xgzcvavhbB5tS27q0naFaoztp8IGpGWI0brd5xR274mzZfCDwxJlHNFYAUNHqEeDrx+rFnrP8A5RHu4ynNwitYUxWtMfRkzABUk6gBqh1TDt2YUsIaI9/ZF97hBNjQN0tUre2a9kLcnOEMyEOVUsttpJKrtE69mAhizpq0M7LJSpc1VkYJp+NONWNXGU3eKbwpeGyEOTFhuSzDKRmrRKr6FnXVdOQa74azYQWV0La28UkdEYIHZlqlYFN4grfGOpOR5xP6J1tZ9aJZRPLbcSOu7HyfabWdZvXs2ThWM7QVKQKgbAKCHrPmkaL93OKTysNVDshpIlUKLLOaQ4U6V3dWJaXblG2l+FVlm0t0IbAx+6JKUUMUSqAeu6OMrpwyFBR+j1jbkFxVCTrrEvZCV1zLIUuhwvEfDK9bzzfjJ5egf8NPx4i7QtGYDTTYqpSv964MvwalFy4r5Q6m8r0DZGdtO0JqYO910xfsW25hn/DKryT/AAnCG7L4QtpYeXgiZSfFrO79k8WkZspBB1gwZ3gisJbJvPWYvml/Z+oerCPC5VK21JVdel3hRbKx5qhluwTsGAyPWbMjQfbKFQgzI8bJTAvDfTX2iETbCqocSFJPRCpOVVPh5k0czEmVgeiD4FOLdLfO5xBCgfTAfcduKedS2hQTeu1OKqdAhP8AzByclmlXg843d8UNWGysUHGGaxunERQoMFTiCkajXJfWaJiZtGfdZlpZx7QUV31XNmEGy1zOdBRfQulMIas6TRedecCEDpMS9myg8WyylCfRxF2alXzeTVdSnevafujxyaD626PFkLG4xVTRB+zF04g74XwctN289LJBllnW4309I41FCE8I+D8vnXhRM1L3qZ9r/uGzsgLAOIqKikV3QVcT5dshqsy0jxzQ/SJ3jpEfJluVTKhy4w+RyFa7pgTc5KhSruDjaqVEFLLQbbQCq4nlKhKWZYqFaS8unzR0/eY8GSQp5elMO/WO7q+gqoQ29ns3cTdOjWsATEzUJ2uGDNTUyrNo/Vt1gixrOeec2F3REOWpabl5xe7UkbhC7cmU0blGzcJHnnD3VgKHoyvz7mpllS+wVhT8yrEkuOneTiYTZ85JtvZ4X376OUowXLAtYoSf0L+NPTANo26sHzktNj3mFWrZNoOvoaxdadGNIs6121UQZhKHPsqwPv4lYvUwiqTkxggjbAaGzE8W0JmfaQy3MoCppeoAp1L68YQqw59bDRSKN3jSlImXLQmlqKmFZx9QqUJpjSG3OD9HUOpr4V5zn+930FBrigZA6SqE2cxNpSS3eWQnVF6YJdO9ZrGYmpVC0fVUmMLHl/5QhJTZrGKSOaEZtlhCBtCEU4loqTtliO3CMy2k3L2JA2wEz/CNbFxseKYAKh06iYVMM2y3Oy5ALa7l1Y648LtG3RKStNFLTV5xZjwJNpPOZ5s+JmmLqqdgh9tSFDMuG4ojcYZmP1jSVdo4hbOyKp0TvTGmi+N4jBfbFb47YLitauJXUN8S/A9U2ES7Kg5aK1KoFE8hqNEYbKQWSUrSjnU6/QYtvgyG/ESc6FyyfqoWL1I8U52xW5UdHH1w/aS9anKJ6BxGvtcW0ZZsVUZRd0dIFYVLoZS4BNfOmykYpVqP+90TFmvIutzVM/mtEqpqNRDElLICUSzAaaG5A2Q/JTsulbcy2EPdIBqIakJYENMoutJrqEN2etPjH36S6Kak6oblf1SAjsHE/hyvTNkSTb02EeIbcXdSo9JhpNoBsv3PHZsaN7bTiX6YmH7Sf5LLRUfQIs+0pxPzu2J1yamj6NEegKgSbM6/dOAbS6aQ1JFPzh0ZybVvWfwi3kfuD7GQJG2Lw1Kx4zzw81pR7o/iPEaP+IOLdI174nUTj85Ly5bWuVMrrVXFPoB90M2gFadLrw3L2wli0pyYl1nFCmmVH3YQZWzX5l8pxcddYUO8w7PXqOclkb1GBaEi9MvSUmUOlc1rFNQ9avZxVHcKZSOKOqJexUq0p+daY9BVU+6LJzI0Gn1oFPsj8IROPtVYkU51VRgVeb34+iFWha02Gm0+knoA2mLSnZ9D0n8p5rwATjRRnLqaU68inTtNBFRrTjxphI/Uq90J6zxEH9scYMlQbmGsZd6mr9k9Bhdnqs11Va56T+sAK3h+MJmm20OIVjm3UiqYXMuhDLSBW6hOv0RKWc1ZzjYeaC5aWJ5KD56t0Is6XoXDpTD311/hx7x7I09Z18UdUcGbPVijPuuqT9lMZ5CfJ5lKveIRNTDV16dVnVbwnze73wzNWnKBwyrl9gk6lR4FajBolwONqRoqSR0wIEUgtnZxVsq1KSQYMuiWKs2qhIVGMs6P4Y5DnqRoMOq/ghnOpCEZwaOsniKmZhwJbbTeWo7BCmOCMgymXSaB+bSSpzpArgIDUoxJuqPmtySj/dEtbfClFmyTLdS1WTq/jhv0fTCJJxAQpnRvsC7DsoEB1x5F2/MaX+xD1ucDlWfNXkDPtOynjjT6tDiOiMzPSkoyobHZFQ/ugN8L7NZcllYKekkELb6aE4iETko6FtuJvIWnaOLePGEWEDqRJzB7cIdsqfTVp0UXTrjRASAKADZAWsYDkj78iwdkBY2jIl4dR40z+9yBVfPAyNfb4j1gS854OJgpS67StEVqYD0xKOz6xtnHKp9UYRm7Os2Xl0gclhkJ90CsK6cYQNxrFIuzkq26D5rrYV74K37BRLr/AFkl4o92EN2AiecmG2FKzK3eUEVwB+jESnCFEymkqwtgs00lFW0dkCAzTRGvKpIPKAjMn0ZFIGvWONM/vcg+3CV7xDXXxL2+C0etMGgJJ3RcShfqxZ8/IzjrJdl1pJacKeSRu64fmJ+0H3gxJmmddKqVIi4uvZkSjp4gsOasFUy2ZZLucaeorGuw9UfOZC0mT0sJV7lQpyXlbUeCeVcktXaYluDVk8Hnm0vXyp+YdFRdSTqHFKd0FEujQWRU+msY60J74QdxxMVyEiKgwHMi0jfxZn95kvbnB98N/YEN8QJhDg2KghOBjQFIs6c+pNKT2p+EWrN0/VJ/qMXSKk8kQE11QVHYKcQdEg1/dk4SsFWkZFKgOi9DLn6uVeV7NPv4qsiug1MUMf8AEzczN54k1aMyS2cKaomJtVvT06HzUMvqF1vHZkCECpMBFa7zF9R1QV7+K+5vcyXt8wP6TDf2BCPTlA9OS6cnQv3wp79TNtq94++J6Ypy5sDsT8YL2wYIyLCdh4hG6Ra+/Jwh/wAq/vTEw/8AqrPV3qTxVnpyFQ2niFAG2LyuWe6KmLiOSO/irc3JJi8fOUTkZH1phR7ob+wIT1HKV5JaQW5RyYUc2N9BWAveIKYtAlOKUIV2LEOOAYrnXKdiRAQnZClbhFu2Kt2vgs9RvHzaU947+IsbpNn3ZOEP+Vf3pi1Zr6kq2ntV8OKpe9eQZaqOoR4U6nE8kZM0g4bemKnizDn+EYbH7OSTTvvn3QjPHGkeFIk3nG0DSU23WkJfYcCkKFUqEYmAEqBO2kXjsixrKac0ZXRc+24Dh2XYT1ZLWaHnSaldkSx3zLyvayOOL1BOMKnJhzQtR1aF13qVVPfSAroyvDdKs/05OEX+Vf3pi2ZjbeYT/WeIaQhPRWD1QOrKhSxhXJmmz1njv9QHfCfs5JE9CvugdUSzMrPMtKl0/Ob+zfDl6bMoh6YW425gClNenf8AfD8yxaHhrjqkoZUumCidt3YKRPSFppRnHGwtKkJoMD8Yoo1HXCOE/wAmFE6h8PZ1t4iqukRdaew6RHmGJqzzQLmWFtNY+cUmJex7QR45srvXDhiomKNMAdZhUvPtZxChpBG6BM2NwdkkKQdfg4vJMVemEtA/XXSEl2azy3MGmpYX1K7NXphKZ3g7OpCzRObW24exKoftiQsecQ2W0IAcYNdEUjCzZj+SYt/OybiL9m3EBSaFRvjUNsWq7TEzqB2I4mb3wE7hGMJcaVVJGBy3VCLjQvHYYLatnHd60++A64aAJjykdkSzUpNJWptWkKHDCPKRHCCUtHg27Nu2iKSMwHU+KxVjieqFv2rfQ2WSAVuVxqIkU2SjPZpK86EnUawoeBLQSoalAb4XN2g4lqXcZuOqmFk4Vro02w8qUtZ1a5hy+87MKWtSj6Y/K3sGPyx7BhqYamFTBaJ0G8DiOnXC25/g2vwTHNuMnT6MCYrL2DaDit11Cf7or8nyTDZ5AdmCVI9UQP8AqOzwFgBajJrI9OMNyk9OWe+lBvJek74PUUqGHbHLjlxy45cYLiblbdd8HddnitKVY1TdG6Pyqj1T+EF6RmA4kGhIyJHTknLQUeZlXF9iYsuaWalckgns4p6eO/1D3wGl6lIGqG5ez0pKVN1OcFY5tn1PjHNs+p8Y5tn1PjHNM+p8Y5tn1PjHNs+p8Y5tn1PjHNs+p8Y5tn1PjHNs+p8Y5tn1PjHNM+p8Y5tn1D+Mc0z6nxjmmfU+Mc2z6nxjm2fU+Mc2z/L+Mc2z/L+Mc2z/AC/jHNs/y/jHNs+p8Yl7ItJKQy5evZoUOqFS8kV3VKvHOKrkvbhktJV6heQllP8AEofdWLHV/wCj9xI4v8PHdk2qXnE0TXfCWn5VQIGwVEMX0keI2jp/N5TqX/ScqlV25LOsZKvKJlTqv4B/+oso7m1j/wCxXE+UeElpBkHmmki8tw/siCqxeCfihglUy/iewQlnhFYC5ZJ/TS7l+nohFqWROtzDDnIdbOHFBO+KgxKf+1+/83lOpf8ATl8JsyccYfXNNpQ40qhGNfugMW3JM2igeeTm19oH3RKTktZ7ssmXYKS24sKxJx1eiJbg9a0rN51hS6rbbBTionf0xg1PH/43xj5lYE88dl4pQPvhYseQYs9AHL5xffh3RO2xwl4UZpqRls5NT03eczaL1AAkdJi0HXOFkjL2dZ7wbVaawotulXJujXjSJ63Zy0pO5JT3g6kZ2hd8WFhSK8qoUMNcWdac+kZmZtZbTq68kKURe9FIrxdBZT1GJW8sn5tt6/zeU6l/05bOkvrzK19iaffxjTaoRMv2bw0asWfMvSVcm0jwd7HSQ5UEDDo2RadjWU7weM074I7MrtJBRJPzCAoPON9vpxi17Jb4XSb05KW74cVu+ITMtZgN+KHRSgEWLLg87aLx9pUWbMP8tcgyVHpuDI7ZnAxbTUswu54Spu8pw7aV1CPy+P8ATp/CPy+P9On8I/L4/wBOn8ITM2taQcUhN1JzYGEeVeyI8q9kR5V7Ajyr2BHlXsCPKvYEeVewI8q9kR5V7Ajyr2BHlXsCPKvYEeVewI8q9gQpmQVfUhF9WCRQenrigZeOFcGNm+CA07herVqnJFTBlWwQtLd8hxITRO/HrjMll69fKQAxrI1iKuS7w0ArmNh1Q7m0K8QKu3khN3Cu2DNZh0ICkJKlNUxVyYE6wXGX2q4qY1b4DrdsM3SAbys0BiAR7xBfdtdnRbvrb8VeSNlR6YTMcKZ5lzwXQRdcb5ZNCjDWrR7oKRJpNK6nUcoa0fa6IU8ZNFELCD45HLoTc+1onCGZ1LIuTDC3mqrSKtprVXcY8bLITSl8reQAlRpRJxwOkMOmEJtVks50EtXqaQGEFtxzDqhqypXgo1NPM3bynHkprUqGGjrNRr3Q+y7wNs/OZ7ONIRNowRVtIRijUSaHbD9zgxKJQmzybzDyCtVSAlzBOqqVRZj9uOtCzL5VLS6TptFeOl1wiVl7cSlDaAlCcwnAD0R+Xx/p0/hFbnfHN98c33xzffHN98c33xzffHN98c33xzffHN98c33xzXfHNd8c13xzffHN98c33xzffDvgZuF5rNrUDjSoOHZASM2KNXMBr74zNxgYkkhvGt277oXab8pLuKcZzS0LTolNa798BLyGXOVfvo5YNcDj0nVGixL12Kuau/caQ++022pcxW8txNaV3Y9MKbmGmiFFs6tqCSDr6YelJiWSC6ALyMABUqOHSTCJQ2YFFIopxMwUlYoKatVKReVYbYcLIbcdzxqq6Bd6qUHXAbRZqGfnGfNHCdPSrr6VQ5SxCl0qLjdJnRDpreWfwhLi5PHPNuqvTJVpJCxhuGnCLF+Sr7V1SXqzRooG9qGpB0tY10hbc9YDbqHqKmW1TBo46KUV1aIwhly2rNE0JcKDKXHKhKTTRx2CneY8GY4Oy8vpAh1sC9BmrInHpZwoulbLlDTdCiu3JnSUSdMa/wDYh3P2q8rPNZtypTyN3RrOrfEtYdrWo69LSgoy2qmGzHfHN98c33x//8QAKRABAAIBAwMDBQEBAQEAAAAAAQARITFBURBhcYGRoSCxwdHw8eEwQP/aAAgBAQABPyG3nF8vvL5feXy+8vl95bzi3n7y+X3l8vvO794d/wB4PN95fN7wvu953H3l6/knm955/eebK5vvPJ95fN7yub7zzZnv949z3l/9UX/SM3g/eLzfePf95fL7y3n7y+WLefvL5feW8/eXy+8vl95fL7y+WL5fpVzW0b+EDQDZaQVcVWsQraVAzpAh0AhBGUpDp46S3RWPVsMUREaRJVf+Nd/o2hUBh6K0v4hnBLpjcW4r4xAgQgOhVQknslSpVSpUYRhDVoiNdotityJGGHujEiR8Q/s786wn+bujLzX1W5+kTQtAtWt+PyqRaEJl2G2mt1+GJxwJcVFtOsaU5HqpzA26AQIQLgVAh2TwlSjqWYvZ28rsNfbeWM5jR2zwTb+hi8Kmf7BJGEclNTRTAVpMtI5dDSccSdvyulTwOrXldB4zETY9Qt9dUf15FE9RmQ00Uu0/L7yx+dRQtQ2TpXW+3037tojmRq2vFJzewlE2C+9R7ypRyEoUdUg4FGxAgQtCAhASm0omiHLrilqbhmFnrZ7QEqaJp5lTC4Rl0Xih51hWLnm762u+hvPCJMto7244TKcBvumtIkGYu5ToG1Odh6wTLfa4PRZ3RLQuN/BFy4GyXNad4lC2mimHvZfyQ8y3rfW5XJd4VjQ8si12aIoobFN+IYzKYo9oN+gIIG8DoBDoKqGEDMqaI0ssVaul+UK0CMXm1V82GeZqPj1frNBA7mbqIt12muhiN8R3yJzLa2KlwSp6FF2jLJV54flEdeos1Vl92HjiyyeCsvljlboQ+peU42QEfJd+6NesZyuTGwshjSDgph9ormEeh+UOj11IVAOaLEbF73HFjC+e0FAQmgaMSAut94P1C4CItFB0hBBUDOkEIFsSpS9Tx6bmXa3tO6ZOynEp3M6qX7hLOga4tm74YKu3wqE5fMS+gXt0xX9Y9DWJjSNhrnij+JYQfyL7gihxEHYNcO7ozGxfoqDLTYYCOLF45ZQ00Jnahza6ghqam8M42rZrQ6j2p+JfW+lj77LEsrfrEEsdO0eIVuKWi+T9TdO0odx/NY0BmUcGrua7wwcgXNhrQPaBExiIOshhWILCCHQCaLQyXanRgdujwhFIxNxGrc1JhcSkLlC7SXgm8s5VAW12jCS5Ya4PQnEM235IVmhhbK3YqoBGgO1D8zXFS8kz7ptSnf5qJSumEugY4MSlXtnd3uaJtAgXLprVqW+vePALBNAu43lzK8R/6j5ldbdKRevop+LPYXr0xmPDBXrGFde5APt8ujrKAQ45ksD0UvoQQQluuofBytjVmKCyajs2Lu3GrzlZ+ND0haua+p1qnoZ2BFeU747kqLPRgpQzsg13RCcqFh8x+/X23Vdd+c+Qg54N8I2DxslJ0pdJUcCUAxGL3nayyr9NfSJVMUblejJ6wpJ5OqLg1K/X4+xgrVxaVsgK9I0W0ReomRrxHrlAjsPIx3thkFV9SaAWbjEs0bVFgQsLvv4uCORlsHOasQk5pUYAd6DVNYBUzIl3VluZ3/ZGlEr0d0QVcEE2iVGsOPPzV09ZfjWGG52lE+vToYIwdnINZ4x4DNclx4SB26hwhMwxN9D+LNDZ10ppyhJpUXvU2nYkdldPMoKvzBBNDiCZHJ6l5ILXy/O4zOOMxhjBeNjJqTd7Cahb5YSirQeVQHoWgbNU7Nib/WkC7L+LOrC7PzFz6QJ8TT2hX9BKvOxQ3vqy+j6o42hbBMOIMc/jQO01bZJozCE1nfe78Ja2cT/qFhbzHzQv40OKixcsCnYD8xHWP4x8QpoVSu9Ew1xU1nIHc/e9IOYl6dBSjLbDWiDro6JO80RKQPi9p/XHoRJhmBlOJk3emVUm2kpr98RI+BoVmzFQBqt4DsPFxhS7SPla/wCIrjX1JeVsn2mF8TU8IJlVEebl+yaQGDN1E9ppU8fogI7qHt3JcVWxBfoQhoQQVFubsTuPyjWlVxitC43sulGqsYF76QPEjzd4JhNKaIiAQ7s+o0aaG8ZCq960Kahrzowg3biniZdP6VfzKlMeyMcjjxtNY07EyXvOek0VHjCInj2ItByWylynHRpDuJSx/ZS7OWr/AMhABh2K7Qf1axg7HepgtAHi3tL/ALJgFqm7uWmE+pAtpUA5bb9nQ+i9c1nKG5hMo5ROG9B9ovktquXnzjtGdijyXcF9/Sb/AFJ2k7vLzAnKqZrC9qc4lykVT2v7XeZzlorsqlcqazFlAx4D8QE4Ri8Bvl6PXXJGKdtgq/aAQItqOcmavSDEE0zIr8m0d+mfsjXrpM+jv/cF6rPxMokFzBkDcpsnjQRJ7hv9Okmp6vEwph0G+/1IkyB7pQPP0GSv9Ohzhr0NHkwTQwkdRlOC3hwGLcFe7Ld9/WYQgtU3yFV7yylQAPIB4JRSlb1FY8FvpDBUGYt48F3AbQemrnBBxJ/l1d9QPEBMTh6DpF6k+H5ynDhVTXO4WtNCYOePVrdAZTgl7jBEzAdBOheZjmH8IASw1Cn5+rUUao1la/c+hfxGZphCCBF7Et1N+Te95WnyG8h3NBZuBuCIWyCdx+8uKbCPAGrM70qSn19L4qGnTiNrXw0DYg2glczTKLuunsdGqw6eVGi+jKO8orCAMnNMDHr21mg2fclwriTgB9FxYf0bS1ow7a3tDbjY26xBo7esBqVoq1uUTtCZaJNxbiH0Fw0a7lQBuxC9WH7M/ZnOXlwdEO1PvCDJi3gLtNOs0Q1irUL0FlWN7FR02oPJnxNIkCH2IdXIlECgGoRby8Q01uBjeaM0XtKHwtht0sE1VgmbWmQDQIT0r5jfUp96x6qoipYdop4hGXqWMWJ0aOoUMmkawvaLt0Lnou7ESE+xkKkCDXQH8R0bZmEKpYbvL4a7+ISvQPcOTpnq6Q6h/LhpEL1e6Ze0L000dNalMeTSr0mLUK9ZWYpoeynuubOLj16I3IS2Yzxw7/5HrmrF9pexeZUT2QD6BhacvJk5r8EoZpQsWpYxdY2lEjhDjqzV0PPSvmhGDdgBPBGVDqXpCWKl9zV1DoSzhv3Mh9W+mmGj1CEr6PnPu9FKNY/ZnaDfiG4VOyKZ5DacT0uJVcFULZklGt0qXI9OirdOPvo5GNXszDaumeZmqTLqVFQOtnwQxll8RiqcKpXNUU+qAjfgKesqAhvBPJEJgS+VcLfxzOUTUehaTVBAKAHGpn99o4MgnlP/ACFNv5LeAqjtFWvrD00YIezhw8QANzPnpol3T6HEV+R93oTZs/j9J/a4gvzP2igzaN4YGxN0R+ziLEoYp+JQYeicMowc21tfOYaA2WDKK7kvi1AtnAlT8zR1HKuiPfo8vXQAP3hq0eibRTKYR4HCRnGHxhX5gUFm8YnsME7n7RFjcracNd69InVLu4wEHOmvciOkDMp41XLcdVlEU7/SocD7J+Z/a4nxvsmOITE2jFd5cirm8tcd7tFOY9mX8Kfkt4P5ijuUfzM0mUn5+8dJjSasdzg/P89Vm/w3oosY9o/QD4jBczX7fjrRH7FgJWu9Xs4ijZgj2GTH5dDqLj+MIghnoCP+ehPzP4XE/icR4hM1XYjPL8TmfwSnuSZZpdE5mqxg4bcebdGkQJ3sX4m74VfT+/jMG/QAw0XyetyE1F9dlegs3oRNEBiovtLKO5fV1WLGNKWsmhGqzLFwmo+EtX0nbL7dfmeJXTtz3dwV3aPY2hrwV4cPnwWyzmobElS6DlmCMXZesQJQMsumskf9IfNPH6TaWcwB5ov2IXeR8T7dHCym3G8WIP8Ayze0esofeBbmekEzZPy6bKbCe49oXF2ixsdKhOVsnrmfITHw+uQjE5xe1zEvszadO0tD9Wo9fcCYH2dGoNr7oQDgy+QawN6O163LWXBYLmMTU0zbTszyNUBQnvpCgKTXqFX/AAmWOohhGph1Nt3ZT7S5wGwsw/6kfhbZaD05lj1434hnwzxC26XzPmM4ZxvMBkZrPksYYMmisL6y4yWyTjZ5IO8PeXUjy5IIK2Ztpxcu+E/riewS9NaTOA6S70L/AFPyxhYixX3UqCJwRQHZWZTWpLc6pr4kFm6G6ETG15+v+pxgQTFeJkr536mFSQCnomAdHhjZYEhVjUGqxnFQOuKgxgXhiUQAUsuWNbQAOrVCtwwrXtGugN274g2oMCys7C8EdsH98QLQf32iYkctVFgKXbEvAzR6l6jGmuZdiNGL3YQ/NIhep6y0/L4wFcn2Y9peT1V8mCnMZV/uHAnaTHh7QQJ21UI6uGOpJUm/24fWLierCEoBlx5GWf2/ekI1t0xUs/Afox1oPc+MzizG6XP3167rkn668f72P97HD7uP9fH+hj/Sx/pY/wB7H+9j/ex/oeh5/oY/1MY6+bH+3j/TR/po/wBdG18iP+Y4JFNujtKVdyKU6kF0HBxFlpGaQyfWnIwzLsP5uPoqHF7fz9GOurMsscIknMX5Qg3a4Yox1x0xMf8Aw0KbdHJAYlOZUAoPIAfPxif8b+m6S63mgnPZGHL50OZTRRoG5rF7x+101PmwfaC7e7Jduz2cyvoVSgFsA0kdEZ8l9/8A639F/Q56Y/x5R4RbImQv3OOk7T2vcL8gfWNVwNMqHZj2R2WxNBvKDL4IpD//AHb1/ZHZccsfkgYoKhUjDJDekUGWL8lvE1Etkc6VKENa+F7QUBy4gKGGT7SD0uVKPswlyyNR5PTuOmdZd75ff/2vpf0X0WT+LSkHmV+6idqk3MfQA9gfH8S9WmnBvEHIulxVRVZzAoMlDBoESR2rHoNhvuKUylAqHiL3Ktt1ZO9E/oVB2loW6YtmPpKkQFVJeK3oP/e3iMYRjucYznLE8DgKF+oe8vtprz3o0GnfSN7ktYUHJqAta4mtfpzA2eHvCFmtjl0YzVMLATZyrWOMXAmDVOyaKbCykQ5ILd3NOYRbalwpFkqu8O/xjQqV0sLy1Ai0yLZXJFAhvCayzClgNIqza00Igqgy7mimETgCbFiBeaegmaSLjejb7UcA5ORFtydVmLhcgIZZK2vEMOvs1JVF3YoRzZLrphVTB0Ka0gOYir8R35RkUkpkHe6sg1KUUFvhsV1avMP2ZKwUHoOgq1U7eR+53Xsndeyd97P3O+9n7ln/AC/c/iP3P5j9zvvZ+5f/AMP3P4j9y3/h+5/YfufwP7n8D+5/E/ufxP7n8T+5/E/uZXN0Ea4HDZ+YzHhfQYz3YNK0lakQJZKznLZzLCZXNTpzul6/EOPJTNWAYGrB8y3UtblFl7dThXfMITVIaCL4Fhs0xiNSm8SDztXt2JZxtpQ4JW8luqKqP+XWGGtlRk1D1lDByagMtdQThBTubeuzy0Ua2xrCu4R3Uay6lYaaxqNX7bHC1mNGld5pxFclmu4bBZrZQ+1yreNR55h6Jb4HOWto2xVL2oKF1raBd8YOCNnMcZ66vD5IlVyOQGno7CWZzGlzPay1xj2Go3hE2LVoUtrUwXdEo0Xs/c772fuf/9oADAMBAAIAAwAAABCDBE1yzBirjqoIyyCxBBRAAALNiwyJmmT07B+4Bh88QDAB/OJwaddDqiG1ilZXD3BQxfVRf5NEajljCUrh8PxUYADXUvRQ/wCj8FD7UD8sY7LqAAAqm8ke4GTh0HP8eKNUWIAMAxQVysPnQ9YkJfI+1Mz8AAAvAULQ2+ci6GRqBmvHHrP8AegWXUSP6n4dCwkYCxHhewADHIBnEKPL9Yns0ZDT4OM8AYABSEDVxknoL6YSnCNBDAA4ebX4sOWucI1js2nMWDEAAItuKcuWAwgACdCgbAIEcMMVvMMMMMcMcOcN6l4ahIcA1bG6y+y2+KKCF/7XBw6Otw//xAApEQEAAgECBQQDAQEBAQAAAAABABEhMUEQUWGB0XGRscEgofHw4TBA/9oACAEDAQE/EOBkQ8tX9R6MGvT3/wDlHgtC32ALXVrkZm2qlbiajnf06zXiFy0OeVjAA92/oWX3gnwW2W+WBc04aFTVYDv4jK0ehV83HMbtQMj9nf8AFoqqgDoGHSyMhs1Zrm72j+oSi6t6vjjTKNfw2rEet6xUjQTLQFYcHj1litSADJhuttStawrbfrQV1FDutQh7oL9ay94bT/iBbox0dR9yI9QxxU6Rn2zE7nkbB0hOtpDfYroup3NyNgIxV8ipWDXHUlIwFddu0q3ErGeNSsSxurLka56Xp6zKw1N6GA2b1fabrFIDVAeatu2spTR9SWhGXNVdoC5WGLa69YErN1afuaDj8nFwYqALW8YO8XIehPmNQflsG66Ac2OopOa0tnLfK5gtYeX0zXeAITwDK5DzgOrFBe3U/Y59owA2Qp7m81LF7mzMaQLMO8Y5beofIanWOcMMwSMCQgJtG4/92muXooxzzefYiLC6HIdUfo7/AINlQFe8xoaAi39Blq8ywqskKu32wUisGRi8coJbi7u9O00l9aqfb5qLqei8wD6RefaMIZYe1eeGljTpNRWZfVocFIpAM1aontquwSuDWrrX7bjfVl3nNjX1HqFfgoO6vtVcOiof3FTXAk+XQc6Ke8EkvuGf1mKKG9oHzOfMPRpR7HBakGIHrGISkJe0+Yfr5g0tgyo/t/0UvuZ+Dg6ZPiWjc6T5mzg4VJY/7U5nKJM8Co7v1p+9YcLIcvPbtqx8rS2YgirBqaEW4qZAa2PsQXKrb8E9Tce2T7jrLviw9V+obpBY9HzBmUI6TQiJRyP6z9Sm5D92xZkhKGkARNoFhwQRUarb9f8AAQbWHe2mQa7zKdvFY+r9S9eRB/kawS5geyENXWvfEsXp8ENYLEKDxTN+UMGYh1mVTeLvDMQ14l3b71Ba8voSwxoLWxespUDMdhTMqtor3U+SWUc+GFUSzKJQrg2GaHYt+Y6cEKg1GqKq3iVygAGVVaA5u0JQGqXmw0xD5gblkzy+plmMCnLVc0Xy21iQHGlq6qtOnOCMe0+JdSW5cs79SLDJ2fAPPrFCa5wUPSmm+pEEPeeJ8yaIqffeI1P70TVE1HWW/oVO4D8S2q4WxrqPwLJGqAug2PqaxYmQOq21nUlc6UN06krmnUlc6VzJXMnURNQiTsRneNV9A+oaEvPDBlh0yX+FxcYucn5/8z/u2eBScy2MBtKQ78AdpXLCpQ0BQ+Z/feZ/feZ/beZ/beZ/beZ/beYN53mf13mf03mf03mNVB2t5gwbXv8A60VhP23nWnWmrMpAKzDkT//EACcRAQACAQIEBwEBAQAAAAAAAAEAESExQRBRYXGBkaGxwdHxIPAw/9oACAECAQE/EODZac8fMHItp14Z4CLBqXmLBlvBn/g6RMCZaUx3VDwLYRULRNs6OjtxLf5Ost2PljsQvLMSpTUqW98N2BQMOqHvCc88n+aUWxbRYs1ByVe+o6bVvMuTr+QlMXlAuZkrKVC6+ShNdsrTfJCgywC6nHYV+vpFconGLxKLv88ZtIH039IgWcRANWFzXu84tVFW8y9+ps+EMHYgmGOQ77ZgvKXBcAfw3tUtDV2wTPjWicIwIdZ7BG4NafaVItVtWsGqddaX47R1cm16+kKA8jigEBtXSDgJ0Rjuof7Tf/Mt3OR229JvWauax8tXB3iRbro0ekt9nkW/WA9B/wBUpqUqFJXlcXjZd76wi9YRiBGYlrjbtHSh8WPb5htTBlfj77Q04iBakrhu3a+ZyQmGjz+iAXqF6MQLZVQLhj0y/ES9AYiae9WPOKc8x48G4RLmyZhImhBmWZ4FOQa11dA3Umca3Tpfoe0blY051qRihX8At1KPnhQ+jGYsat2+vqasU5RrJjltN6Bt04MsTVCNbnBku0uY1foJ8+0daMuCoKs29AS5rU/jwhlCArkM1S9YiwynrKGLNEBtqk9YbAo6zTEAt4HJw1DmTvs9VCiYMVjsRbj8U9Me7FhAzze0cuY/QRtDG1I37y1G1blhuXtb5gCODFN3BBnKgdCvngsmZV1JkHt9ccgdPdiA7kdJ1e01blS8PA0Iag6QKck5MuexwSzlBGWaQ4HOb5GCgfHip1xNQ/yk96iKOYx1XioBcvtGwIaXSFOxAyqK5F6YFzViqJAYyXxoPRhrKNYgIFRqiqt4rNQKq7AFvhH4jbeGMtfOVABK8TummsPkVA6nYyW1Wt50YwZyFgdb5mqrpFfrRTbTGepyzBkE5r3L9QkksZbTxLPOGs8tM7PsgJ8aMU84gUljKxZaPL9lFxzME6wv+CeEcI7iZITQDg8CZ6rK3TOigu2D7Ylsjyc6OdHB9sTBpSEjoNoa4G/BLcQ+4rzz5REaeNEOHlNE5fg/5+tIMJ8bExUMQiWpg2bjG0QDcPJxwBaXuTovKJ2h7PqfgvqfgvqfgvqfgvqfgvqfgvqZPhfU/BfUyfC+p+C+otZZ2fUSLU4xkeJw/OjNblnQDTrSl+M6EAdIjBCY1gtrpz+Z0/lP/8QAJxABAAICAgEDBAMBAQAAAAAAAQARITFBUWFxgZEQobHBINHw8eH/2gAIAQEAAT8Qv18z+52fLn/Vz/q5yfezr+Z/cz4+bP8Ap50fLiWD5X9xsz7v/qEW/wDD1gBgPRRIVh5VAW131Q3Mzq/9wUX9x/cG4tHq/uKjD+7/AHLD9yUcX+6Gk+ROT76Kd1+rFDA11t94iZ2cf+oDv2P/AFN1Z74r2N82x2ryK/8AUuUk+V/caMFXSlW/lzr+fP8Aqv7n/e/3BP7s/wC//uf9HOz5c/6ef9VP+q/v+D6Q+BubkNg5iqxcN9gRVqAFY91qZMparsjFuU8MuamG34ir7YIG0+05zmHEwDBBS7+Yq9D6S2iCmdQekIllT2hJWo9W4odEEwkoLGKNsU1XpiGqftLGK2/iZaiI0/W3T+Cphvqo4cUsgt859VEYnaFpzHAtEz5PtG0rltqf0iuHPi4/WZinXBC1maq3GXMAw8Qu8PSFyqzE6pIPgxXUt1ERaujgYeLbPB62ywL6L+CEbQ4zuKalzaQPaEFQKaYSNlysbPTMNGeQD5gwAvK/YpXbGxY9mmCy82VR7/qE1FVz9bM/j/DWb5ICjbElFzBa9IAOY3GCjGu3aIhNUN9RhiQwIL2DjjKCS9S8lpfz+I1ogNSwo1CrUveDNQ9g9amgZmHJ8kyQUbIXeYFG2S/iCvb8S0WDADajkcKNqbQyRIFreboA0YWjKxoY5CXap1uZUbUNGbQWLRFxiFAFUbgOSWYG70QmlRIVzGwWo/RaLXAUFooC1UAzGzsQVc9GXwZB3eDdVtn85oKuW0JGxAI+SLGocFKkUBuiOItKIFQQqZtFnkSxLMwwVOPpT1BV/AlZC3zEp1ExRstyAMk7o70A7o2GUDSg8RVLStCwAhrspsgdENBQHpLyvliYqMrdXAMuoXJjiYIK0EKhWYVYMw7GvWDUW4ltmCWMOuoyQDnGCHdP2xxqIOqyizkw/BBFRdzWyoAtrIYj8jck1PxVbC7aiAOOpQyvl89+MyX82esLCVrJhbc02X4lWYiqzzgv1/3cOlrTYYxgUL7zY3B+03cFvoyku4AQfATHsGS+46PvHHIaBUopSL1nzuAX+sjLA4HkDqJwrQlh9Faz7QIV9dqY0CDiP/fiVtZJtozeYrWBhN6LZ7wrY6YfSgBT4AHpU1rb4gGVuJyYlpuVIj4gmU8xNyriKoVmAbPiBFWeLgtsRTKqiVv5iFUanUmrhA9xjaXQWVWq6psDkNVBlolXbFAooDGjtY1XxeI+RS0ZIUxVXQaJV6nTBaB61AOMOM0B+YRJIhlAIvm2vaEq2rhGFtEyVHy/aY5RBYj1FZ9SW0Co3mutAhYjWA5dsmJXtY/T7xQuL6GcC9NB4YoALJCLQwV8o8RslADlgonkSxx6EGRo+n0RRmAUY+j07izDcLrIPAaxLFFd1f3h4ngDPAenPYvNUVFquwOkeSWkONLtV6lfmFV0AAtzjCjv1jo5BttBL6sRPDKoAHnOpcX+IGVQW1PSeKGZrcC3m6lAus+YbIIu7mJv1riFzj3lunxKOP1HtrWKKo+UDbktABu3QRdHmY3Z3LKD3lILNAiGjU4CeA7ksCGACXYw7tYWz3zOsiwS3ePLZEb08EBah3irhuXLEquld5sSelykpPleuQA7rtRV5lCklYNgITSMS1SQbbu0vsxI7kACotW1tGoMlg3rS5XdnSmo5tJ0FVYPjqC2qOjKAXPiG5VfUqVX0Mlg++wai51oe03+bhWUcmUqh9Gz0qGG1s3BRSkODjM426YveYXJpW1ZvAx6Jn3uWW1rrRhgazCvgG/AgUPKBaLlYkNAaqiQnz6xbToyS3McLZfqWXjXicLR24+8IHjBh9Je4ePEO4QxxBZwkQ0R1eNC5DMJZdmH0YckJa8yESZX6aUDXUDvwW7N4XfEArYooP4jtR7LK3CO9XqxyPtDLLpcKkHysBavaLoag6tfQLwRFWFWSh4wfeWuWC5YDByol2KO4fNReWwgNIOaA4j4xugQ3oxBLBfC5hSSFz7CxbUrfZgea3ueoFeRtVlJaoKikCD2T8RYX9bM3G1olSMTJ2N3+IgWXZwkK66yWA07DSntAoq+JXW4XSnFvAzlhgampA5N4E8RQuiO6VedS1gLNiAXw1vwSwpjXRAp+cx486pfRbZgbTQ5l53Q39azdlOo4AnKJ6aF4AEbtoWh9KB6U9Mfg1INdLwGCyU6JUk07HZPIPSUNVEHKCLOVblD4YU6pA2J6kQJohlh5YijJKDICuHPOOXlWVIpiZsfSLMW1oPM7TyPazeSfEt/f8RgpYbZIJqwPSq9QjYfhRthe6J7ItTJKohrwlx+4UjBdbkcUlNmaVigTaWag0sOLBXpCshNGGWlTzqUFly2SFgg8pFSC7TcNAEACg1Le/rb3GKQjdqwoT7yvolZxfEJoYy0qo/YxuCgESxHDHlo1SPo3+dRAMdVVYg50EfCCzzd80YwVSmFuiqjLz+4dnwLb4GaAJrQ2eVFfLMOZbjSahZkDhjKJhNLheTXVSqWWDM1QsSIGM1WPZgtjFZQ4C6+SyKAZzhXsiMEcPhnD4fMeWs/itnhEm9ilhBWWeoBNamOQuZmueSYRmBO+PTqDJAOBqKqAbwsmhRajIhEukBXY6ccRjZgts31Cs7RQ7WA+YJOyFqqzz97jZPMvE7he9xbpwtDRc1bCN3M8prV2pZpkDRhPSBdkNFGnPh1AY+pWRReXCrceQIcexVuG/RwrQGDiysvodAHIHZ6jtmXlv8Ahb1EuIEDtIVQjesvUVKKT6gAFcm9yxJlOV1Tg6bhYKFglQKCnPUX5fVbAKngpeyYEsGFkXzS1aqqqrcsZbdqaZSlVYyKQ3k7gLLdXslNHMBh6hGKTcApPf8AKX0IkzlldqT7wvMbQOTdWJRBvIJbGBZyLt7ajNUXtY8MGvN6EbKzF0lzmIU3ItoatvFR5NQKAIl5AGfMO40MWcwVh9pfz8QhXDmyEK0Zo47P6hcyWjDLD9oLW/riAkqXc0Ku/eUaaOB06/ZAq6quIBKIq6gyW1+I5j6wPuBRWoHXK3C5AjpYo2sSRzlzxFr/APiCurYlV21VZgIR0Jsa1A3iFiqsbDIz/wAjpQfaU9fWnr6U/gi3VncFovQNp4BbKc1b5EDVVou2sMPNGUxe7LU9iF3QMI6LFV4mAv42dYgwMuTqPbCI7S0AXzuC0AeIzruKrCFEB00JfZQz4D/N2grTgg/yazWidRVgtjZUCKm5FiYVLCjmxMwiLIVqhuq2TOuSWXZIohUbttHh0JBrLGk3MZpOL0RKcrX+HcDOYkWL7xnLaMFzljt5X69vMaQ5KtfkMPvNY5zjLt5e0cyzed8MeyBdJXwTCCjFo4PicT7Q0twjKQPQ7l0xz4a9pysTRuRgE8i3XOYjRoMAYwxXpiDnC0zNWZi4R2DMi5GhQE2Bcf8AsuLTRRifJ+41ry38NVwQoG6ROfqJRn6VE5gLV4JcqhC64B1iCm/vFVt+mVtD82VC1bbm4S1iWNDt3An0m20diZHlakSvLXoIOCFeRwEQA1nfhQgmqo3SqzEHXdgFLRKc7ciqzmaFxh1LbNDRvdwwDMydUO8ratqVSwjWPegbgnt7GIInpAJLSoiDImBriW4bG70P3l1k7nP5iA9FiYDhFl84CKRcFb5Q8EpLN0EoyPiAfEdWhwZFiNJclbnCg9VAeUiF6Mc0YnsCJRKoYJcGKy0UG2IRERpGx2h8S8trIALVDkPunxKwc6im5rZsFK+LUJd6vYA/xc5qe/8ACiU83yUfeoydq586P4KjUBW+ES6VOCYVAxIKcRMPq6wcInSY94ugW0Knyec4hQFg389sCAHnXsFeKyVp9NIKLZFpeskvxEEg0IDfBnDjdMsmFWKKOHI3ivIZaOAphQKrzAElxnrUoFkvt1MKKsTMChhNKq+yTHT8xqt16wDEWsnnf6hzfnc2fSAXD3mY9EJwFIHaouXpowD48pXxJKUDOMBX5yxDlbsbeGlh+8q2aKdDHXMAZ3gtg86pcRiNQVsErOImhamtblaGQemr7osYLd7DjhBEt+38SXwHllAGGGHz/hSjkQ+Mo4TGXpXUeb4uoDggpvEtpiV4DYgMmRedxDeCCBYBAAUhmcNWWktU2VSzG+IqCyOWgU/V3uMZE3WCgctXVZlI7x1C6gm9ehg5uxDKDVZTiNjtOP5mRVbVtbxX0TAtNelQHcJHEhWnr21AL6SgagKUQih0x1C8D4gzG2QqF9w+8BEPRuV6SHoiWJL5zkq8Q7C9ltSqW7AKJQMwvyraTWti1XD6mLYEpNANrlWeLxZ5csEu6CeGDPwqtjkfipoekyzR9UpiBsGQfMAnYCshhTqbPd4V+SAGQ6/RI9z9VKflEUikCrG+AHxmYkQp1NEeKQ7WIxdhwBcSi53UliqsCVWbWDY6BEq1VpjYRXvWgVWWFc6heGGdNd9lKJavcfQoXsUGFKC1slhF4ZEqOCgCtQcaFSKomNFz1HR2JFUKLW1KTJcSux7xZDpEiNp8ytl63G/tNS2qWTxEl2YSJ6iwnRAQEQWpMmKEle8uMUezCIfmvmIYvKtQvGQe8OAYSgFAeAwHQSpYL0+q89EKo+WIYEDZ9f8AEpmAKeefv+JkyEzFHqyZF9YJx1cCm/rdkK81uPM9Er6WlPGSNiy57iBq6QriFM+cyqAyhJ3iLzCnECaKTFrO419lQHeHRrHqXLmxgDjwQvmBzankXLMQqEaXVK/aISzIMFF/NTFtSinTKTP5ztiI/ES66a8h07tvLgDyziJ6ibrAEGCIOSFc8YzLGW+ZdzxKA3iiMFTm4EF8RhtqJyfqAn54eWWeykbfWkaES1WtMDdNz8Kv9kwv1rpoD4KYyqqscTXEpWdgwK/ZUUKttr8n7946lYLQPuH4r3isHxErf1S8RYV5ImCI6SuDGEgm8u+UMqVaT9mJVLEiQAUcblhZbx6DEbuwLpOfY/ZmKMGyDhwQ7jKqD19e8Q3MmBBovHnFW+lImBgaiaoLRj0INzjcIHVW1hL1UUCtg9x/Udf0RNf1jOLgHLT9VvSKt0W8xMT/ADAC/iVCjoirRWHysJRYa0S9VJnEZmtpgL9Zem5SovA6c9wbm/hVhZFUrmsODcTYd6MJFHw9mC8m4ba3tfMSwUWUbK7uALVAew7jcXUxzX+qJkAvYu7m+3FvXI+YhtrebgIYknAt17XKen6tyCJSMbJ1RRLlZ+QmO/P9OHdNL90JBfmPYQDGtQB7xEqDKPvON3vq2HxqUeyujO9PiJMxvkhhHtuFidZgC39TDbYo5q1D8faYPpq5tyem/aI6NBrWsXCjgG3lz+kVIep8PeNM38MeInhpf+5RSUe0I0MMVTGuwe8qTyJxY/IfMBe0o5iMLi0H0go3OD7P9QWVfxCO7CHkL7le0TFTR6i0idwlZNAOAheaiqD5lwNlRQFYEOi971WbjcKbkRWITykZhGlFOvB6sGpL6dZaPipRj6gEuqLxMzwIfGX9y8W+8skMLj/FSBBf8mAbez7pho3iNafMuCxcxwQVB8oxDYU+bE+5MqXllIRbQNdviwr1Ixyw+OUnvd7RFKIiHR/k+YEq3Fdfk/Uq11npjuApvk1X3Fe0F+qZdvSF5I8OCe4gvxGWuiMv6staQ/wyhxzKiziYg3AXPmIiqtb1f3CpR1CxZcVumn2+q5z8Royu+LeQlP2xr2HntjAZmVaAnlhE27QVt5mh6fUDgOX0r+pkvJe7a/RFbXG7mrSx5JRVR/kxYP8AVpaEOI6dRN3dN1yzlDel7ORB0JfqQheCq8XmDY8zeBkfmpQsrzLfXsX7RhlMg3QvuPxMmRAe+35ikmRjzaJfLGFAtMaLnTBhTV+IgHsuUVyVqUKWwvd/vfRJX2ZqHS53bfb7o7WcRNX6eYgXWepQKooVvWpYuoLTQf8ApCd1RaNs/sK/v6nJQycQ3CdhjofWogzAq9wSZwK26eItIW8HH0p6+tZXZCu2iCL2M12lsciOpfJCe4C/ggZKGBXSfNVEQ52xVjFLORDkGAm6PX0j8+TTMJiMpQROKZCi2LrUv1ApL6ousDLegergvrleywKFzWgb/hZIF4UG5xa+V8RoxCKDQqKZt7DE7Q/m272XB3h8YlzzWoy+sx0KMy772ofZjhuokRZvVTsugPwsN72HzH41DK2jpv0mawR4XMZYpOntf2miGf0Q101+A+t1SsnyPQsgAo+Sb5ZXxB5jA66OpQaP44TRvjinCDaNfoRTmYAC89XC/dfgFRsQlTOZtLvHkdxjqrVuoNlaDBbZFQgo4AsCgt0E5meUWiWUrUsK4t3KkyeDjsPXcPrtZ1hjtTVCtQ+3FBpeeKYW20nrv5gUFg4LBxYKtVggwjeMWx7w359IaiHbL2CpVOHQ7RbJFNNMIumuViZUrkzd5I9cwhzOLgtdS/M6A+cFTnuXSM2SOvk3+pCGxdZNKAUULutzMLtaG4DVUGjmCWkDQZ3CscX6TT5FPeClEdpMd3mAHYPVeIb5QCvBGpUErogX9KNgpPFV9cHTEfz6xAhOZXkHLxfvANUNFy7v7/wx19/rZEYC6S2YFHUsos5WZ4or8QCtjkhq0gK1faIXlGCEACnTg0AwN5eAtxZXSa4cwAOQgK0hctXUsoEVBJ6NpjGpVjlplx0eA0AueJnp0KKhJoEAYvKuYAyXgZQweleREXkWBA6QBalueIjhdv5TE4FMEasZSCmND0tr1plzgenfrfEouIXUEV9qXArpQyidQQS/3LYnRsvVpDZe5GM4cXublOiFNrZct2+sQz9l/qAUC6TeGv8AyLiyOCzghYFt+I8+ymVEMEKRpoOkjge+I5pdBHgzEU3u+IB0xTRgc+ofMfC+rdon7jALmYPofeqNxf2avq1RXvDHP1UxyfGWC1JF9Aodo9QNl3uZwSgoOJZgHyi9FNEVbWtxAk3Y8zAuauie5p33X/NL+PussZcetF/6H0UrJZNKxQOSrgkXM5eAyCqm/mpHAecwx/YMzX+SKor3IhDZM7hjvDGfX6P8wrQdwVkJCi8OIzrbBwQmChL+fSW27KelQ/uG7Ge55TThKjzX2DFCsNvv/wAR7QU1KevoqswDEzb8/UhQjArn8/VoCqlBJk8Z5i4duI1sCmOumbNi+S5fJ4gjqNXl4mUWppVkvQSIU4JZ2Q1FL394JeyX4iDwQxo+30W45KZR1FoijtlnZGmRnKbRA75gVCyNi6BipUNWQSu1RzuPMXvHS+P9EUzHYgN5hi8Lkbm/VVcwjZYLINUdS/Vq7YCJOUpq1ENt16OFZY8DrSuRcIAREGNAzAKMfW7uLqLh/MSwGJGlFVj+KX/Iabjcqv4Niq+o03Fa6+it3DAx2mcHUrNFNPyYQRwjmBwwMFPN515M7i21aGNUDQiwb4RdagYfDWwMcbIoTcAL7tZ0ua4fKQIygkQtKxqlSWj1HC6KfY4cgguYaJOC4uhVXcZlHNJUIAQ8FICgPknk11yWk6QBgRBGwbzY8k0PSJNwQu5YWp7wpXLaoF9I5a5K/RAdPmWdks7JZ2SzslnZLOyWdks7JZ2SzslnZLOyWdkp2SzsiDmWOn7wb5PmWdkp2SzTCL2r2aaK7JcpweCY50C5sny4AEsmRsxxMcJFDb6yx03L2tO7Nv4R4QTqqi0I7JCxqxxvec+J7uKaS7g+jQWwQUoKFK7iFAe5c30yQgYE5Rpvzc8D5gf3HrwySFMlC2CEQE817pkGZi9GRIitUQ5eYniv+vEu0f8AXiImf9PSf4f0T/D+iHT/AMeJ/h/RKNn/AF4nif48TxP8eJ/h/RP8P6J/p/RKs0f8dSu3ZkV2oKo1vCBrUWpFsFRvinuhSVyCk6gC9wLE0VHOxJZpRO0eYhA51KFV2VlXVSqwnVLSksggNNj1DyVCQoBtWkG0L6mpR4iFiYxtLovJLT9iDFBVS+nIwgG/D3glBDlHayCOLI8xwyHdqLqLRQxCIXgXaM4zGtDfeAElXyFpoZvdF5+k5CcmFZIbbfTwamUEc03SrSMNUJoK0cBRg6Y7+uVEvaxe2lGrMxOzihdZYj4/cp7/AElqFkFR2pYZcIgwFBObwCpzUELAJgoNRNDSQEgfqNxEg6gGIAcVUG93n5AB9ESmfYruOD/H6zg/x+sBKllpZylTA6USnKanEskpcwbYpjGEzW7jeZF5kXlReVF5sXnxGUCJqroVg30jmwELJYI5PUfWb1eY1IdW6nWEKxbZd0T0GzgaGo8HFaQyEpyyGrBAqWBQECjlr6iHJgERRAEagUvmcDRKfKyErGUDu5IgQxVxowRJUQd7EBzogAhe2eRCvAD6AgwJR+asDsWYWVpeDdnPaBTCBZIBeTDq6NiItBWvQZKg4Ylo/bIJi2ZbJmaAVJyYO1SuyIKJ7U06TeIGxHBirL9jFIjXAdZFiM2x2CBQqL5ACFFBKf7JRVEyLOwBKS4+Hv1bUumBQ0BEGz6Xyx9eQ35E0EIrLXgvQyVs1gg5fUb/AKG3/9k="

    }

}