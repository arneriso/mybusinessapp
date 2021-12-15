import { Component, OnInit } from '@angular/core';
import {BusinessUser} from "../models/business-user.model";
import {Router, ActivatedRoute, ParamMap} from "@angular/router";
import {BusinessUserService} from "../services/business-user/business-user.service";
import {Sector} from "../models/sector.model";
import {SectorService} from "../services/sector/sector.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.sass']
})
export class UsersComponent implements OnInit {

  businessUser?: BusinessUser
  sectors?: Array<Sector>
  id?: string | null
  routeState: any

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private businessUserService: BusinessUserService,
    private sectorService: SectorService
  ) {
    if (this.router.getCurrentNavigation()?.extras.state) {
      this.routeState = this.router.getCurrentNavigation()?.extras.state
      this.businessUser = this.routeState.businessUser ? this.routeState.businessUser : null
    }
  }

  ngOnInit(): void {
    this.getSectors();

    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = params?.get('id')
      if (this.businessUser == null) {
        if (this.id != null) {
          this.findUser(this.id);
        } else {
          this.generateUser(UsersComponent.navigateToUser);
        }
      }
    })
  }

  saveUser(): void {
    if (this.businessUser != null) {
      this.businessUserService.upsertBusinessUser(this.businessUser).subscribe({
        next: () => {},
        error: (e) => {
          console.error(e)
        }
      })
    }
  }

  onChange(sb: any): void {
    let selectedOptions = Array<string>()
    for (let i = 0; i < sb.length; i++) {
      let option = sb.item(i)
      if (option.selected) {
        selectedOptions.push(option.value)
      }
    }
    if (this.businessUser != null) {
      this.businessUser.sectorIds = selectedOptions
    }
  }

  sectorChosen(): boolean {
    return this.businessUser != null
      && this.businessUser.sectorIds != null
      && this.businessUser.sectorIds.length > 0
  }

  agreedToTerms(): boolean {
    return this.businessUser != null
      && this.businessUser.agreementToTerms == true
  }

  private getSectors() {
    this.sectorService.getSectors().subscribe({
      next: (data) => {
        this.sectors = data
      },
      error: (e) => {
        console.error(e)
      }
    })
  }

  private generateUser(callback: Callback) {
    this.businessUserService.generateBusinessUser()
      .subscribe({
        next: (data) => {
          callback(this.router, data)
        },
        error: (e) => {
          console.error(e)
        }
      })
  }

  private static navigateToUser(router: Router, data: BusinessUser) {
    router.navigate(['/user', data.id], {
      state: {
        businessUser: data
      }
    })
  }

  private findUser(id: string) {
    this.businessUserService.getBusinessUser(id)
      .subscribe({
        next: (data) => {
          this.businessUser = data
        },
        error: (e) => {
          console.error(e)
        }
      })
  }
}

interface Callback {
  (router: Router, businessUser: BusinessUser): void;
}
