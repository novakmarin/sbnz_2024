import { Component } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { Button } from 'primeng/button';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { MessagesModule } from 'primeng/messages';


@Component({
  selector: 'app-log-in',
  standalone: true,
  imports: [CommonModule, FormsModule, InputTextModule, Button, MessagesModule],
  providers: [MessageService],
  templateUrl: './log-in.component.html',
  styleUrl: './log-in.component.css'
})
export class LogInComponent {

  username: string;
  password: string;
  error: string;
  msgs: any;

  constructor(private authService: AuthService, private router: Router, private messageService: MessageService) {
    this.username = '';
    this.password = '';
    this.error = '';
    this.msgs = [];
  }

  onSubmit(): void {
    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        console.log('Login successful', response);
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error('Login failed', err);
        this.messageService.add({severity:'error', summary: 'Neispravni kredencijali!', detail: this.error});
        this.msgs.push({severity:'error', summary:'Info Message', detail:'PrimeNG rocks'});
      }
    });
  }
}
