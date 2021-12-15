import { Component, OnInit  } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';

import { BusinessUserService } from './services/business-user/business-user.service';
import {BusinessUser} from "./models/business-user.model";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit {
  title = 'My Business';

  ngOnInit(): void {
  }
}
