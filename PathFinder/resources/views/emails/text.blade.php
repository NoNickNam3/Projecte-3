<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enlace de cuenta de trabajador</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.15);
        }
        h1 {
            font-size: 24px;
            color: #333333;
        }
        p {
            font-size: 16px;
            color: #333333;
        }
        .code {
            background-color: #f0f0f0;
            padding: 10px;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>¡ {{ $nom }}, te han invitado !</h1>
        <p>Hola,</p>
        <p>Has recibido este correo electrónico porque necesitas enlazar tu cuenta de trabajador con la cuenta de una empresa. Utiliza el siguiente código para completar el proceso:</p>
        <p class="code">{{ $codigo }}</p>
        <p>Por favor, ingresa este código en la plataforma de enlace de cuentas para completar el proceso. Si no has solicitado este enlace, ignora este correo electrónico.</p>
        <p>Saludos,</p>
        <p>El equipo de soporte</p>
    </div>
</body>
</html>
