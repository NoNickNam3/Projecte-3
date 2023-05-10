<?php

namespace App\Mail;

use Illuminate\Bus\Queueable;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Mail\Mailable;
use Illuminate\Mail\Mailables\Content;
use Illuminate\Mail\Mailables\Envelope;
use Illuminate\Queue\SerializesModels;

class EnviarMail extends Mailable
{
    use Queueable, SerializesModels;

    
    public $email;
    public $nom;
    public $codi;
    /**
     * Create a new message instance.
     */
    public function __construct($email,$nom,$codi)
    {
        $this->email = $email;
        $this->nom = $nom;
        $this->codi = $codi;
    }

    public function build()
    {
        return $this->subject('Enlace de cuenta')
                    ->view('emails.text', ['codigo' => $this->codi,'nom' => $this->nom]);
    }
    

    // /**
    //  * Get the message envelope.
    //  */
    // public function envelope(): Envelope
    // {
    //     return new Envelope(
    //         subject: 'Enviar Mail',
    //     );
    // }

    // /**
    //  * Get the message content definition.
    //  */
    // public function content(): Content
    // {
    //     return new Content(
    //         view: 'view.name',
    //     );
    // }

    // /**
    //  * Get the attachments for the message.
    //  *
    //  * @return array<int, \Illuminate\Mail\Mailables\Attachment>
    //  */
    // public function attachments(): array
    // {
    //     return [];
    // }
}
