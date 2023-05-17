document.addEventListener('DOMContentLoaded', f_principal);

function f_principal() {
    if (window.location.pathname == '/dashboard') {
        graficoCicular();
        graficoBarras();
    }

}

function graficoCicular() {
    emps = ['Eduardo Lopez', 'Samuel Santos', 'Federico Garcia', 'Susana Diaz', 'Steven Suarez', 'Alberto Jimenez'];
    qts = [30, 40, 50, 23, 12, 53];
    var data = {
        labels: emps,
        datasets: [{
            data: qts, // Valores de los datos
            backgroundColor: generarPaletaColores(emps.length) // Colores para cada sección del gráfico
        }]
    };

    // Configuración del gráfico
    var options = {
        responsive: true,
        maintainAspectRatio: false,
        legend: {
            position: 'right' // Posiciona la leyenda a la derecha del gráfico
        }
    };

    // Crear el gráfico circular
    var ctx = document.getElementById('circular').getContext('2d');
    var circular = new Chart(ctx, {
        type: 'pie',
        data: data,
        options: options
    });
}

function graficoBarras() {
    var data = {
        labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        datasets: [{
            label: 'Rutas Creadas',
            data: [14, 15, 20, 12, 25, 0, 0, 0, 0, 0, 0, 0],
            backgroundColor: '#d6882c' // Color de las barras
        }]
    };

    // Configuración del gráfico
    var options = {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                beginAtZero: true,
                stepSize: 5 // Incremento en el eje y
            }
        }
    };

    // Crear el gráfico de barras
    var ctx = document.getElementById('barras').getContext('2d');
    var barras = new Chart(ctx, {
        type: 'bar',
        data: data,
        options: options
    });
}

function generarPaletaColores(numero) {
    var paleta = [];
    var letrasHexadecimales = "0123456789ABCDEF";

    for (var i = 0; i < numero; i++) {
        var color = generarColorAleatorio(letrasHexadecimales);
        paleta.push(color);
    }

    return paleta;
}

function generarColorAleatorio(letrasHexadecimales) {
    var color = "#";

    for (var i = 0; i < 6; i++) {
        color += letrasHexadecimales[Math.floor(Math.random() * 16)];
    }

    return color;
}
